package com.teamacronymcoders.base.blocks;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;

import java.util.List;
import java.util.Map;

public class BlockStateMatcher {
    private final Block block;
    private final Map<IProperty<?>, List<?>> allowedValues;

    public BlockStateMatcher(Block block, Map<IProperty<?>, List<?>> allowedValues) {
        this.block = block;
        this.allowedValues = allowedValues;
    }

    public BlockStateMatcher(Block block) {
        this(block, Maps.newHashMap());
    }

    public boolean matches(IBlockState blockState) {
        if (block == blockState.getBlock()) {
            for (IProperty<?> property: blockState.getPropertyKeys()) {
                List<?> allowedValue = allowedValues.get(property);
                if (allowedValue != null) {
                    if (!allowedValue.contains(blockState.getValue(property))) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
