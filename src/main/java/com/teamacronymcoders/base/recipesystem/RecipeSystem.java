package com.teamacronymcoders.base.recipesystem;

import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.Reference;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@EventBusSubscriber(modid = Reference.MODID)
public class RecipeSystem {
    private static Map<String, RecipeType> recipeTypes = Maps.newHashMap();
    private static Map<RecipeType, RecipeList> recipeLists = Maps.newHashMap();

    public static void loadRecipes() {
        Loader.instance().getActiveModList()
                .forEach(mod -> CraftingHelper.findFiles(mod, "assets/" + mod.getModId() + "/base_recipe_system",
                        (root) -> {
                            Base.instance.getLogger().devInfo(mod.getModId());
                            return true;
                        },
                        (root, file) -> {
                            Loader.instance().setActiveModContainer(mod);

                            String relative = root.relativize(file).toString();
                            if (!"json".equals(FilenameUtils.getExtension(file.toString())) || relative.startsWith("_"))
                                return true;

                            String name = FilenameUtils.removeExtension(relative).replaceAll("\\\\", "/");
                            ResourceLocation key = new ResourceLocation(ctx.getModId(), name);

                            BufferedReader reader = null;
                            try {
                                reader = Files.newBufferedReader(file);
                                JsonObject json = JsonUtils.fromJson(GSON, reader, JsonObject.class);
                                if (json.has("conditions") && !CraftingHelper.processConditions(JsonUtils.getJsonArray(json, "conditions"), ctx))
                                    return true;
                                IRecipe recipe = CraftingHelper.getRecipe(json, ctx);
                                ForgeRegistries.RECIPES.register(recipe.setRegistryName(key));
                            } catch (JsonParseException e) {
                                FMLLog.log.error("Parsing error loading recipe {}", key, e);
                                return false;
                            } catch (IOException e) {
                                FMLLog.log.error("Couldn't read recipe {} from {}", key, file, e);
                                return false;
                            } finally {
                                IOUtils.closeQuietly(reader);
                            }
                            return true;
                        },
                        true, true));
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent<IRecipe> recipeRegistryEvent) {
        loadRecipes();
    }
}
