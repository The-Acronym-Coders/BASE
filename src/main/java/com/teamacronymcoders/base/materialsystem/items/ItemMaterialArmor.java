package com.teamacronymcoders.base.materialsystem.items;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

import javax.annotation.Nonnull;
import java.util.Locale;

public class ItemMaterialArmor extends ItemArmor implements IHasModel {
    private MaterialPart materialPart;
    private IBaseMod mod;

    public ItemMaterialArmor(MaterialPart materialPart, ArmorMaterial material, EntityEquipmentSlot equipmentSlot) {
        super(material, 0, equipmentSlot);
        this.materialPart = materialPart;
        this.setUnlocalizedName(materialPart.getMaterial().getUnlocalizedName() + "_" + equipmentSlot.getName().toLowerCase(Locale.US));
    }

    @Override
    public boolean hasColor(@Nonnull ItemStack stack) {
        return materialPart.isColorized();
    }

    @Override
    public int getColor(@Nonnull ItemStack stack) {
        return materialPart.getColor();
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack itemStack) {
        //noinspection deprecation
        return I18n.translateToLocalFormatted(this.materialPart.getPart()
                .getUnlocalizedName() + "." + this.armorType.getName(),
                this.materialPart.getMaterial().getName());
    }

    @Override
    public IBaseMod getMod() {
        return mod;
    }

    @Override
    public void setMod(IBaseMod mod) {
        this.mod = mod;
    }

    @Override
    public Item getItem() {
        return this;
    }
}
