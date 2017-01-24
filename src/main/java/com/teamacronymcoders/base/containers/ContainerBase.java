package com.teamacronymcoders.base.containers;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public abstract class ContainerBase extends Container {
	public void createPlayerSlots(InventoryPlayer player) {
		for(int vertical = 0; vertical < 3; ++vertical) {
			for(int horizontal = 0; horizontal < 9; ++horizontal) {
				this.addSlotToContainer(
						new Slot(player, horizontal + (vertical * 9) + 9, 8 + (horizontal * 18), 84 + (vertical * 18)));
			}
		}

		for(int horizontal = 0; horizontal < 9; ++horizontal) {
			this.addSlotToContainer(new Slot(player, horizontal, 8 + (horizontal * 18), 142));
		}
	}
}