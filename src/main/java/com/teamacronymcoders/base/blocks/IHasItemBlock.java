package com.teamacronymcoders.base.blocks;

import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.util.IItemProvider;

import javax.annotation.Nonnull;

@FunctionalInterface
public interface IHasItemBlock extends IItemProvider {
    /*
     * @return an instance of the blocks ItemBlock, called by the registry, but should be able to called by anything so don't return new every time.
     */
    @Nonnull
    BlockItem getItemBlock();

    @Override
    @Nonnull
    default Item asItem() {
        return this.getItemBlock();
    }
}
