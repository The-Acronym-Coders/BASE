package com.teamacronymcoders.base.registry.entity;

import net.minecraft.entity.Entity;

public class EntityEntry {
    private Class<? extends Entity> entityClass;
    private SpawnEgg spawnEgg;
    private SpawnInfo spawnInfo;
    private UpdateInfo updateInfo;

    public EntityEntry(Class<? extends Entity> entityClass) {
        this.entityClass = entityClass;
        this.updateInfo = new UpdateInfo();
    }

    public Class<? extends Entity> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<? extends Entity> entityClass) {
        this.entityClass = entityClass;
    }

    public SpawnEgg getSpawnEgg() {
        return spawnEgg;
    }

    public void setSpawnEgg(SpawnEgg spawnEgg) {
        this.spawnEgg = spawnEgg;
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
