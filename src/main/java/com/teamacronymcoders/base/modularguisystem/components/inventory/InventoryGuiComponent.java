package com.teamacronymcoders.base.modularguisystem.components.inventory;

import com.teamacronymcoders.base.modularguisystem.components.IModularGuiComponent;
import com.teamacronymcoders.base.modularguisystem.container.ContainerModular;
import com.teamacronymcoders.base.modularguisystem.gui.GuiModular;
import net.minecraft.inventory.Slot;
import net.minecraftforge.items.IItemHandler;

import java.awt.*;

public class InventoryGuiComponent implements IModularGuiComponent {
    private final Point startingPosition;
    private final IItemHandler iItemHandler;
    private final ISlotCreator slotPlacementFunction;

    public InventoryGuiComponent(IItemHandler itemStackHandler, Point startingPosition, ISlotCreator slotPlacementFunction) {
        this.iItemHandler = itemStackHandler;
        this.slotPlacementFunction = slotPlacementFunction;
        this.startingPosition = startingPosition;
    }

    @Override
    public void addToGui(GuiModular guiModular) {

    }

    @Override
    public void addToContainer(ContainerModular containerModular) {
        for (int slotNumber = 0; slotNumber < iItemHandler.getSlots(); slotNumber++) {
            Slot slot = slotPlacementFunction.createSlot(iItemHandler, slotNumber);
            slot.xPos += startingPosition.getX();
            slot.yPos += startingPosition.getY();
            containerModular.addSlot(slot);
        }
    }
}
