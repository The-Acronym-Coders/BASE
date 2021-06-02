package com.teamacronymcoders.base.recipesystem.loader;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.*;
import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.json.factory.IObjectFactory;
import com.teamacronymcoders.base.recipesystem.Recipe;
import com.teamacronymcoders.base.recipesystem.RecipeSystem;
import com.teamacronymcoders.base.recipesystem.condition.ICondition;
import com.teamacronymcoders.base.recipesystem.event.RegisterRecipeFactoriesEvent;
import com.teamacronymcoders.base.recipesystem.input.IInput;
import com.teamacronymcoders.base.recipesystem.output.IOutput;
import com.teamacronymcoders.base.recipesystem.type.RecipeType;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.JsonContext;
import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public abstract class JsonRecipeLoader implements ILoader {
    private static boolean loadedFactories = false;

    private static final Map<String, IObjectFactory<? extends IInput>> inputFactories = Maps.newHashMap();
    private static final Map<String, IObjectFactory<? extends IOutput>> outputFactories = Maps.newHashMap();
    private static final Map<String, IObjectFactory<? extends ICondition>> conditionFactories = Maps.newHashMap();
    private final Gson GSON = new Gson();

    protected JsonRecipeLoader() {
        loadFactories();
    }

    private void loadFactories() {
        if (!loadedFactories) {
            RegisterRecipeFactoriesEvent<IInput> inputEvent = new RegisterRecipeFactoriesEvent<>(IInput.class);
            MinecraftForge.EVENT_BUS.post(inputEvent);
            inputFactories.putAll(inputEvent.getFactories());

            RegisterRecipeFactoriesEvent<IOutput> outputEvent = new RegisterRecipeFactoriesEvent<>(IOutput.class);
            MinecraftForge.EVENT_BUS.post(outputEvent);
            outputFactories.putAll(outputEvent.getFactories());

            RegisterRecipeFactoriesEvent<ICondition> conditionEvent = new RegisterRecipeFactoriesEvent<>(ICondition.class);
            MinecraftForge.EVENT_BUS.post(conditionEvent);
            conditionFactories.putAll(conditionEvent.getFactories());
            loadedFactories = true;
        }
    }

    @Override
    public abstract List<Recipe> loadRecipes();

    protected Recipe loadRecipe(JsonContext ctx, Path root, Path file) {
        String relative = root.relativize(file).toString();
        if (!"json".equals(FilenameUtils.getExtension(file.toString())) || relative.startsWith("_")) {
            return null;
        }

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

                            return new Recipe(key, priority, this.getRecipeSource(), recipeType, inputList, outputList, conditionList);
                        }
                    } else {
                        throw new JsonParseException("No Recipe Type found for: " + typeName);
                    }
                }
            }
        } catch (IOException | JsonParseException e) {
            Base.instance.getLogger().getLogger().warn("Error in recipe: " + key.toString(), e);
        }
        return null;
    }

    public static List<IOutput> processOutputs(JsonArray jsonElements, JsonContext context) {
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
