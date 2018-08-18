package com.teamacronymcoders.base.recipesystem.input;

import com.google.gson.annotations.JsonAdapter;
import com.teamacronymcoders.base.json.deserializer.BlockPosDeserializer;
import com.teamacronymcoders.base.json.deserializer.BlockStateDeserializer;
import com.teamacronymcoders.base.recipesystem.RecipeContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;

public class BlockStateInput implements IInput {
    @JsonAdapter(value = BlockStateDeserializer.class, nullSafe = false)
    private final IBlockState blockState;
    @JsonAdapter(value = BlockPosDeserializer.class, nullSafe = false)
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
