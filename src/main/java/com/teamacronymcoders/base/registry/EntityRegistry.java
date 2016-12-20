package com.teamacronymcoders.base.registry;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.registry.entity.EntityEntry;
import com.teamacronymcoders.base.registry.pieces.IRegistryPiece;
import net.minecraft.entity.Entity;

import java.util.List;

public class EntityRegistry extends ModularRegistry<EntityEntry> {
    public EntityRegistry(IBaseMod mod, List<IRegistryPiece> registryPieces) {
        super("ENTITY", mod, registryPieces);
    }

    public void register(Class<? extends Entity> entityClass) {
        EntityEntry entityEntry = new EntityEntry(entityClass);
        register(entityClass.getName(), entityEntry);
    }
}
