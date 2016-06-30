package com.acronym.base.items;

import com.acronym.base.api.materials.Material;
import com.acronym.base.api.materials.registries.MaterialRegistry;
import com.acronym.base.util.IMetaItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Jared on 6/27/2016.
 */
public class ItemGear extends Item implements IMetaItem {
    public ItemGear() {
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
            return String.format("%s_gear", MaterialRegistry.getFromID(stack.getItemDamage()).getName());
        return "null_gear";
    }

    @Override
    public List<Integer> getMetaData() {
        List<Integer> retList = MaterialRegistry.getMaterials().entrySet().stream().map(ent -> ent.getKey().getRight()).collect(Collectors.toList());
        return retList;
    }
}
