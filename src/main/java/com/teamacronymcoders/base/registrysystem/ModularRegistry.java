package com.teamacronymcoders.base.registrysystem;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.registrysystem.pieces.IRegistryPiece;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPieceComparator;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.stream.Collectors;

public class ModularRegistry<ENTRY> extends Registry<ENTRY> {
    private List<IRegistryPiece> registryPieces;

    public ModularRegistry(String name, IBaseMod mod, List<IRegistryPiece> registryPieces) {
        super(name, mod);
        this.registryPieces = registryPieces.stream().filter(registryPiece -> registryPiece.acceptsRegistry(this))
                .collect(Collectors.toList());
        this.registryPieces.sort(new RegistryPieceComparator());
    }

    @Override
    @SuppressWarnings("unchecked") //Yes I know... it's IRegistryPiece#preInit()
    public void preInit() {
        super.preInit();
        entries.forEach((entryName, entryValue) -> registryPieces.stream()
                .filter(registryPiece -> registryPiece.acceptsEntry(entryName, entryValue))
                .forEach(registryPiece -> registryPiece.preInit(entryName, entryValue)));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void registryEvent() {
        super.registryEvent();
        entries.forEach((entryName, entryValue) -> registryPieces.stream()
                .filter(registryPiece -> registryPiece.acceptsEntry(entryName, entryValue))
                .forEach(registryPiece -> registryPiece.onRegistryEvent(entryName, entryValue)));
    }

    @Override
    @SuppressWarnings("unchecked") //Yes I know... it's IRegistryPiece#init()
    public void init() {
        super.init();
        entries.forEach((entryName, entryValue) -> registryPieces.stream()
                .filter(registryPiece -> registryPiece.acceptsEntry(entryName, entryValue))
                .forEach(registryPiece -> registryPiece.init(entryName, entryValue)));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onModelRun() {
        super.onModelRun();
        entries.forEach((entryName, entryValue) -> registryPieces.stream()
                .filter(registryPiece -> registryPiece.acceptsEntry(entryName, entryValue))
                .forEach(registryPiece -> registryPiece.onModelEvent(entryName, entryValue)));

    }

    @Override
    @SuppressWarnings("unchecked") //Yes I know... it's IRegistryPiece#postInit()
    public void postInit() {
        super.postInit();
        entries.forEach((entryName, entryValue) -> registryPieces.stream()
                .filter(registryPiece -> registryPiece.acceptsEntry(entryName, entryValue))
                .forEach(registryPiece -> registryPiece.postInit(entryName, entryValue)));
    }

    @Override
    @SuppressWarnings("unchecked") //Yes I know... it's IRegistryPiece#addEntry()
    public void register(ResourceLocation name, ENTRY entry) {
        super.register(name, entry);
        registryPieces.stream()
                .filter(registryPiece -> registryPiece.acceptsEntry(name, entry))
                .forEach(registryPiece -> registryPiece.addEntry(name, entry));
    }
}
