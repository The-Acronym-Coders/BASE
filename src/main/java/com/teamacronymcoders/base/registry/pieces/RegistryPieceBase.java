package com.teamacronymcoders.base.registry.pieces;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.IModAware;
import com.teamacronymcoders.base.registry.Registry;
import net.minecraft.util.ResourceLocation;

public class RegistryPieceBase<ENTRY> implements IRegistryPiece<ENTRY>, IModAware {
    private IBaseMod mod;
    private Class<ENTRY> entryClass;

    public RegistryPieceBase() {
        this(null);
    }

    public RegistryPieceBase(Class<ENTRY> entryClass) {
        this.entryClass = entryClass;
    }

    @Override
    public boolean acceptsRegistry(Registry registry) {
        return true;
    }

    @Override
    public boolean acceptsEntry(ResourceLocation name, Object entry) {
        return entryClass == null || entry.getClass().isAssignableFrom(entryClass);
    }

    @Override
    public void preInit(ResourceLocation name, ENTRY entry) {}

    @Override
    public void init(ResourceLocation name, ENTRY entry) {}

    @Override
    public void postInit(ResourceLocation name, ENTRY entry) {}

    @Override
    public void addEntry(ResourceLocation name, ENTRY entry) {}

    @Override
    public IBaseMod getMod() {
        return mod;
    }

    @Override
    public void setMod(IBaseMod mod) {
        this.mod = mod;
    }
}
