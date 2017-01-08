package com.teamacronymcoders.base.nbt.converters;

import com.teamacronymcoders.base.nbt.INBTConverter;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Jared on 7/26/2016.
 */
public class NBTByteConverter implements INBTConverter<Byte> {

    private String name;

    public NBTByteConverter() {
    }

    @Override
    public NBTByteConverter setKey(String key) {
        this.name = key;
        return this;
    }

    @Override
    public Byte convert(NBTTagCompound tag) {
        return tag.getByte(name);
    }

    @Override
    public NBTTagCompound convert(NBTTagCompound tag, Byte value) {
        tag.setByte(name, value);
        return tag;
    }

}
