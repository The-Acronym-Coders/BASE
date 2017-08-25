package com.teamacronymcoders.base.registrysystem;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.registrysystem.entity.SpawnInfo;
import com.teamacronymcoders.base.registrysystem.entity.UpdateInfo;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

public class RegistryEventHandler {
    private IBaseMod mod;
    private int nextAvailableID;

    public RegistryEventHandler(IBaseMod mod) {
        this.mod = mod;
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> blockRegistryEvent) {
        BlockRegistry blockRegistry = this.mod.getRegistryHolder().getRegistry(BlockRegistry.class, "BLOCK");
        blockRegistry.getEntries().forEach((name, block) ->
                blockRegistryEvent.getRegistry().register(block.setRegistryName(name)));
        blockRegistry.registryEvent();
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> itemRegistryEvent) {
        ItemRegistry itemRegistry = this.mod.getRegistryHolder().getRegistry(ItemRegistry.class, "ITEM");
        itemRegistry.getEntries().forEach((name, item) ->
                itemRegistryEvent.getRegistry().register(item.setRegistryName(name)));
        itemRegistry.registryEvent();
    }

    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void registerEntities(RegistryEvent.Register<EntityEntry> entityRegistryEvent) {
        EntityRegistry entityRegistry = this.mod.getRegistryHolder().getRegistry(EntityRegistry.class, "ENTITY");
        entityRegistry.getEntries().forEach(
                (name, entityEntry) -> {
                    Class<? extends Entity> entityClass = entityEntry.getEntityClass();
                    UpdateInfo updateInfo = entityEntry.getUpdateInfo();
                    net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(name, entityClass, name.getResourcePath(), nextAvailableID++, this.mod,
                            updateInfo.getTrackingRange(), updateInfo.getUpdateFrequency(), updateInfo.isSendVelocityUpdates());

                    EntityList.EntityEggInfo spawnEgg = entityEntry.getEgg();
                    if (spawnEgg != null) {
                        net.minecraftforge.fml.common.registry.EntityRegistry.registerEgg(name, spawnEgg.primaryColor, spawnEgg.secondaryColor);
                    }

                    SpawnInfo spawnInfo = entityEntry.getSpawnInfo();
                    if (spawnInfo != null) {
                        if (EntityLiving.class.isAssignableFrom(entityClass)) {
                            net.minecraftforge.fml.common.registry.EntityRegistry.addSpawn((Class<? extends EntityLiving>) entityClass, spawnInfo.getWeighted(),
                                    spawnInfo.getMinimum(), spawnInfo.getMaximum(), spawnInfo.getCreatureType(), spawnInfo.getSpawnBiomes());
                        }

                    }
                    //entityRegistryEvent.getRegistry().register(entityEntry.setRegistryName(name));
                });
        entityRegistry.registryEvent();
    }
}
