package com.teamacronymcoders.base.reference;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.api.materials.MaterialRegistry;
import com.teamacronymcoders.base.items.BaseItems;
import com.google.common.base.Stopwatch;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TabBase extends CreativeTabs {

    Stopwatch watch = Stopwatch.createUnstarted();

    int iconIndex = -1;

    public TabBase() {
        super("BASE");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void displayAllRelevantItems(List<ItemStack> list) {
        for (Item item : Item.REGISTRY) {
            if (item == null) {
                continue;
            }
            for (CreativeTabs tab : item.getCreativeTabs()) {
                if (tab == this) {
                    item.getSubItems(item, this, list);
                }
            }
            if (item.getRegistryName().getResourceDomain().equals(Reference.MODID)) {
                if (!list.contains(item)) {
                    item.getSubItems(item, this, list);
                }
            }
        }

        if (this.getRelevantEnchantmentTypes() != null) {
            this.addEnchantmentBooksToList(list, this.getRelevantEnchantmentTypes());
        }
        Collections.sort(list, new Comparator<ItemStack>() {
            @Override
            public int compare(ItemStack o1, ItemStack o2) {
                if ((Item.getIdFromItem(o1.getItem()) + "").compareTo(Item.getIdFromItem(o2.getItem()) + "") == 0) {
                    return (o2.getItemDamage()+"").compareTo(o2.getItemDamage() + "");
                } else return (Item.getIdFromItem(o1.getItem()) + "").compareTo(Item.getIdFromItem(o2.getItem()) + "");
            }
        });

    }

    private void updateIcon() {
        if (!watch.isRunning()) {
            watch.reset();
            watch.start();
        }
        if (Base.proxy.isClient() && this.watch.elapsed(TimeUnit.MILLISECONDS) >= 1500L) {
            Random random = new Random();
            this.watch.reset();
            this.watch.start();
            iconIndex = MaterialRegistry.getIDList().get(random.nextInt(MaterialRegistry.getIDList().size()));
        }
    }

    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack() {
        updateIcon();

        if (iconIndex != -1) {
            return new ItemStack(BaseItems.GEAR, 1, iconIndex);
        } else {
            return new ItemStack(BaseItems.GEAR, 1);
        }

    }


    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return this.getIconItemStack().getItem();
    }
}
