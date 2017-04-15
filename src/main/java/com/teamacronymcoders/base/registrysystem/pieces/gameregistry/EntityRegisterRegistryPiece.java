package com.teamacronymcoders.base.registrysystem.pieces.gameregistry;

import com.teamacronymcoders.base.registrysystem.Registry;
import com.teamacronymcoders.base.registrysystem.entity.EntityEntry;
import com.teamacronymcoders.base.registrysystem.entity.SpawnEgg;
import com.teamacronymcoders.base.registrysystem.entity.SpawnInfo;
import com.teamacronymcoders.base.registrysystem.entity.UpdateInfo;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPiece;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPieceBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import static net.minecraftforge.fml.common.eventhandler.EventPriority.HIGH;

@RegistryPiece(priority = HIGH)
public class EntityRegisterRegistryPiece extends RegistryPieceBase<EntityEntry> {
    public int nextAvailableID = 0;

    public EntityRegisterRegistryPiece() {
        super(EntityEntry.class);
    }

    @Override
    public boolean acceptsRegistry(Registry registry) {
        return "ENTITY".equalsIgnoreCase(registry.getName());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void preInit(ResourceLocation name, EntityEntry entry) {
        Class<? extends Entity> entityClass = entry.getEntityClass();
        UpdateInfo updateInfo = entry.getUpdateInfo();
        EntityRegistry.registerModEntity(name, entityClass, name.getResourcePath(), nextAvailableID++, this.getMod(),
                updateInfo.getTrackingRange(), updateInfo.getUpdateFrequency(), updateInfo.isSendVelocityUpdates());

        SpawnEgg spawnEgg = entry.getSpawnEgg();
        if (spawnEgg != null) {
            EntityRegistry.registerEgg(name, spawnEgg.getPrimaryColor(), spawnEgg.getSecondaryColor());
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
