package com.teamacronymcoders.base.registry;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.blocks.IHasBlockColor;
import com.teamacronymcoders.base.blocks.IHasBlockStateMapper;
import com.teamacronymcoders.base.blocks.IHasItemBlock;
import com.teamacronymcoders.base.blocks.IHasTileEntity;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.items.IHasItemColor;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockRegistry extends Registry<Block> {
    public BlockRegistry(IBaseMod mod) {
        super(mod);
    }

    @Override
    protected void initiateEntry(String name, Block block) {
        ResourceLocation blockName = new ResourceLocation(mod.getID(), name);
        block.setCreativeTab(mod.getCreativeTab());
        GameRegistry.register(block, blockName);

        if (block instanceof IHasItemBlock) {
            GameRegistry.register(((IHasItemBlock) block).getItemBlock(), blockName);
        }

        if (block instanceof IHasTileEntity) {
            Class<? extends TileEntity> tileEntityClass = ((IHasTileEntity) block).getTileEntityClass();
            GameRegistry.registerTileEntity(tileEntityClass, mod.getPrefix() + name);
        }
        super.initiateEntry(name, block);
    }

    @Override
    protected void initiateModel(String name, Block entry) {
        if(entry instanceof IHasBlockStateMapper) {
            mod.getModelLoader().registerBlockStateMapper(entry, (IHasBlockStateMapper)entry);
        }

        Item item = Item.getItemFromBlock(entry);

        IHasModel hasModel = null;
        if (item instanceof IHasModel) {
            hasModel = (IHasModel) item;
        } else if(entry instanceof IHasModel){
            hasModel = (IHasModel)entry;
        }

        if(item != null) {
            if(hasModel != null) {
                mod.getModelLoader().setAllItemModels(item, hasModel);
            } else {
                mod.getModelLoader().setItemModel(item);
            }
        }

        super.initiateModel(name, entry);
    }

    @Override
    protected void initiateColor(Block entry) {
        if(entry instanceof IHasItemColor) {
            mod.getModelLoader().registerItemColor(entry, (IHasItemColor)entry);
        }
        if(entry instanceof IHasBlockColor) {
            mod.getModelLoader().registerBlockColor((IHasBlockColor)entry);
        }
    }

    public void register(Block block) {
        String name = block.getUnlocalizedName();
        if (name.startsWith("tile.")) {
            name = name.substring(5);
        }
        register(name, block);
    }
}
