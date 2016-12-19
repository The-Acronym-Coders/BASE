package com.teamacronymcoders.base.registry.pieces.gameregistry;

import com.teamacronymcoders.base.registry.entity.EntityEntry;
import com.teamacronymcoders.base.registry.entity.SpawnEgg;
import com.teamacronymcoders.base.registry.entity.SpawnInfo;
import com.teamacronymcoders.base.registry.entity.UpdateInfo;
import com.teamacronymcoders.base.registry.pieces.RegistryPiece;
import com.teamacronymcoders.base.registry.pieces.RegistryPieceBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@RegistryPiece
public class EntityRegisterRegistryPiece extends RegistryPieceBase<EntityEntry> {
    public int nextAvailableID = 0;

    @Override
    public boolean acceptsRegistry(String name) {
        return "ENTITY".equalsIgnoreCase(name);
    }

    @Override
    public boolean acceptsEntry(String name, Object entry) {
        return entry instanceof EntityEntry;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void preInit(String name, EntityEntry entry) {
        Class<Entity> entityClass = entry.getEntityClass();
        UpdateInfo updateInfo = entry.getUpdateInfo();
        EntityRegistry.registerModEntity(entityClass, name, nextAvailableID++, this.getMod(),
                updateInfo.getTrackingRange(), updateInfo.getUpdateFrequency(), updateInfo.isSendVelocityUpdates());

        SpawnEgg spawnEgg = entry.getSpawnEgg();
        if(spawnEgg != null) {
            EntityRegistry.registerEgg(entry.getEntityClass(), spawnEgg.getPrimaryColor(), spawnEgg.getSecondaryColor());
        }

        SpawnInfo spawnInfo = entry.getSpawnInfo();
        if (spawnInfo != null) {
            if (EntityLiving.class.isAssignableFrom(entityClass)) {
                EntityRegistry.addSpawn((Class<? extends EntityLiving>) entityClass, spawnInfo.getWeighted(),
                        spawnInfo.getMinimum(), spawnInfo.getMaximum(), spawnInfo.getCreatureType(), spawnInfo.getSpawnBiomes());
            }

        }
    }
}
