package com.teamacronymcoders.base.registrysystem.pieces.assorted;

import com.teamacronymcoders.base.IModAware;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPiece;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPieceBase;
import net.minecraft.util.ResourceLocation;

@RegistryPiece
public class ModAwareRegistryPiece extends RegistryPieceBase<IModAware> {
    public ModAwareRegistryPiece() {
        super(IModAware.class);
    }

    @Override
    public void addEntry(ResourceLocation name, IModAware entry) {
        entry.setMod(this.getMod());
    }
}
