package com.teamacronymcoders.base.blocks;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidBase extends BlockFluidClassic {
    public BlockFluidBase(String name, Fluid fluid, Material material) {
        super(fluid, material);
        this.setUnlocalizedName(name);
    }
}
