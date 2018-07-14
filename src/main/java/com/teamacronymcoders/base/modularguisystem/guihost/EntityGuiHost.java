package com.teamacronymcoders.base.modularguisystem.guihost;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import java.lang.ref.WeakReference;

public class EntityGuiHost implements IModularGuiHost {
    private final WeakReference<Entity> entityWeakReference;

    public EntityGuiHost(Entity entity) {
        entityWeakReference = new WeakReference<>(entity);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        Entity entity = entityWeakReference.get();
        if (entity != null) {
            return !entity.isDead && player.getDistanceSq(entity) <= 64.0D;
        }
        return false;
    }
}
