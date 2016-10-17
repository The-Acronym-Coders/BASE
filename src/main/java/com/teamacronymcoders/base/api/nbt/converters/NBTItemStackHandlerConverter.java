package com.teamacronymcoders.base.api.nbt.converters;

import com.teamacronymcoders.base.api.nbt.INBTConverter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.ItemStackHandler;

/**
 * Created by Jared on 8/20/2016.
 */
public class NBTItemStackHandlerConverter implements INBTConverter<ItemStackHandler> {
    private String name;

    public NBTItemStackHandlerConverter() {
    }

    @Override
    public NBTItemStackHandlerConverter setKey(String key) {
        this.name = key;
        return this;
    }

    @Override
    public ItemStackHandler convert(NBTTagCompound tag) {
        ItemStackHandler handler = new ItemStackHandler();
        handler.deserializeNBT(tag.getCompoundTag("items"));
        System.out.println(handler);
        return handler;
    }

    @Override
    public NBTTagCompound convert(NBTTagCompound tag, ItemStackHandler value) {
        tag.setTag("items", value.serializeNBT());
        return tag;
    }
}
