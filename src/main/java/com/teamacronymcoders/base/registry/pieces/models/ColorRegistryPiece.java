package com.teamacronymcoders.base.registry.pieces.models;

import com.teamacronymcoders.base.blocks.IHasBlockColor;
import com.teamacronymcoders.base.client.Colors;
import com.teamacronymcoders.base.items.IHasItemColor;
import com.teamacronymcoders.base.registry.pieces.RegistryPiece;
import com.teamacronymcoders.base.registry.pieces.RegistryPieceBase;
import com.teamacronymcoders.base.registry.pieces.RegistrySide;

@RegistryPiece(RegistrySide.CLIENT)
public class ColorRegistryPiece extends RegistryPieceBase {
    @Override
    public boolean acceptsRegistry(String name) {
        return "ITEM".equalsIgnoreCase(name) || "BLOCK".equalsIgnoreCase(name);
    }

    public void init(String name, Object entry) {
        if(entry instanceof IHasItemColor) {
            Colors.registerItemColor(entry, (IHasItemColor)entry);
        }
        if(entry instanceof IHasBlockColor) {
            Colors.registerBlockColor((IHasBlockColor)entry);
        }
    }
}
