package com.teamacronymcoders.base.client;

import com.teamacronymcoders.base.blocks.IHasBlockStateMapper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class BlockStateMappers {
    public static void registerStateMapper(IHasBlockStateMapper stateMapper) {
        ModelLoader.setCustomStateMapper(stateMapper.getBlock(), new StateMapperBase() {
            @Override
            @Nonnull
            protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
                return new ModelResourceLocation(stateMapper.getResourceLocation(state), stateMapper.getVariant(state));
            }
        });
    }
}
