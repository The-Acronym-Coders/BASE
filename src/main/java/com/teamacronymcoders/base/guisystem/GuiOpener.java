package com.teamacronymcoders.base.guisystem;

import com.teamacronymcoders.base.IBaseMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.teamacronymcoders.base.guisystem.GuiCarrier.*;

public class GuiOpener {
    public static void openEntityGui(IBaseMod mod, Entity entity, EntityPlayer player, World world) {
        player.openGui(mod, ENTITY.ordinal(), world, entity.getEntityId(), 0, 0);
    }

    public static void openBlockGui(IBaseMod mod, EntityPlayer player, World world, BlockPos blockPos) {
        player.openGui(mod, BLOCK.ordinal(), world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public static void openTileEntityGui(IBaseMod mod, EntityPlayer player, World world, BlockPos blockPos) {
        player.openGui(mod, TileEntity.ordinal(), world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public static void openItemGui(IBaseMod mod, EntityPlayer player, World world, EnumHand enumHand) {
        player.openGui(mod, ITEMSTACK.ordinal(), world, enumHand.ordinal(), 0, 0);
    }
}
