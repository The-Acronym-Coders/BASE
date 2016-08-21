package com.acronym.base.api.nbt.converters;

import com.acronym.base.api.nbt.INBTConverter;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Jared on 7/26/2016.
 */
public class NBTIntConverter implements INBTConverter<Integer> {
    private String name;

    public NBTIntConverter() {
    }

    @Override
    public NBTIntConverter setKey(String key) {
        this.name = key;
        return this;
    }

    @Override
    public Integer convert(NBTTagCompound tag) {
        return tag.getInteger(name);
    }

    @Override
    public NBTTagCompound convert(NBTTagCompound tag, Integer value) {
        tag.setInteger(name, value);
        return tag;
    }

}
