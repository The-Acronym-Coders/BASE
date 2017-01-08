package com.teamacronymcoders.base.nbt.converters;

import com.teamacronymcoders.base.nbt.INBTConverter;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Jared on 7/26/2016.
 */
public class NBTIntArrayConverter implements INBTConverter<int[]> {

    private String name;

    public NBTIntArrayConverter() {
    }

    @Override
    public NBTIntArrayConverter setKey(String key) {
        this.name = key;
        return this;
    }

    @Override
    public int[] convert(NBTTagCompound tag) {
        return tag.getIntArray(name);
    }

    @Override
    public NBTTagCompound convert(NBTTagCompound tag, int[] value) {
        tag.setIntArray(name, value);
        return tag;
    }

}
