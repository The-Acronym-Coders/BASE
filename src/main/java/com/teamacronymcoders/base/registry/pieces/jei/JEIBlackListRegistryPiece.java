package com.teamacronymcoders.base.registry.pieces.jei;

import com.teamacronymcoders.base.items.IIsHidden;
import com.teamacronymcoders.base.registry.pieces.RegistryPiece;
import com.teamacronymcoders.base.registry.pieces.RegistryPieceBase;
import net.minecraft.util.ResourceLocation;

@RegistryPiece(modid = "jei")
public class JEIBlackListRegistryPiece extends RegistryPieceBase<IIsHidden> {
    public JEIBlackListRegistryPiece() {
        super(IIsHidden.class);
    }

    @Override
    public void init(ResourceLocation name, IIsHidden hidden) {
        BASEJEIPlugin.blackListItem(hidden);
    }
}
