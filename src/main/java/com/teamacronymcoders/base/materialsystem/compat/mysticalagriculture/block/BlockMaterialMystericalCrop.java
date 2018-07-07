package com.teamacronymcoders.base.materialsystem.compat.mysticalagriculture.block;

import com.blakebr0.mysticalagriculture.blocks.crop.BlockMysticalCrop;
import com.teamacronymcoders.base.blocks.IHasBlockColor;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;

public class BlockMaterialMystericalCrop extends BlockMysticalCrop implements IHasBlockColor {
    private final MaterialPart materialPart;

    public BlockMaterialMystericalCrop(MaterialPart materialPart) {
        super(materialPart.getUnlocalizedName() + "_crop");
        this.materialPart = materialPart;
    }

    @Override
    public int colorMultiplier(IBlockState state, @Nullable IBlockAccess world, @Nullable BlockPos pos, int tintIndex) {
        return materialPart.getColor();
    }

    @Override
    public Block getBlock() {
        return this;
    }
}
