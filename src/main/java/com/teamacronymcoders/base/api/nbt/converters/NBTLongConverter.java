package com.teamacronymcoders.base.api.nbt.converters;

import com.teamacronymcoders.base.api.nbt.INBTConverter;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Jared on 7/26/2016.
 */
public class NBTLongConverter implements INBTConverter<Long> {
    private String name;

    public NBTLongConverter() {
    }

    @Override
    public NBTLongConverter setKey(String key) {
        this.name = key;
        return this;
    }

    @Override
    public Long convert(NBTTagCompound tag) {
        return tag.getLong(name);
    }

    @Override
    public NBTTagCompound convert(NBTTagCompound tag, Long value) {
        tag.setLong(name, value);
        return tag;
    }

}
