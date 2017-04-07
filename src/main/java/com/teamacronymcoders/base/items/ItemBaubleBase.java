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
    public boolean canEquip(ItemStack bauble, EntityLivingBase entityLivingBase) {
        return true;
    }

    @Override
    @Optional.Method(modid = "Baubles")
    public boolean canUnequip(ItemStack bauble, EntityLivingBase entityLivingBase) {
        return true;
    }

    @Override
    @Optional.Method(modid = "Baubles")
    public void onEquipped(ItemStack bauble, EntityLivingBase entityLivingBase) {
    }

    @Override
    @Optional.Method(modid = "Baubles")
    public void onUnequipped(ItemStack bauble, EntityLivingBase entityLivingBase) {
    }

    @Override
    @Optional.Method(modid = "Baubles")
    public void onWornTick(ItemStack bauble, EntityLivingBase entityLivingBase) {
    }
}
