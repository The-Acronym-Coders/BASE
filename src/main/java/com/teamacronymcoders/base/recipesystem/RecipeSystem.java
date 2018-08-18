package com.teamacronymcoders.base.recipesystem;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.teamacronymcoders.base.event.BaseRegistryEvent;
import com.teamacronymcoders.base.recipesystem.loader.ILoader;
import net.minecraft.util.Tuple;
import net.minecraftforge.common.MinecraftForge;

import java.util.List;
import java.util.Map;

public class RecipeSystem {
    private final static Map<String, RecipeType> recipeTypes = Maps.newHashMap();
    private final static Map<RecipeType, List<Recipe>> recipeLists = Maps.newHashMap();
    private final static Map<String, ILoader> loaders = Maps.newHashMap();

    public static void loadRecipeTypes() {
        BaseRegistryEvent<RecipeType> recipeTypeEvent = new BaseRegistryEvent<>(RecipeType.class);
        MinecraftForge.EVENT_BUS.post(recipeTypeEvent);
        recipeTypes.putAll(recipeTypeEvent.getEntries());

        BaseRegistryEvent<ILoader> loaderEvent = new BaseRegistryEvent<>(ILoader.class);
        MinecraftForge.EVENT_BUS.post(loaderEvent);
        loaders.putAll(loaderEvent.getEntries());
    }

    public static void loadRecipes(boolean reload) {
        loaders.values().stream()
                .filter(loader -> !reload || loader.getRecipeSource().canReload())
                .map(ILoader::loadRecipes)
                .flatMap(List::stream)
                .forEach(recipe -> {
                    recipeLists.putIfAbsent(recipe.type, Lists.newArrayList());
                    recipeLists.get(recipe.type).add(recipe);
                });

        recipeTypes.values().parallelStream()
                .map(recipeType -> new Tuple<>(recipeType.getHandlers(), recipeLists.get(recipeType)))
                .forEach(tuple -> tuple.getFirst().parallelStream()
                        .forEach(recipeHandler -> recipeHandler.reloadRecipes(tuple.getSecond())));
    }

    public static void reloadRecipe() {
        for (List<Recipe> recipes : recipeLists.values()) {
            recipes.removeIf(recipe -> recipe.source.canReload());
        }
        loadRecipes(true);
    }

    public static RecipeType getRecipeType(String typeName) {
        return recipeTypes.get(typeName);
    }

    public static List<Recipe> getRecipesFor(RecipeType recipeType) {
        return recipeLists.get(recipeType);
    }
}
