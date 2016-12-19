package com.teamacronymcoders.base.registry;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.registry.pieces.IRegistryPiece;

import java.util.List;
import java.util.stream.Collectors;

public class ModularRegistry<ENTRY> extends Registry<ENTRY> {
    private String name;
    private List<IRegistryPiece> registryPieces;

    public ModularRegistry(String name, IBaseMod mod, List<IRegistryPiece> registryPieces) {
        super(mod);
        this.name = name;
        this.registryPieces = registryPieces.stream().filter(registryPiece->registryPiece.acceptsRegistry(name)).collect(Collectors.toList());
    }

    @Override
    @SuppressWarnings("unchecked") //Yes I know... it's IRegistryPiece#preInit()
    public void preInit() {
        entries.forEach((entryName, entryValue) -> registryPieces.stream()
                .filter(registryPiece -> registryPiece.acceptsEntry(entryName, entryValue))
                .forEach(registryPiece -> registryPiece.preInit(entryName, entryValue)));
    }

    @Override
    @SuppressWarnings("unchecked") //Yes I know... it's IRegistryPiece#init()
    public void init() {
        entries.forEach((entryName, entryValue) -> registryPieces.stream()
                .filter(registryPiece -> registryPiece.acceptsEntry(entryName, entryValue))
                .forEach(registryPiece -> registryPiece.init(entryName, entryValue)));
    }

    @Override
    @SuppressWarnings("unchecked") //Yes I know... it's IRegistryPiece#postInit()
    public void postInit() {
        entries.forEach((entryName, entryValue) -> registryPieces.stream()
                .filter(registryPiece -> registryPiece.acceptsEntry(entryName, entryValue))
                .forEach(registryPiece -> registryPiece.postInit(entryName, entryValue)));
    }


    public String getName() {
        return this.name;
    }
}
