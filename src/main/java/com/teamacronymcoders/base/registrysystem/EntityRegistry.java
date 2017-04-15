package com.teamacronymcoders.base.registrysystem;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.registrysystem.entity.EntityEntry;
import com.teamacronymcoders.base.registrysystem.pieces.IRegistryPiece;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class EntityRegistry extends ModularRegistry<EntityEntry> {
    public EntityRegistry(IBaseMod mod, List<IRegistryPiece> registryPieces) {
        super("ENTITY", mod, registryPieces);
    }

    public void register(Class<? extends Entity> entityClass) {
        EntityEntry entityEntry = new EntityEntry(entityClass);
        ResourceLocation name = new ResourceLocation(this.mod.getName(), entityClass.getName());
        register(name, entityEntry);
    }
}
