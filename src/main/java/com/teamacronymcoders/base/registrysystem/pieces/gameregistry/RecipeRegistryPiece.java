package com.teamacronymcoders.base.registrysystem.pieces.gameregistry;

import com.teamacronymcoders.base.items.IHasRecipe;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPiece;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPieceBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

import static net.minecraftforge.fml.common.eventhandler.EventPriority.LOW;

@RegistryPiece(priority = LOW)
public class RecipeRegistryPiece extends RegistryPieceBase<IHasRecipe> {
    public RecipeRegistryPiece() {
        super(IHasRecipe.class);
    }

    @Override
    public void init(ResourceLocation name, IHasRecipe entry) {
        entry.getRecipes(new ArrayList<>()).forEach(GameRegistry::addRecipe);
    }
}
