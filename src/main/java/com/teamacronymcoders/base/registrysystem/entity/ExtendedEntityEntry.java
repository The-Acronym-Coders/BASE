package com.teamacronymcoders.base.registrysystem.entity;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityEntry;

public class ExtendedEntityEntry extends EntityEntry {
    private SpawnInfo spawnInfo;
    private UpdateInfo updateInfo;

    public ExtendedEntityEntry(Class<? extends Entity> entityClass, String name) {
        super(entityClass, name);
        this.updateInfo = new UpdateInfo();
    }

    public SpawnInfo getSpawnInfo() {
        return spawnInfo;
    }

    public void setSpawnInfo(SpawnInfo spawnInfo) {
        this.spawnInfo = spawnInfo;
    }

    public UpdateInfo getUpdateInfo() {
        return updateInfo;
    }

    public void setUpdateInfo(UpdateInfo updateInfo) {
        this.updateInfo = updateInfo;
    }
}
