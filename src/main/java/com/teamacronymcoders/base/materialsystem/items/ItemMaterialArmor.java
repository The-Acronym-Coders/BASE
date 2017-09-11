package com.teamacronymcoders.base.materialsystem.items;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemMaterialArmor extends ItemArmor {
    public ItemMaterialArmor(ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, 0, equipmentSlotIn);
    }


}
