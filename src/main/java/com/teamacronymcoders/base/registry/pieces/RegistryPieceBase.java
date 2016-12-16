package com.teamacronymcoders.base.registry.pieces;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.IModAware;

public class RegistryPieceBase<ENTRY> implements IRegistryPiece<ENTRY>, IModAware {
    private IBaseMod mod;

    public boolean acceptsRegistry(String name) {
        return true;
    }

    public void preInit(String name, ENTRY entry) {}

    public void init(String name, ENTRY entry) {}

    public void postInit(String name, ENTRY entry) {}

    @Override
    public IBaseMod getMod() {
        return mod;
    }

    @Override
    public void setMod(IBaseMod mod) {
        this.mod = mod;
    }
}
