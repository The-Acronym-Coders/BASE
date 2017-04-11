package com.teamacronymcoders.base.containers.slots;

import com.teamacronymcoders.base.util.ItemStackUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class SlotArmor extends Slot {
    private EntityEquipmentSlot entityEquipmentSlot;
    private EntityPlayer entityPlayer;

    public SlotArmor(EntityPlayer entityPlayer, EntityEquipmentSlot entityEquipmentSlot, int xPos, int yPos) {
        super(entityPlayer.inventory, 36 + (3 - entityEquipmentSlot.ordinal()), xPos, yPos);
        this.entityEquipmentSlot = entityEquipmentSlot;
        this.entityPlayer = entityPlayer;
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
    public boolean isItemValid(ItemStack itemStack) {
        return ItemStackUtils.isValid(itemStack) && itemStack.getItem().isValidArmor(itemStack, entityEquipmentSlot, entityPlayer);
    }

    @Nullable
    @SideOnly(Side.CLIENT)
    public String getSlotTexture() {
        return ItemArmor.EMPTY_SLOT_NAMES[entityEquipmentSlot.getIndex()];
    }
}
