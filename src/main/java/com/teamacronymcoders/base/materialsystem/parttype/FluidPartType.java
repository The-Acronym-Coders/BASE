package com.teamacronymcoders.base.materialsystem.parttype;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.materialsystem.blocks.BlockMaterialFluid;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.registry.BlockRegistry;
import net.minecraftforge.fluids.FluidRegistry;

import javax.annotation.Nonnull;

public class FluidPartType extends PartType {
    public FluidPartType(IBaseMod mod) {
        super("fluid", mod);
    }

    public void setup(@Nonnull MaterialPart materialPart) {
        if (!FluidRegistry.isFluidRegistered(materialPart.getMaterial().getUnlocalizedName())) {
            BlockMaterialFluid materialFluid = new BlockMaterialFluid(materialPart);
            this.getMod().getRegistryHolder().getRegistry(BlockRegistry.class, "BLOCK").register(materialFluid);
        }
    }
}
