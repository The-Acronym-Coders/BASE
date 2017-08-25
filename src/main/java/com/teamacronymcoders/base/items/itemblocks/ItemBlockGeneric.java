package com.teamacronymcoders.base.items.itemblocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.items.IHasOreDict;
import com.teamacronymcoders.base.items.IHasSubItems;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemBlockGeneric<T extends Block> extends ItemBlock implements IHasModel, IHasOreDict {
    private T actualBlock;

    public ItemBlockGeneric(T block) {
        super(block);
        this.actualBlock = block;
        this.setHasSubtypes(true);
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

    @Override
    @Nonnull
    public Map<ItemStack, String> getOreDictNames(@Nonnull Map<ItemStack, String> names) {
        if (actualBlock instanceof IHasOreDict) {
            return ((IHasOreDict) actualBlock).getOreDictNames(names);
        } else {
            return names;
        }
    }

    @Override
    public IBaseMod getMod() {
        return null;
    }

    @Override
    public void setMod(IBaseMod mod) {

    }
}
