package com.teamacronymcoders.base.recipesystem.loader;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.*;
import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.json.factory.IObjectFactory;
import com.teamacronymcoders.base.recipesystem.Recipe;
import com.teamacronymcoders.base.recipesystem.RecipeSystem;
import com.teamacronymcoders.base.recipesystem.type.RecipeType;
import com.teamacronymcoders.base.recipesystem.condition.ICondition;
import com.teamacronymcoders.base.recipesystem.event.RegisterRecipeFactoriesEvent;
import com.teamacronymcoders.base.recipesystem.input.IInput;
import com.teamacronymcoders.base.recipesystem.output.IOutput;
import com.teamacronymcoders.base.recipesystem.source.IRecipeSource;
import com.teamacronymcoders.base.recipesystem.source.RecipeSource;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JsonRecipeLoader implements ILoader {
    private static JsonRecipeLoader instance;

    private final Gson GSON = new Gson();
    private final IRecipeSource JSON_RECIPE_SOURCE = new RecipeSource("json", true);

    private final Map<String, IObjectFactory<? extends IInput>> inputFactories = Maps.newHashMap();
    private final Map<String, IObjectFactory<? extends IOutput>> outputFactories = Maps.newHashMap();
    private final Map<String, IObjectFactory<? extends ICondition>> conditionFactories = Maps.newHashMap();

    public static JsonRecipeLoader getInstance() {
        if (instance == null) {
            instance = new JsonRecipeLoader();
        }
        return instance;
    }

    private JsonRecipeLoader() {
        RegisterRecipeFactoriesEvent<IInput> inputEvent = new RegisterRecipeFactoriesEvent<>(IInput.class);
        MinecraftForge.EVENT_BUS.post(inputEvent);
        inputFactories.putAll(inputEvent.getFactories());

        RegisterRecipeFactoriesEvent<IOutput> outputEvent = new RegisterRecipeFactoriesEvent<>(IOutput.class);
        MinecraftForge.EVENT_BUS.post(outputEvent);
        outputFactories.putAll(outputEvent.getFactories());

        RegisterRecipeFactoriesEvent<ICondition> conditionEvent = new RegisterRecipeFactoriesEvent<>(ICondition.class);
        MinecraftForge.EVENT_BUS.post(conditionEvent);
        conditionFactories.putAll(conditionEvent.getFactories());
    }

    @Override
    public IRecipeSource getRecipeSource() {
        return JSON_RECIPE_SOURCE;
    }

    @Override
    public List<Recipe> loadRecipes() {
        return Loader.instance().getActiveModList()
                .parallelStream()
                .map(this::loadRecipesForMod)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<Recipe> loadRecipesForMod(ModContainer mod) {
        JsonContext ctx = new JsonContext(mod.getModId());
        List<Recipe> recipes = Lists.newArrayList();

        CraftingHelper.findFiles(mod, "assets/" + mod.getModId() + "/base/recipe_system",
                (root) -> true,
                (root, file) -> {
                    String relative = root.relativize(file).toString();
                    if (!"json".equals(FilenameUtils.getExtension(file.toString())) || relative.startsWith("_"))
                        return true;

                    String name = FilenameUtils.removeExtension(relative).replaceAll("\\\\", "/");
                    ResourceLocation key = new ResourceLocation(ctx.getModId(), name);

                    try (BufferedReader bufferedReader = Files.newBufferedReader(file)) {
                        JsonObject jsonObject = JsonUtils.fromJson(GSON, bufferedReader, JsonObject.class);
                        if (jsonObject != null) {
                            if (jsonObject.has("type")) {
                                String typeName = jsonObject.get("type").getAsString();
                                RecipeType recipeType = RecipeSystem.getRecipeType(typeName);
                                if (recipeType != null) {
                                    boolean loadRecipe = true;
                                    if (jsonObject.has("load_conditions")) {
                                        loadRecipe = CraftingHelper.processConditions(JsonUtils.getJsonArray(jsonObject, "load_conditions"), ctx);
                                    }
                                    if (loadRecipe) {
                                        int priority = JsonUtils.getInt(jsonObject, "priority", 0);
                                        JsonArray inputsJson = JsonUtils.getJsonArray(jsonObject, "inputs");
                                        JsonArray outputsJson = JsonUtils.getJsonArray(jsonObject, "outputs");
                                        JsonArray conditionsJson = JsonUtils.getJsonArray(jsonObject, "conditions", new JsonArray());

                                        List<IInput> inputList = processJsonArray(inputsJson, ctx, inputFactories::get, "Input");
                                        List<IOutput> outputList = processOutputs(outputsJson, ctx);
                                        List<ICondition> conditionList = processJsonArray(conditionsJson, ctx, conditionFactories::get, "Conditions");

                                        recipes.add(new Recipe(key, priority, JSON_RECIPE_SOURCE, recipeType, inputList, outputList, conditionList));
                                    }
                                } else {
                                    throw new JsonParseException("No Recipe Type found for: " + typeName);
                                }
                            }
                        }
                    } catch (IOException | JsonParseException e) {
                        Base.instance.getLogger().getLogger().warn("Error in recipe: " + key.toString(), e);
                    }
                    return true;
                }, true, true);

        return recipes;
    }

    public List<IOutput> processOutputs(JsonArray jsonElements, JsonContext context) {
        return processJsonArray(jsonElements, context, outputFactories::get, "Output");
    }

    private static <T> List<T> processJsonArray(JsonArray jsonElements, JsonContext context, Function<String, IObjectFactory<? extends T>> factoryFunction, String name) {
        List<T> processedList = Lists.newArrayList();
        for (JsonElement element : jsonElements) {
            if (element.isJsonObject()) {
                JsonObject jsonObject = element.getAsJsonObject();
                String typeName = JsonUtils.getString(jsonObject, "type");
                IObjectFactory<? extends T> factory = factoryFunction.apply(typeName);
                jsonObject.remove("type");
                if (factory != null) {
                    processedList.add(factory.parse(context, jsonObject));
                } else {
                    throw new JsonParseException("No " + name + " found for type: " + typeName);
                }
            } else {
                throw new JsonParseException(name + "s must be JSON Objects");
            }
        }
        return processedList;
    }
}
