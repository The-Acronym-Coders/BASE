package com.teamacronymcoders.base.registry.pieces;

import com.teamacronymcoders.base.registry.Registry;

public interface IRegistryPiece<ENTRY> {
    boolean acceptsRegistry(Registry registry);

    boolean acceptsEntry(String name, Object entry);

    void preInit(String name, ENTRY entry);

    void init(String name, ENTRY entry);

    void postInit(String name, ENTRY entry);

    void addEntry(String name, ENTRY entry);
}
