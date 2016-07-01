package com.acronym.base.items;

import com.acronym.base.Base;
import com.acronym.base.api.materials.Material;
import com.acronym.base.api.registries.MaterialRegistry;
import com.acronym.base.util.IMetaItem;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Jared on 6/30/2016.
 */
public class ItemPart extends Item implements IMetaItem {
    Material.EnumPartType type;

    public ItemPart(Material.EnumPartType type) {
        this.type = type;
        setHasSubtypes(true);
    }


    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (Map.Entry<MutablePair<String, Integer>, Material> ent : MaterialRegistry.getMaterials().entrySet()) {
            subItems.add(new ItemStack(itemIn, 1, ent.getKey().getRight()));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if (stack.getItemDamage() >= 0 && stack.getItemDamage() < MaterialRegistry.getMaterials().size())
            return String.format("item.base.%s.%s", this.type.name().toLowerCase(), MaterialRegistry.getFromID(stack.getItemDamage()).getName().toLowerCase());
        return "item.base.null_part";
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        if (stack.getItemDamage() >= 0 && stack.getItemDamage() < MaterialRegistry.getMaterials().size())
            return String.format("%s %s", MaterialRegistry.getFromID(stack.getItemDamage()).getLocalizedName(), this.type.getLocalizedName());
        return ChatFormatting.RED+Base.languageHelper.error("null_part");
    }

    @Override
    public List<Integer> getMetaData() {
        List<Integer> retList = MaterialRegistry.getMaterials().entrySet().stream().map(ent -> ent.getKey().getRight()).collect(Collectors.toList());
        return retList;
    }
}
