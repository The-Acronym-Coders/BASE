package com.teamacronymcoders.base.client.models;

import com.teamacronymcoders.base.IBaseMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class SafeModelLoader {
    private IBaseMod mod;

    public SafeModelLoader(IBaseMod mod) {
        this.mod = mod;
    }

    public void setBlockModel(Block block) {
        setBlockModel(block, 0);
    }

    public void setBlockModel(Block block, int metadata) {
        setBlockModel(block, metadata, block.getUnlocalizedName());
    }

    public void setBlockModel(Block block, int metadata, String override) {
        setItemModel(Item.getItemFromBlock(block), metadata, override);
    }

    public void setItemModel(Item item) {
        setItemModel(item, 0);
    }

    public void setItemModel(Item item, int metadata) {
        if(item != null) {
            String name = item.getUnlocalizedName();
            if (name.startsWith("item.") || name.startsWith("tile.")) {
                name = name.substring(5);
            }
            setItemModel(item, metadata, name);
        }

    }

    public void setItemModel(Item item, int metadata, String override) {
        setItemModel(item, metadata, new ResourceLocation(mod.getID(), override));
    }

    public void setItemModel(Item item, int metadata, ResourceLocation resourceLocation) {
        mod.getLibProxy().setItemModel(item, metadata, resourceLocation);
    }

    public void setItemModel(Object object, int metadata, String location) {
        Item item = null;
        if (object instanceof Item) {
            item = (Item) object;
        } else if (object instanceof Block) {
            item = Item.getItemFromBlock((Block) object);
        }

        if (item != null) {
            setItemModel(item, metadata, new ResourceLocation(mod.getID(), location));
        }
    }

    public void setAllItemModels(Item item, IHasModel model) {
        mod.getLibProxy().setAllItemModels(item, model.getResourceLocations(new ArrayList<>()));
    }

    public void registerFluidModel(Block fluidBlock, final ResourceLocation resourceLocation) {
        mod.getLibProxy().registerFluidModel(fluidBlock, resourceLocation);
    }
}
