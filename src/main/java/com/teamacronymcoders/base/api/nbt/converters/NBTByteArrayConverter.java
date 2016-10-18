package com.teamacronymcoders.base.api.nbt.converters;

import com.teamacronymcoders.base.api.nbt.INBTConverter;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Jared on 7/26/2016.
 */
public class NBTByteArrayConverter implements INBTConverter<byte[]> {

    private String name;

    public NBTByteArrayConverter() {
    }

    @Override
    public NBTByteArrayConverter setKey(String key) {
        this.name = key;
        return this;
    }

    @Override
    public byte[] convert(NBTTagCompound tag) {
        return tag.getByteArray(name);
    }

    @Override
    public NBTTagCompound convert(NBTTagCompound tag, byte[] value) {
        tag.setByteArray(name, value);
        return tag;
    }
}
