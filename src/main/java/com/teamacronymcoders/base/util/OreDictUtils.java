package com.teamacronymcoders.base.util;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.registrysystem.config.ConfigEntry;
import com.teamacronymcoders.base.registrysystem.config.ConfigEntryBuilder;
import com.teamacronymcoders.base.registrysystem.config.ConfigRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.*;

public class OreDictUtils {
    private static Map<String, ItemStack> preferredItemStacks;
    private static List<String> preferredModIds;

    private OreDictUtils() {

    }

    public static void setup() {
        preferredItemStacks = new HashMap<>();
        preferredModIds = new ArrayList<>();
        ConfigEntry preferredMods = ConfigEntryBuilder.getBuilder("preferredOreDictIds", "base").setArray(true)
                .setCategory("materials").setComment("List for Prioritizing OreDict returns by modid").build();
        Base.instance.getRegistry(ConfigRegistry.class, "CONFIG").addEntry(preferredMods);
        preferredModIds = Arrays.asList(preferredMods.getStringArray());
    }

    @Nullable
    //TODO: Change in MC 1.11
    public static ItemStack getPreferredItemStack(String oreDictName) {
        if (!preferredItemStacks.containsKey(oreDictName)) {
            int bestMatchLevel = preferredModIds.size();
            ItemStack preferredItemStack = ItemStack.EMPTY;
            List<ItemStack> itemStackList = OreDictionary.getOres(oreDictName);
            for (ItemStack itemStack : itemStackList) {
                if (ItemStackUtils.isValid(itemStack)) {
                    String modid = ItemStackUtils.getModIdFromItemStack(itemStack);
                    if (preferredModIds.contains(modid)) {
                        int newMatch = preferredModIds.indexOf(modid);
                        bestMatchLevel = newMatch < bestMatchLevel ? newMatch : bestMatchLevel;
                        preferredItemStack = itemStack;
                    }
                }
            }
            preferredItemStacks.put(oreDictName, preferredItemStack);
        }
        ItemStack itemStack = preferredItemStacks.get(oreDictName);
        return ItemStackUtils.isValid(itemStack) ? itemStack.copy() : null;
    }
}
