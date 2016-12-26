package com.teamacronymcoders.base.registry.pieces;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.IModAware;
import com.teamacronymcoders.base.registry.Registry;

public class RegistryPieceBase<ENTRY> implements IRegistryPiece<ENTRY>, IModAware {
    private IBaseMod mod;

    @Override
    public boolean acceptsRegistry(Registry registry) {
        return true;
    }

    @Override
    public boolean acceptsEntry(String name, Object entry) {
        return true;
    }

    @Override
    public void preInit(String name, ENTRY entry) {}

    @Override
    public void init(String name, ENTRY entry) {}

    @Override
    public void postInit(String name, ENTRY entry) {}

    @Override
    public void addEntry(String name, ENTRY entry) {}

    @Override
    public IBaseMod getMod() {
        return mod;
    }

    @Override
    public void setMod(IBaseMod mod) {
        this.mod = mod;
    }
}
