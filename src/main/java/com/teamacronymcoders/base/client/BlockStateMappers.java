package com.teamacronymcoders.base.client;

import com.teamacronymcoders.base.blocks.IHasBlockStateMapper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nonnull;

public class BlockStateMappers {
    public static void registerStateMapper(Block block, IHasBlockStateMapper stateMapper) {
        ModelLoader.setCustomStateMapper(block, new StateMapperBase() {
            @Override
            @Nonnull
            protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
                return new ModelResourceLocation(stateMapper.getResourceLocation(state), stateMapper.getVariant(state));
            }
        });
    }
}
