package com.teamacronymcoders.base.materialsystem.parttype;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.materialsystem.blocks.BlockMaterialFluid;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.registrysystem.BlockRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;

import javax.annotation.Nonnull;
import java.util.List;

public class FluidPartType extends PartType {
    public FluidPartType() {
        super("fluid", createFluidDataPieces());
    }

    private static List<PartDataPiece> createFluidDataPieces() {
        List<PartDataPiece> fluidDataPieces = Lists.newArrayList();
        fluidDataPieces.add(new PartDataPiece("temperature"));
        fluidDataPieces.add(new PartDataPiece("density"));
        fluidDataPieces.add(new PartDataPiece("viscosity"));
        fluidDataPieces.add(new PartDataPiece("vaporize"));
        return fluidDataPieces;
    }

    public void setup(@Nonnull MaterialPart materialPart) {
        if (!FluidRegistry.isFluidRegistered(materialPart.getMaterial().getUnlocalizedName())) {
            BlockMaterialFluid materialFluid = new BlockMaterialFluid(materialPart);
            materialPart.getMaterialUser().getMod().getRegistryHolder().getRegistry(BlockRegistry.class, "BLOCK").register(materialFluid);
        }
    }

    @Override
    public ItemStack getItemStack(MaterialPart materialPart) {
        FluidStack fluidStack = FluidRegistry.getFluidStack(materialPart.getMaterial().getUnlocalizedName(), 1000);
        ItemStack filledBucket = ItemStack.EMPTY;
        if (fluidStack != null) {
            filledBucket = FluidUtil.getFilledBucket(fluidStack);
        }
        return filledBucket;
    }
}
