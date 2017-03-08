package com.teamacronymcoders.base.containers.slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class SlotArmor extends Slot {
    public static final EntityEquipmentSlot[] VALID_EQUIPMENT_SLOTS =
            new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS,
                    EntityEquipmentSlot.FEET};

    EntityEquipmentSlot entityEquipmentSlot;
    EntityPlayer entityPlayer;

    public SlotArmor(EntityPlayer entityPlayer, int entityEquipmentSlotNumber, int xPos, int yPos) {
        super(entityPlayer.inventory, 36 + (3 - entityEquipmentSlotNumber), xPos, yPos);
        this.entityEquipmentSlot = VALID_EQUIPMENT_SLOTS[entityEquipmentSlotNumber];
    }

    /**
     * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1
     * in the case of armor slots)
     */
    public int getSlotStackLimit() {
        return 1;
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace
     * fuel.
     */
    public boolean isItemValid(@Nullable ItemStack stack) {
        return stack != null && stack.getItem().isValidArmor(stack, entityEquipmentSlot, entityPlayer);
    }

    @Nullable
    @SideOnly(Side.CLIENT)
    public String getSlotTexture() {
        return ItemArmor.EMPTY_SLOT_NAMES[entityEquipmentSlot.getIndex()];
    }
}
