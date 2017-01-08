package com.teamacronymcoders.base.registry.pieces.assorted;

import com.teamacronymcoders.base.IModAware;
import com.teamacronymcoders.base.registry.pieces.RegistryPiece;
import com.teamacronymcoders.base.registry.pieces.RegistryPieceBase;
import net.minecraft.util.ResourceLocation;

@RegistryPiece
public class ModAwareRegistryPiece extends RegistryPieceBase<IModAware> {
    @Override
    public boolean acceptsEntry(ResourceLocation name, Object entry) {
        return entry instanceof IModAware;
    }

    @Override
    public void addEntry(ResourceLocation name, IModAware entry) {
        entry.setMod(this.getMod());
    }
}
