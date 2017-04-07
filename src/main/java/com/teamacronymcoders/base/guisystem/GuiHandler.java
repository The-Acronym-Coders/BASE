package com.teamacronymcoders.base.guisystem;

import com.teamacronymcoders.base.IBaseMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler {
    public GuiHandler(IBaseMod instance) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, this);
    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos blockPos = new BlockPos(x, y, z);
        IHasGui openableGUI = this.getHasGUI(id, player, world, blockPos);
        return openableGUI != null ? openableGUI.getContainer(player, world, blockPos) : null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, final int z) {
        BlockPos blockPos = new BlockPos(x, y, z);
        IHasGui openableGUI = this.getHasGUI(id, player, world, blockPos);
        return openableGUI != null ? openableGUI.getGui(player, world, blockPos) : null;
    }

    private IHasGui getHasGUI(int id, EntityPlayer player, World world, BlockPos blockPos) {
        return GuiCarrier.values()[id].getHasGUI(player, world, blockPos);
    }
}
