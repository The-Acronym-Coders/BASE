package com.teamacronymcoders.base.registry.pieces;

public interface IRegistryPiece<ENTRY> {
    boolean acceptsRegistry(String name);

    void preInit(String name, ENTRY entry);

    void init(String name, ENTRY entry);

    void postInit(String name, ENTRY entry);
}
