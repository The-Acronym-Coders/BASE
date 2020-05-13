package com.teamacronymcoders.base.registrysystem;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.registrysystem.entity.ExtendedEntityEntry;
import com.teamacronymcoders.base.registrysystem.pieces.IRegistryPiece;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class EntityRegistry extends ModularRegistry<ExtendedEntityEntry> {
    public EntityRegistry(IBaseMod mod, List<IRegistryPiece> registryPieces) {
        super("ENTITY", mod, registryPieces);
    }
    
    public EntityRegistry(IBaseMod mod, List<IRegistryPiece> registryPieces, String altNamespace) {
        super("ENTITY", mod, registryPieces);
    }

    public void register(Class<? extends Entity> entityClass) {
        String namespace = (altNamespace != null) ? altNamespace : mod.getID();
        ResourceLocation name = new ResourceLocation(namespace, entityClass.getSimpleName());
        ExtendedEntityEntry extendedEntityEntry = new ExtendedEntityEntry(entityClass, name.toString());
        register(name, extendedEntityEntry);
    }
}
