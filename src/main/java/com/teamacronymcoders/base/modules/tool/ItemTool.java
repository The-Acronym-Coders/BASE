package com.teamacronymcoders.base.modules.tool;

import java.util.List;

import com.teamacronymcoders.base.api.BaseAPI;
import com.teamacronymcoders.base.api.ITool;
import com.teamacronymcoders.base.api.ToolImpl;
import com.teamacronymcoders.base.items.IHasRecipe;
import com.teamacronymcoders.base.items.ItemBase;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ItemTool extends ItemBase implements IHasRecipe {

	public ItemTool() {
		super("wrench");
		this.setCreativeTab(CreativeTabs.TOOLS);
		this.setMaxStackSize(1);
	}

	@Override
	public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack,
			NBTTagCompound nbt) {
		return new CapabilityProvider(stack);
	}

	public static class CapabilityProvider implements ICapabilityProvider {
		private final ItemStack stack;
		private ITool spanner;

		public CapabilityProvider(ItemStack stack) {
			this.stack = stack;
			this.spanner = new ToolImpl();
		}

		public CapabilityProvider(ItemStack stack, ITool cap) {
			this.stack = stack;
			this.spanner = cap;
		}

		@Override
		public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
			if(capability == BaseAPI.TOOL_CAPABILITY)
				return true;
			return false;
		}

		@Override
		public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
			if(capability == BaseAPI.TOOL_CAPABILITY)
				return BaseAPI.TOOL_CAPABILITY.cast(spanner);
			return null;
		}
	}

	@Override
	public List<IRecipe> getRecipes(List<IRecipe> recipes) {
		recipes.add(new ShapedOreRecipe(this, "I I", "ISI", " S ", 'I', "ingotIron", 'S', "stickWood"));
		return recipes;
	}

}
