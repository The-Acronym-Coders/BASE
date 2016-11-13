package com.teamacronymcoders.base.blocks;

import com.teamacronymcoders.base.client.models.IHasModel;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import java.util.List;

public class BlockFluidBase extends BlockFluidClassic implements IHasModel {
    public BlockFluidBase(String name, Fluid fluid, Material material) {
        super(fluid, material);
        this.setUnlocalizedName(name);
    }

    @Override
    public List<String> getModelNames(List<String> modelNames) {
        String name = this.getUnlocalizedName();
        if(name.startsWith("tile.")) {
            name = name.substring(5);
        }
        modelNames.add(name);
        return modelNames;
    }
}
