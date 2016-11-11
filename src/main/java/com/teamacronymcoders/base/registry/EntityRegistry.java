package com.teamacronymcoders.base.registry;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.entity.SpawnEgg;
import com.teamacronymcoders.base.entity.SpawnInfo;
import com.teamacronymcoders.base.util.ReflectionUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

import java.util.HashMap;

public class EntityRegistry extends Registry<Class<? extends Entity>> {
    private HashMap<String, SpawnEgg> spawnEggs = new HashMap<>();
    private HashMap<String, SpawnInfo> spawnInfos = new HashMap<>();
    private int nextAvailableID = 0;

    public EntityRegistry(IBaseMod mod) {
        super(mod);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void initiateEntry(String name, Class<? extends Entity> entityClass) {
        net.minecraftforge.fml.common.registry.EntityRegistry
                .registerModEntity(entityClass, name, ++nextAvailableID, mod, 64, 1, true);
        ReflectionUtils.setStaticField(entityClass, "mod", mod);
        if (spawnEggs.containsKey(name)) {
            SpawnEgg spawnEgg = spawnEggs.get(name);
            net.minecraftforge.fml.common.registry.EntityRegistry
                    .registerEgg(entityClass, spawnEgg.getPrimaryColor(), spawnEgg.getSecondaryColor());
        }

        if (spawnInfos.containsKey(name)) {
            SpawnInfo spawnInfo = spawnInfos.get(name);
            if (EntityLiving.class.isAssignableFrom(entityClass)) {
                net.minecraftforge.fml.common.registry.EntityRegistry
                        .addSpawn((Class<? extends EntityLiving>) entityClass, spawnInfo.getWeighted(), spawnInfo.getMinimum(), spawnInfo.getMaximum(), spawnInfo.getCreatureType(), spawnInfo.getSpawnBiomes());
            }

        }
        super.initiateEntry(name, entityClass);
    }

    public void register(Class<? extends Entity> entityClass) {
        register(entityClass.getSimpleName().toLowerCase(), entityClass);
    }

    public void addSpawnEgg(String name, SpawnEgg spawnEgg) {
        this.spawnEggs.put(name, spawnEgg);
    }

    public void addSpawnInfo(String name, SpawnInfo spawnInfo) {
        this.spawnInfos.put(name, spawnInfo);
    }
}
