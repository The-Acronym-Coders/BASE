package com.acronym.base.api.nbt.converters;

import com.acronym.base.api.nbt.INBTConverter;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Jared on 7/26/2016.
 */
public class NBTFloatConverter implements INBTConverter<Float> {

    private String name;

    public NBTFloatConverter() {
    }

    @Override
    public NBTFloatConverter setKey(String key) {
        this.name = key;
        return this;
    }

    @Override
    public Float convert(NBTTagCompound tag) {
        return tag.getFloat(name);
    }

    @Override
    public NBTTagCompound convert(NBTTagCompound tag, Float value) {
        tag.setFloat(name, value);
        return tag;
    }

}
