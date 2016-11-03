package com.teamacronymcoders.base.items;

import baubles.api.IBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional;

@Optional.Interface(iface = "baubles.api.IBauble", modid = "Baubles")
public abstract class ItemBaubleBase extends ItemBase implements IBauble {

    public ItemBaubleBase(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Override
    @Optional.Method(modid = "Baubles")
    public boolean canEquip(ItemStack arg0, EntityLivingBase arg1) {
        return true;
    }

    @Override
    @Optional.Method(modid = "Baubles")
    public boolean canUnequip(ItemStack arg0, EntityLivingBase arg1) {
        return true;
    }

    @Override
    @Optional.Method(modid = "Baubles")
    public void onEquipped(ItemStack arg0, EntityLivingBase arg1) {}

    @Override
    @Optional.Method(modid = "Baubles")
    public void onUnequipped(ItemStack arg0, EntityLivingBase arg1) {}

    @Override
    @Optional.Method(modid = "Baubles")
    public void onWornTick(ItemStack stack, EntityLivingBase living) {}
}
