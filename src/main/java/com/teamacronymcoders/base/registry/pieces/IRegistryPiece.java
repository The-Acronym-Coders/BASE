package com.teamacronymcoders.base.registry.pieces;

import com.teamacronymcoders.base.registry.Registry;
import net.minecraft.util.ResourceLocation;

public interface IRegistryPiece<ENTRY> {
    boolean acceptsRegistry(Registry registry);

    boolean acceptsEntry(ResourceLocation name, Object entry);

    void preInit(ResourceLocation name, ENTRY entry);

    void init(ResourceLocation name, ENTRY entry);

    void postInit(ResourceLocation name, ENTRY entry);

    void addEntry(ResourceLocation name, ENTRY entry);
}
