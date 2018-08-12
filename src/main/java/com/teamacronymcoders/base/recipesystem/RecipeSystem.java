package com.teamacronymcoders.base.recipesystem;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.recipesystem.event.RegisterRecipeSystemTypesEvent;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import static com.teamacronymcoders.base.Reference.MODID;

@EventBusSubscriber(modid = MODID)
public class RecipeSystem {
    private static final Gson GSON = new Gson();

    private static Map<String, RecipeType> recipeTypes = Maps.newHashMap();
    private static Map<RecipeType, RecipeList> recipeLists = Maps.newHashMap();

    public static void loadRecipes() {
        Loader.instance().getActiveModList()
                .forEach(RecipeSystem::loadRecipe);
    }

    private static void loadRecipe(ModContainer mod) {
        JsonContext ctx = new JsonContext(mod.getModId());
        CraftingHelper.findFiles(mod, "assets/" + mod.getModId() + "/base/recipe_system",
                (root) -> {
                    Base.instance.getLogger().devInfo(mod.getModId());
                    return true;
                },
                (root, file) -> {
                    String relative = root.relativize(file).toString();
                    if (!"json".equals(FilenameUtils.getExtension(file.toString())) || relative.startsWith("_"))
                        return true;

                    String name = FilenameUtils.removeExtension(relative).replaceAll("\\\\", "/");
                    ResourceLocation key = new ResourceLocation(ctx.getModId(), name);

                    try (BufferedReader bufferedReader = Files.newBufferedReader(file)) {
                        JsonObject jsonObject = JsonUtils.fromJson(GSON, bufferedReader, JsonObject.class);
                        if (jsonObject != null) {
                            boolean loadRecipe = true;
                            if (jsonObject.has("load_conditions")) {
                                loadRecipe = CraftingHelper.processConditions(JsonUtils.getJsonArray(jsonObject, "load_conditions"), ctx);
                            }
                            if (loadRecipe) {
                                Base.instance.getLogger().fatal("LOADED " + key + " FUCK YES");
                            }
                        }
                    } catch (IOException e) {
                        Base.instance.getLogger().getLogger().warn(e.getMessage(), e);
                    }
                    return true;
                }, true, true);
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent<IRecipe> recipeRegistryEvent) {
        loadTypes();
        loadRecipes();
    }

    private static void loadTypes() {
        RegisterRecipeSystemTypesEvent event = new RegisterRecipeSystemTypesEvent();
        MinecraftForge.EVENT_BUS.post(event);
    }
}
