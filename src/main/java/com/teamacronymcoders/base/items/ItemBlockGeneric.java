package com.teamacronymcoders.base.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockGeneric<T extends Block> extends ItemBlock {
    private T actualBlock;

    public ItemBlockGeneric(T block) {
        super(block);
        this.actualBlock = block;
    }

    public T getActualBlock() {
        return this.actualBlock;
    }
}
