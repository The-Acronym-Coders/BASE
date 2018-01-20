package com.teamacronymcoders.base.guicomponentsystem.component;

import com.teamacronymcoders.base.guicomponentsystem.container.ContainerComponentBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class ComponentPlayerInventory extends ComponentBase {
    public ComponentPlayerInventory() {
        this(8, 84);
    }

    public ComponentPlayerInventory(int left, int top) {
        super(left, top, 72, 170);
    }

    @Override
    public void onAddedToContainer(EntityPlayer entityPlayer, ContainerComponentBase containerComponentBase) {
        InventoryPlayer inventoryPlayer = entityPlayer.inventory;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                containerComponentBase.addSlot(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, this.getTop() + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            containerComponentBase.addSlot(new Slot(inventoryPlayer, i, this.getLeft() + i * 18, this.getTop() + 54));
        }
    }
}
