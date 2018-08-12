package com.teamacronymcoders.base.recipesystem.input;

import com.teamacronymcoders.base.recipesystem.RecipeContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;

public class BlockStateInput implements IInput {
    private final IBlockState blockState;
    private final BlockPos offset;

    public BlockStateInput(IBlockState blockState, BlockPos offset) {
        this.blockState = blockState;
        this.offset = offset;
    }

    @Override
    public boolean isMatched(RecipeContainer recipeContainer) {
        return recipeContainer.getWorld().getBlockState(recipeContainer.getPos().add(offset)) == blockState;
    }

    @Override
    public void consume(RecipeContainer recipeContainer) {
        recipeContainer.getWorld().setBlockToAir(recipeContainer.getPos().add(offset));
    }
}
