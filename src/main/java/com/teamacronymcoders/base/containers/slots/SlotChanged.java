package com.teamacronymcoders.base.containers.slots;

import com.teamacronymcoders.base.tileentities.IOnSlotChanged;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotChanged extends SlotItemHandler {
    protected IOnSlotChanged changeReceiver;

    public SlotChanged(IItemHandler itemHandler, IOnSlotChanged changeReceiver, int slotIndex, int posX, int posY) {
        super(itemHandler, slotIndex, posX, posY);
        this.changeReceiver = changeReceiver;
    }

    @Override
    public void onSlotChanged() {
        super.onSlotChanged();
        changeReceiver.onSlotChanged(this);
    }
}
