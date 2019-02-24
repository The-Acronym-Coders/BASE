package com.teamacronymcoders.base.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Locale;

public class TextUtils {
    private TextUtils() {

    }

    public static String removeSpecialCharacters(String input) {
        return input.replace(" ", "").replace("'", "").replace(":", "_");
    }

    public static String toSnakeCase(String input) {
        input = removeSpecialCharacters(input);
        input = input.substring(0, 1).toLowerCase(Locale.US) + input.substring(1);
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (Character.isUpperCase(currentChar)) {
                output.append("_");
            }
            output.append(Character.toLowerCase(currentChar));
        }

        return output.toString();
    }

    public static String getRegistryLocation(IForgeRegistryEntry entry) {
        ResourceLocation resourceLocation = entry.getRegistryName();
        String rl = "";
        if (resourceLocation != null) {
            rl = resourceLocation.toString();
        }
        return rl;
    }

	public static String localize(String input, Object... format) {
		return I18n.translateToLocalFormatted(input, format);
	}

	public static TextComponentString representFluidStack(FluidStack stack) {
		if(stack != null) {
			return new TextComponentString(stack.getLocalizedName() + ": " + stack.amount + "mB");
	
		}
		return new TextComponentString("No fluid");
	}

	public static TextComponentString representTankContents(IFluidTank tank) {
		if(tank.getFluid() != null && tank.getFluidAmount() > 0) {
			return new TextComponentString(tank.getFluid().getLocalizedName() + ": " + tank.getFluidAmount() + "mB/"
					+ tank.getCapacity() + "mB");
		}
		return new TextComponentString("Empty");
	}

	public static TextComponentString representInventoryContents(ItemStackHandler handler) {
		TextComponentString start = new TextComponentString("Inventory: ");
		for(int i = 0; i < handler.getSlots(); i++) {
			ItemStack current = handler.getStackInSlot(i);
			if(current != ItemStack.EMPTY) {
				start.appendText(current.getCount() + " " + current.getDisplayName() + " ");
			}
		}
	
		return start;
	}
}
