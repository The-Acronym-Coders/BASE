package com.teamacronymcoders.base.client.gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IHasGui {
    Gui getClientGuiElement(int id, EntityPlayer player, World world, BlockPos blockPos);

    Container getServerGuiElement(int id, EntityPlayer player, World world, BlockPos blockPos);
}
