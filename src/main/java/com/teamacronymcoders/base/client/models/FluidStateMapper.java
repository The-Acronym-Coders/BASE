package com.teamacronymcoders.base.client.models;

import com.teamacronymcoders.base.IBaseMod;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

import javax.annotation.Nonnull;

public class FluidStateMapper extends StateMapperBase implements ItemMeshDefinition {

    public final Fluid fluid;
    public final ModelResourceLocation location;

    public FluidStateMapper(IBaseMod mod, Fluid fluid) {
        this(mod, fluid, fluid.getName());
    }

    public FluidStateMapper(IBaseMod mod, Fluid fluid, String name) {
        this.fluid = fluid;
        location = new ModelResourceLocation(mod.getPrefix() + "fluids", name);
    }

    @Override
    @Nonnull
    protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
        return location;
    }

    @Override
    @Nonnull
    public ModelResourceLocation getModelLocation(@Nonnull ItemStack stack) {
        return location;
    }
}
