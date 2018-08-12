package com.teamacronymcoders.base.recipesystem.output;

import com.teamacronymcoders.base.recipesystem.RecipeContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;

import java.util.Optional;

public class BlockStateRecipeOutput implements IOutput {
    private final IBlockState blockState;
    private final BlockPos offset;

    public BlockStateRecipeOutput(IBlockState blockState, BlockPos offset) {
        this.blockState = blockState;
        this.offset = Optional.ofNullable(offset).orElse(BlockPos.ORIGIN);
    }

    @Override
    public boolean canOutput(RecipeContainer recipeContainer) {
        return true;
    }

    @Override
    public void output(RecipeContainer recipeContainer) {
        recipeContainer.getWorld().setBlockState(recipeContainer.getPos().add(offset), blockState, 3);
    }
}
