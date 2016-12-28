package com.teamacronymcoders.base.modules.jei;

import com.teamacronymcoders.base.items.IHasSubItems;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

@JEIPlugin
public class BASEJEIPlugin extends BlankModPlugin {
    private static IJeiHelpers jeiHelpers;

    @Override
    public void register(IModRegistry registry) {
        jeiHelpers = registry.getJeiHelpers();
    }

    public static void blackListItemStack(ItemStack itemStack) {
        jeiHelpers.getItemBlacklist().addItemToBlacklist(itemStack);
    }

    public static void blackListItem(IHasSubItems item) {
        item.getAllSubItems(new ArrayList<>()).forEach(BASEJEIPlugin::blackListItemStack);
    }


}
