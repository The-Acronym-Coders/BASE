package com.teamacronymcoders.base.util.inventory;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fluids.*;

/**
 * @author BluSunrize - 03.07.2017
 */
public class IngredientFluidStack extends Ingredient {
	private final FluidStack fluid;
	ItemStack[] cachedStacks;

	public IngredientFluidStack(FluidStack fluid) {
		super(0);
		this.fluid = fluid;
	}

	public IngredientFluidStack(Fluid fluid, int amount) {
		this(new FluidStack(fluid, amount));
	}

	public FluidStack getFluid() {
		return fluid;
	}

	@Override
	public ItemStack[] getMatchingStacks() {
		if(cachedStacks == null) {
			cachedStacks = new ItemStack[] { FluidUtil.getFilledBucket(fluid) };
		}
		return cachedStacks;
	}

	@Override
	public boolean apply(@Nullable ItemStack stack) {
		if(stack == null) {
			return false;
		}
		else {
			FluidStack fs = FluidUtil.getFluidContained(stack);
			return fs == null && fluid == null || fs != null && fs.containsFluid(fluid);
		}
	}
}
