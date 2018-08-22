package com.teamacronymcoders.base.recipesystem.input;

import com.teamacronymcoders.base.recipesystem.RecipeContainer;
import com.teamacronymcoders.base.util.CapUtils;
import net.minecraftforge.energy.CapabilityEnergy;

public class ForgeEnergyInput implements IInput {
    private final int amountRequired;

    public ForgeEnergyInput(int amountRequired) {
        this.amountRequired = amountRequired;
    }

    @Override
    public boolean isMatched(RecipeContainer recipeContainer) {
        return CapUtils.getOptional(recipeContainer, CapabilityEnergy.ENERGY)
                .map(energyStorage -> energyStorage.extractEnergy(amountRequired, true) == amountRequired)
                .orElse(false);
    }

    @Override
    public void consume(RecipeContainer recipeContainer) {
        CapUtils.getOptional(recipeContainer, CapabilityEnergy.ENERGY)
                .ifPresent(energyStorage -> energyStorage.extractEnergy(amountRequired, false));
    }
}
