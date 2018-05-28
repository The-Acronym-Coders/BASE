package com.teamacronymcoders.base.registrysystem.pieces;

import com.teamacronymcoders.base.registrysystem.Registry;
import net.minecraft.util.ResourceLocation;

public interface IRegistryPiece<ENTRY> {
    boolean acceptsRegistry(Registry registry);

    boolean acceptsEntry(ResourceLocation name, Object entry);

    void preInit(ResourceLocation name, ENTRY entry);

    void init(ResourceLocation name, ENTRY entry);

    void postInit(ResourceLocation name, ENTRY entry);

    void addEntry(ResourceLocation name, ENTRY entry);

    void onModelEvent(ResourceLocation name, ENTRY entry);

    void onRegistryEvent(ResourceLocation name, ENTRY entry);

    void afterRegistryEvent();
}
