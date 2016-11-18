package com.teamacronymcoders.base.guisystem;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IHasGui {
    @SideOnly(Side.CLIENT)
    Gui getGui(EntityPlayer entityPlayer, World world, NBTTagCompound context);

    Container getContainer(EntityPlayer entityPlayer, World world, NBTTagCompound context);
}
