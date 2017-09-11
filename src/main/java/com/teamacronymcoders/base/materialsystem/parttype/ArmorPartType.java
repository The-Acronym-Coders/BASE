package com.teamacronymcoders.base.materialsystem.parttype;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.partdata.DataPartParsers;
import com.teamacronymcoders.base.materialsystem.partdata.MaterialPartData;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.EnumHelper;

import javax.annotation.Nonnull;
import javax.xml.crypto.Data;
import java.util.List;
import java.util.stream.Collectors;

public class ArmorPartType extends PartType {
    public ArmorPartType() {
        super("Armor");
        List<PartDataPiece> dataPieceList = Lists.newArrayList();
        dataPieceList.add(new PartDataPiece("enchantability"));
        dataPieceList.add(new PartDataPiece("durability"));
        dataPieceList.add(new PartDataPiece("reduction"));
        dataPieceList.add(new PartDataPiece("toughness"));
        this.setData(dataPieceList);
    }

    public void setup(@Nonnull MaterialPart materialPart) {
        ArmorMaterial iron = ArmorMaterial.IRON;
        MaterialPartData data = materialPart.getData();
        int enchantability = data.getValue("enchantability", iron.getEnchantability(), DataPartParsers::getInt);
        float toughness = data.getValue("toughness", iron.getToughness(), DataPartParsers::getFloat);
        int durability = data.getValue("durability", 15, DataPartParsers::getInt);
        List<Integer> damageReduction = data.getValues("reduction", new Integer[] {2, 5, 6, 2}, DataPartParsers::getInt, 5);
        int[] damageArray = new int[damageReduction.size()];
        for (int i = 0; i < damageReduction.size(); i++) {
            damageArray[i] = damageReduction.get(i);
        }
        String name = materialPart.getMaterial().getUnlocalizedName();
        EnumHelper.addArmorMaterial(name, name, durability, damageArray, enchantability, SoundEvents.ITEM_ARMOR_EQUIP_IRON, toughness);

    }
}
