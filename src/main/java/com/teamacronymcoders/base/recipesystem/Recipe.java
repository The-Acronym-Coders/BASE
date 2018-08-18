package com.teamacronymcoders.base.recipesystem;

import com.teamacronymcoders.base.recipesystem.condition.ICondition;
import com.teamacronymcoders.base.recipesystem.input.IInput;
import com.teamacronymcoders.base.recipesystem.output.IOutput;
import com.teamacronymcoders.base.recipesystem.source.IRecipeSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class Recipe {
    public final ResourceLocation name;
    public final RecipeType type;
    public final IRecipeSource source;
    private final List<IInput> inputs;
    private final List<IOutput> outputs;
    private final List<ICondition> conditions;

    public Recipe(ResourceLocation name, IRecipeSource source, RecipeType type, List<IInput> inputs, List<IOutput> outputs, List<ICondition> conditions) {
        this.name = name;
        this.source = source;
        this.type = type;
        this.inputs = inputs;
        this.outputs = outputs;
        this.conditions = conditions;
    }

    public boolean matches(RecipeContainer recipeContainer, EntityPlayer entityPlayer) {
        return inputs.parallelStream().allMatch(input -> input.isMatched(recipeContainer))
                && conditions.parallelStream().allMatch(condition -> condition.isMet(recipeContainer, entityPlayer));
    }

    public boolean canOutput(RecipeContainer recipeContainer) {
        return outputs.parallelStream().allMatch(output -> output.canOutput(recipeContainer));
    }

    public void doOutput(RecipeContainer recipeContainer) {
        outputs.forEach(output -> output.output(recipeContainer));
    }
}
