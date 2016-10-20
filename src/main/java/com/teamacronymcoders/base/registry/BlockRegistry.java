package com.teamacronymcoders.base.registry;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.blocks.IHasItemBlock;
import com.teamacronymcoders.base.blocks.IHasTileEntity;
import com.teamacronymcoders.base.client.models.IHasModel;
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
    public void initiateEntry(String name, Block block) {
        ResourceLocation blockName = new ResourceLocation(mod.getID(), name);
        GameRegistry.register(block, blockName);

        if (block instanceof IHasItemBlock) {
            GameRegistry.register(((IHasItemBlock) block).getItemBlockClass(), blockName);
        }

        if (block instanceof IHasTileEntity) {
            Class<? extends TileEntity> tileEntityClass = ((IHasTileEntity) block).getTileEntityClass();
            GameRegistry.registerTileEntity(tileEntityClass, mod.getPrefix() + name);
        }
        super.initiateEntry(name, block);
    }

    @Override
    public void initiateModel(String name, Block entry) {
        Item item = Item.getItemFromBlock(entry);

        if (item instanceof IHasModel) {
            mod.getModelLoader().setAllItemModels(item, (IHasModel) item);
        } else {
            mod.getModelLoader().setItemModel(item);
        }
        super.initiateModel(name, entry);
    }

    public void register(Block block) {
        String name = block.getUnlocalizedName();
        if (name.startsWith("tile.")) {
            name = name.substring(5);
        }
        register(name, block);
    }
}
