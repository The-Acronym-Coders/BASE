package com.teamacronymcoders.base.registry.pieces.models;

import com.teamacronymcoders.base.client.Models;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.registry.Registry;
import com.teamacronymcoders.base.registry.pieces.RegistryPiece;
import com.teamacronymcoders.base.registry.pieces.RegistryPieceBase;
import com.teamacronymcoders.base.registry.pieces.RegistrySide;
import net.minecraft.util.ResourceLocation;

@RegistryPiece(RegistrySide.CLIENT)
public class ModelRegistryPiece extends RegistryPieceBase<IHasModel> {
    public ModelRegistryPiece() {
        super(IHasModel.class);
    }

    @Override
    public boolean acceptsRegistry(Registry registry) {
        return "ITEM".equalsIgnoreCase(registry.getName()) || "BLOCK".equalsIgnoreCase(registry.getName());
    }

    @Override
    public void preInit(ResourceLocation name, IHasModel entry) {
        Models.registerModels(entry);
    }
}
