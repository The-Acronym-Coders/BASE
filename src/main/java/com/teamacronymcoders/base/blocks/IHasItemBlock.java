package com.teamacronymcoders.base.blocks;

import com.teamacronymcoders.base.items.IAmItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

@FunctionalInterface
public interface IHasItemBlock extends IAmItem {
    /*
     * @return an instance of the blocks ItemBlock, called by the registry, but should be able to called by anything so don't return new every time.
     */
    ItemBlock getItemBlock();

    @Override
    default Item getItem() {
        return this.getItemBlock();
    }
}
