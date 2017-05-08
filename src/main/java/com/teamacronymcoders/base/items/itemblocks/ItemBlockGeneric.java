package com.teamacronymcoders.base.items.itemblocks;

import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.items.IHasSubItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemBlockGeneric<T extends Block> extends ItemBlock implements IHasModel {
    private T actualBlock;

    public ItemBlockGeneric(T block) {
        super(block);
        this.actualBlock = block;
    }

    public T getActualBlock() {
        return this.actualBlock;
    }

    @Override
    public List<ItemStack> getAllSubItems(List<ItemStack> itemStacks) {
        if (this.actualBlock instanceof IHasSubItems) {
            itemStacks.addAll(((IHasSubItems) this.actualBlock).getAllSubItems(new ArrayList<>()));
        } else {
            itemStacks.add(new ItemStack(this));
        }
        return itemStacks;
    }

    @Override
    public int getMetadata(int damage) {
        return Math.min(15, Math.max(0, damage));
    }

    @Override
    public Item getItem() {
        return this;
    }
}
