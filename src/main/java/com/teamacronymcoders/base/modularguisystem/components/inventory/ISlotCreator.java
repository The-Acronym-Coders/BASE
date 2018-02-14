package com.teamacronymcoders.base.modularguisystem.components.inventory;

import net.minecraft.inventory.Slot;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

@FunctionalInterface
public interface ISlotCreator {
    Slot createSlot(IItemHandler handler, int slotNumber);
}
