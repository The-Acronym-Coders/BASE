package com.teamacronymcoders.base.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public interface IRightClickEntity {
    boolean rightClickEntity(ItemStack itemStack, Entity target, EntityPlayer entityPlayer, World entityWorld, EnumHand hand);
}
