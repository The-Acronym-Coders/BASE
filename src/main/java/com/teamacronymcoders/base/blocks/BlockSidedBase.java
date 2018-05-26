package com.teamacronymcoders.base.blocks;


import com.teamacronymcoders.base.Capabilities;
import com.teamacronymcoders.base.blocks.properties.PropertySideType;
import com.teamacronymcoders.base.tileentities.TileEntitySidedBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;

import javax.annotation.Nonnull;
import java.util.Optional;

public abstract class BlockSidedBase<T extends TileEntitySidedBase> extends BlockTEBase<T> {
    protected BlockSidedBase(Material material, String name) {
        super(material, name);
        setHardness(3.0F);
        setResistance(15.0F);
    }

    @Override
    @Nonnull
    public BlockStateContainer createBlockState() {
        return new ExtendedBlockState(this, new IProperty[0], new IUnlistedProperty[]{
                PropertySideType.SIDE_TYPE[0], PropertySideType.SIDE_TYPE[1], PropertySideType.SIDE_TYPE[2],
                PropertySideType.SIDE_TYPE[3], PropertySideType.SIDE_TYPE[4], PropertySideType.SIDE_TYPE[5]
        });
    }

    @Override
    @Nonnull
    public IBlockState getExtendedState(@Nonnull IBlockState state, IBlockAccess world, BlockPos pos) {
        IExtendedBlockState extendedBlockState = (IExtendedBlockState) state;

        Optional<T> optionalTileEntitySidedBase = this.getTileEntity(world, pos);
        if (optionalTileEntitySidedBase.isPresent()) {
            T tileEntitySidedBase = optionalTileEntitySidedBase.get();
            for (int i = 0; i < PropertySideType.SIDE_TYPE.length; i++) {
                extendedBlockState = extendedBlockState.withProperty(PropertySideType.SIDE_TYPE[i], tileEntitySidedBase.getSideValue(i));
            }
        }

        return extendedBlockState;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
                                    EnumFacing side, float hitX, float hitY, float hitZ) {

        Optional<T> tileEntity = this.getTileEntity(world, pos);
        if (tileEntity.isPresent()) {
            ItemStack heldItem = player.getHeldItem(hand);
            if (heldItem.hasCapability(Capabilities.TOOL, null)) {
                if (player.isSneaking()) {
                    side = side.getOpposite();
                }
                tileEntity.get().toggleSide(side.ordinal());
                return true;
            }
        }
        return false;
    }
}
