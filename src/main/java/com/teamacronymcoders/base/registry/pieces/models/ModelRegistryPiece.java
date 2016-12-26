package com.teamacronymcoders.base.registry.pieces.models;

import com.teamacronymcoders.base.client.Models;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.registry.Registry;
import com.teamacronymcoders.base.registry.pieces.RegistryPiece;
import com.teamacronymcoders.base.registry.pieces.RegistryPieceBase;
import com.teamacronymcoders.base.registry.pieces.RegistrySide;

@RegistryPiece(RegistrySide.CLIENT)
public class ModelRegistryPiece extends RegistryPieceBase {
    @Override
    public boolean acceptsRegistry(Registry registry) {
        return "ITEM".equalsIgnoreCase(registry.getName()) || "BLOCK".equalsIgnoreCase(registry.getName());
    }

    public void init(String name, Object entry) {
        if(entry instanceof IHasModel) {
            Models.registerModels((IHasModel) entry);
        }
    }
}
