package com.teamacronymcoders.base.blocks;

import net.minecraft.item.ItemBlock;

@FunctionalInterface
public interface IHasItemBlock {
    /*
     * @return an instance of the blocks ItemBlock, called by the registry, but should be able to called by anything so don't return new every time.
     */
    ItemBlock getItemBlock();
}
