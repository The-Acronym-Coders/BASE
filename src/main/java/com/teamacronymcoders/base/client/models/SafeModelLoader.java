package com.teamacronymcoders.base.client.models;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.blocks.IHasBlockColor;
import com.teamacronymcoders.base.blocks.IHasBlockStateMapper;
import com.teamacronymcoders.base.client.models.wrapped.WrappedBlockEntry;
import com.teamacronymcoders.base.items.IHasItemColor;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

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
        if (item != null) {
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
        mod.getLibProxy().setAllItemModels(item, model);
    }

    public void registerFluidModel(Block fluidBlock, final ResourceLocation resourceLocation) {
        mod.getLibProxy().registerFluidModel(fluidBlock, resourceLocation);
    }

    public void registerItemColor(Item item, IHasItemColor itemColor) {
        mod.getLibProxy().registerItemColor(item, itemColor);
    }

    public void registerItemColor(Block block, IHasItemColor itemColor) {
        mod.getLibProxy().registerItemColor(block, itemColor);
    }

    public void registerBlockColor(IHasBlockColor blockColor) {
        mod.getLibProxy().registerBlockColor(blockColor);
    }

    public void registerBlockStateMapper(Block block, IHasBlockStateMapper stateMapper) {
        mod.getLibProxy().registerBlockStateMapper(block, stateMapper);
    }

    public void registerModelVariant(Item item, ResourceLocation resourceLocation) {
        mod.getLibProxy().registerModelVariant(item, resourceLocation);
    }

    public void registerWrappedModel(ResourceLocation resourceLocation, WrappedBlockEntry wrappedBlockEntry) {
        mod.getLibProxy().registerWrappedModel(resourceLocation, wrappedBlockEntry);
    }
}
