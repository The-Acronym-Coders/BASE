package com.teamacronymcoders.base.recipesystem.output;

import com.google.gson.annotations.JsonAdapter;
import com.teamacronymcoders.base.json.deserializer.BlockPosDeserializer;
import com.teamacronymcoders.base.json.deserializer.BlockStateDeserializer;
import com.teamacronymcoders.base.recipesystem.RecipeContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;

import java.util.Optional;

public class BlockStateOutput implements IOutput {
    @JsonAdapter(value = BlockStateDeserializer.class)
    private final IBlockState blockState;
    @JsonAdapter(value = BlockPosDeserializer.class)
    private final BlockPos offset;

    public BlockStateOutput(IBlockState blockState, BlockPos offset) {
        this.blockState = blockState;
        this.offset = Optional.ofNullable(offset).orElse(BlockPos.ORIGIN);
    }

    @Override
    public boolean canOutput(RecipeContainer recipeContainer) {
        return true;
    }

    @Override
    public void output(RecipeContainer recipeContainer) {
        recipeContainer.getWorld().setBlockState(recipeContainer.getPos()
                .add(Optional.ofNullable(offset).orElse(BlockPos.ORIGIN)), blockState, 3);
    }
}
