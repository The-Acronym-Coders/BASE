package com.teamacronymcoders.base.util.inventory;

import javax.annotation.Nullable;

import com.teamacronymcoders.base.containers.slots.SlotChanged;
import com.teamacronymcoders.base.tileentities.IOnSlotChanged;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.items.IItemHandler;

public class SlotFuel extends SlotChanged {

	public SlotFuel(IItemHandler itemHandler, IOnSlotChanged changeReceiver, int slotIndex, int posX, int posY) {
		super(itemHandler, changeReceiver, slotIndex, posX, posY);
	}

	@Override
	public boolean isItemValid(@Nullable ItemStack stack) {
		return TileEntityFurnace.isItemFuel(stack);
	}

}
