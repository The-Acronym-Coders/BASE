package com.teamacronymcoders.base.registry;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.items.IHasItemColor;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRegistry extends Registry<Item> {
    public ItemRegistry(IBaseMod mod) {
        super(mod);
    }

    @Override
    protected void initiateEntry(String name, Item item) {
        ResourceLocation itemRegistryName = new ResourceLocation(mod.getPrefix() + name);
        item.setCreativeTab(mod.getCreativeTab());
        GameRegistry.register(item, itemRegistryName);
        super.initiateEntry(name, item);
    }

    @Override
    protected void initiateModel(String name, Item item) {
        if (item instanceof IHasModel) {
            mod.getModelLoader().setAllItemModels(item, (IHasModel) item);
        } else {
            mod.getModelLoader().setItemModel(item);
        }
    }

    @Override
    protected void initiateColor(Item entry) {
        if(entry instanceof IHasItemColor) {
            mod.getModelLoader().registerItemColor(entry, (IHasItemColor) entry);
        }
    }

    public void register(Item item) {
        String name = item.getUnlocalizedName();
        if (name.startsWith("item.")) {
            name = name.substring(5);
        }
        register(name, item);
    }
}
