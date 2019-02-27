package com.teamacronymcoders.base.blocks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IItemProvider;

@FunctionalInterface
public interface IHasItemBlock extends IItemProvider {
    /*
     * @return an instance of the blocks ItemBlock, called by the registry, but should be able to called by anything so don't return new every time.
     */
    ItemBlock getItemBlock();

    @Override
    default Item asItem() {
        return this.getItemBlock();
    }
}
