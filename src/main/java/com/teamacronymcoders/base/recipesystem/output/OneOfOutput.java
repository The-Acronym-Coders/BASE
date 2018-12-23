package com.teamacronymcoders.base.recipesystem.output;

import com.teamacronymcoders.base.recipesystem.RecipeContainer;

import java.util.List;

public class OneOfOutput implements IOutput {
    private final List<IOutput> outputList;

    public OneOfOutput(List<IOutput> outputList) {
        this.outputList = outputList;
    }

    @Override
    public boolean canOutput(RecipeContainer recipeContainer) {
        return outputList.parallelStream()
                .anyMatch(output -> output.canOutput(recipeContainer));
    }

    @Override
    public void output(RecipeContainer recipeContainer) {
        boolean hasOutput = false;
        int tries = 0;

        do {
            IOutput output = outputList.get(recipeContainer.getWorld().rand.nextInt(outputList.size()));
            if (output.canOutput(recipeContainer)) {
                output.output(recipeContainer);
                hasOutput = true;
            }
            tries++;
        } while (!hasOutput && tries < 3);
    }
}
