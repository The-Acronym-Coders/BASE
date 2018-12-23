package com.teamacronymcoders.base.items;

import java.util.List;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.client.models.IHasModel;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

public class ItemArmorBase extends ItemArmor implements IHasModel {
    private IBaseMod mod;
    private String name;

    public ItemArmorBase(ArmorMaterial material, EntityEquipmentSlot equipmentSlot, String name) {
        super(material, 0, equipmentSlot);
        this.setTranslationKey(name);
        this.name = name;
    }

    @Override
    public IBaseMod getMod() {
        return this.mod;
    }

    @Override
    public void setMod(IBaseMod mod) {
        this.mod = mod;
    }

    @Override
    public Item getItem() {
        return this;
    }
    
    @Override
    public List<String> getModelNames(List<String> modelNames) {
        modelNames.add(name);
        return modelNames;
    }
}
