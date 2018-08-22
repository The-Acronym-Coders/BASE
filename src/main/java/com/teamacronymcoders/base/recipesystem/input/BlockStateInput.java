package com.teamacronymcoders.base.recipesystem.input;

import com.google.gson.annotations.JsonAdapter;
import com.teamacronymcoders.base.blocks.BlockStateMatcher;
import com.teamacronymcoders.base.json.deserializer.BlockPosDeserializer;
import com.teamacronymcoders.base.json.deserializer.BlockStateDeserializer;
import com.teamacronymcoders.base.json.deserializer.BlockStateMatcherDeserializer;
import com.teamacronymcoders.base.recipesystem.RecipeContainer;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;

import java.util.Optional;

public class BlockStateInput implements IInput {
    @JsonAdapter(value = BlockStateMatcherDeserializer.class)
    private final BlockStateMatcher blockState;
    @JsonAdapter(value = BlockPosDeserializer.class)
    private final BlockPos offset;

    public BlockStateInput(BlockStateMatcher blockStateMatcher, BlockPos offset) {
        this.blockState = blockStateMatcher;
        this.offset = offset;
    }

    @Override
    public boolean isMatched(RecipeContainer recipeContainer) {
        return blockState.matches(recipeContainer.getWorld().getBlockState(handlePos(recipeContainer)));
    }

    @Override
    public void consume(RecipeContainer recipeContainer) {
        recipeContainer.getWorld().setBlockToAir(handlePos(recipeContainer));
    }

    private BlockPos handlePos(RecipeContainer recipeContainer) {
        return offset != null ? recipeContainer.getPos().add(offset) : recipeContainer.getPos();
    }
}
