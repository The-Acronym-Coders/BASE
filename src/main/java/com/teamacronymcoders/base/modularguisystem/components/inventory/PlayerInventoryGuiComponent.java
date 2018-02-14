package com.teamacronymcoders.base.modularguisystem.components.inventory;

import com.teamacronymcoders.base.util.CapUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.awt.*;

public class PlayerInventoryGuiComponent extends InventoryGuiComponent {
    public PlayerInventoryGuiComponent(EntityPlayer entityPlayer) {
        super(CapUtils.get(entityPlayer, CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP),
                new Point(8, 84), (inventory, slotNumber) -> {
                    Slot slot;
                    if (slotNumber < 9) {
                        slot = new SlotItemHandler(inventory, slotNumber, slotNumber * 18, 58);
                    } else {
                        int slotPos = slotNumber - 9;
                        int x = slotPos % 9;
                        int y = slotPos / 9;
                        slot = new SlotItemHandler(inventory, slotNumber, x * 18, y * 18);
                    }

                    return slot;
                });
    }
}
