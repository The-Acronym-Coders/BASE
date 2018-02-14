package com.teamacronymcoders.base.guisystem;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public interface IHasGui {
    @SideOnly(Side.CLIENT)
    @Nullable
    Gui getGui(EntityPlayer entityPlayer, World world, BlockPos blockPos);

    @Nullable
    Container getContainer(EntityPlayer entityPlayer, World world, BlockPos blockPos);
}
