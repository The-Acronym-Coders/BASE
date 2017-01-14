package com.teamacronymcoders.base.modules.jei.registry;

import com.teamacronymcoders.base.items.IIsHidden;
import com.teamacronymcoders.base.modules.jei.BASEJEIPlugin;
import com.teamacronymcoders.base.registry.pieces.RegistryPiece;
import com.teamacronymcoders.base.registry.pieces.RegistryPieceBase;
import net.minecraft.util.ResourceLocation;

@RegistryPiece(modid = "JEI")
public class JEIBlackListRegistryPiece extends RegistryPieceBase<IIsHidden> {
    public JEIBlackListRegistryPiece() {
        super(IIsHidden.class);
    }

    @Override
    public void addEntry(ResourceLocation name, IIsHidden hidden) {
        BASEJEIPlugin.blackListItem(hidden);
    }
}
