package com.teamacronymcoders.base.registry.pieces.gameregistry;

import com.teamacronymcoders.base.registry.Registry;
import com.teamacronymcoders.base.registry.entity.EntityEntry;
import com.teamacronymcoders.base.registry.entity.SpawnEgg;
import com.teamacronymcoders.base.registry.entity.SpawnInfo;
import com.teamacronymcoders.base.registry.entity.UpdateInfo;
import com.teamacronymcoders.base.registry.pieces.RegistryPiece;
import com.teamacronymcoders.base.registry.pieces.RegistryPieceBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@RegistryPiece
public class EntityRegisterRegistryPiece extends RegistryPieceBase<EntityEntry> {
    public int nextAvailableID = 0;

    @Override
    public boolean acceptsRegistry(Registry registry) {
        return "ENTITY".equalsIgnoreCase(registry.getName());
    }

    @Override
    public boolean acceptsEntry(ResourceLocation name, Object entry) {
        return entry instanceof EntityEntry;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void preInit(ResourceLocation name, EntityEntry entry) {
        Class<? extends Entity> entityClass = entry.getEntityClass();
        UpdateInfo updateInfo = entry.getUpdateInfo();
        EntityRegistry.registerModEntity(entityClass, name.getResourcePath(), nextAvailableID++, this.getMod(),
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
