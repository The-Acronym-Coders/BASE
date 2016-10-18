package com.teamacronymcoders.base.api.nbt.converters;

import com.teamacronymcoders.base.api.nbt.INBTConverter;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Jared on 7/26/2016.
 */
public class NBTShortConverter implements INBTConverter<Short> {
    private String name;

    public NBTShortConverter() {
    }

    @Override
    public NBTShortConverter setKey(String key) {
        this.name = key;
        return this;
    }

    @Override
    public Short convert(NBTTagCompound tag) {
        return tag.getShort(name);
    }

    @Override
    public NBTTagCompound convert(NBTTagCompound tag, Short value) {
        tag.setShort(name, value);
        return tag;
    }

}
