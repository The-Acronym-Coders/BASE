package com.teamacronymcoders.base.recipesystem.loader;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.recipesystem.Recipe;
import com.teamacronymcoders.base.recipesystem.source.IRecipeSource;
import com.teamacronymcoders.base.recipesystem.source.RecipeSource;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;

import java.util.List;
import java.util.stream.Collectors;

public class AssetJsonRecipeLoader extends JsonRecipeLoader {
    private static AssetJsonRecipeLoader instance;
    private final IRecipeSource RECIPE_SOURCE = new RecipeSource("assets", true);

    public static AssetJsonRecipeLoader getInstance() {
        if (instance == null) {
            instance = new AssetJsonRecipeLoader();
        }
        return instance;
    }

    @Override
    public IRecipeSource getRecipeSource() {
        return RECIPE_SOURCE;
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
                    Recipe recipe = this.loadRecipe(ctx, root, file);
                    if (recipe != null) {
                        recipes.add(recipe);
                    }
                    return true;
                }, true, true);

        return recipes;
    }
}
