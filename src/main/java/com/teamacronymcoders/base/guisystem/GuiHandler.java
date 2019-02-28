package com.teamacronymcoders.base.guisystem;

import com.teamacronymcoders.base.IBaseMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.network.NetworkRegistry;

import java.util.Optional;

public class GuiHandler implements IGuiHandler {
    public GuiHandler(IBaseMod instance) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, this);
    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos blockPos = new BlockPos(x, y, z);
        return this.getHasGUI(id, player, world, blockPos)
                .map(openableGUI -> openableGUI.getContainer(player, world, blockPos))
                .orElse(null);
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, final int z) {
        BlockPos blockPos = new BlockPos(x, y, z);
        return this.getHasGUI(id, player, world, blockPos)
                .map(openableGUI -> openableGUI.getGui(player, world, blockPos))
                .orElse(null);
    }

    private Optional<IHasGui> getHasGUI(int id, EntityPlayer player, World world, BlockPos blockPos) {
        return Optional.ofNullable(GuiCarrier.values()[id].getHasGUI(player, world, blockPos));
    }
}
