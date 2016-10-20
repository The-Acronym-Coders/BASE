package com.teamacronymcoders.base.registry;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.client.models.IHasModel;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRegistry extends Registry<Item> {
    public ItemRegistry(IBaseMod mod) {
        super(mod);
    }

    @Override
    public void initiateEntry(String name, Item item) {
        ResourceLocation itemRegistryName = new ResourceLocation(mod.getPrefix() + name);
        GameRegistry.register(item, itemRegistryName);
        super.initiateEntry(name, item);
    }

    @Override
    public void initiateModel(String name, Item item) {
        if (item instanceof IHasModel) {
            mod.getModelLoader().setAllItemModels(item, (IHasModel) item);
        } else {
            mod.getModelLoader().setItemModel(item);
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
