package com.teamacronymcoders.base.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public interface IRightClickEntity {
    boolean rightClickEntity(ItemStack itemStack, Entity target, PlayerEntity entityPlayer, World entityWorld, Hand hand);
}
