package com.teamacronymcoders.base.guisystem.target;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.guisystem.IHasGui;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GuiBlockTarget extends GuiTarget<Block> {
    private BlockPos pos;

    public GuiBlockTarget() {
        super();
    }

    public GuiBlockTarget(@Nonnull Block block, @Nonnull BlockPos pos) {
        super(block);
        this.pos = pos;
    }

    @Override
    @Nonnull
    public ResourceLocation getTargetType() {
        return new ResourceLocation(Base.instance.getID(), "block");
    }

    @Override
    @Nullable
    public IHasGui deserialize(@Nonnull EntityPlayer entityPlayer, @Nonnull World world, @Nonnull NBTTagCompound nbtTagCompound) {
        IHasGui hasGui = null;
        Long posLong = nbtTagCompound.getLong("blockPos");
        BlockPos pos = BlockPos.fromLong(posLong);
        Block block = world.getBlockState(pos).getBlock();
        if (block instanceof IHasGui) {
            hasGui = (IHasGui) block;
        } else {
            TileEntity tileEntity = world.getTileEntity(pos);
            if (tileEntity instanceof IHasGui) {
                hasGui = (IHasGui) tileEntity;
            }
        }
        return hasGui;
    }

    @Override
    @Nonnull
    public NBTTagCompound serialize(@Nonnull NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setLong("blockPos", this.pos.toLong());
        return nbtTagCompound;
    }
}
