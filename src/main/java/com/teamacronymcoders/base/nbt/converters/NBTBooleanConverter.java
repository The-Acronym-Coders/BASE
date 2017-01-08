package com.teamacronymcoders.base.nbt.converters;

import com.teamacronymcoders.base.nbt.INBTConverter;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Jared on 7/26/2016.
 */
public class NBTBooleanConverter implements INBTConverter<Boolean> {

    private String name;

    public NBTBooleanConverter() {
    }

    @Override
    public NBTBooleanConverter setKey(String key) {
        this.name = key;
        return this;
    }

    @Override
    public Boolean convert(NBTTagCompound tag) {
        return tag.getBoolean(name);
    }

    @Override
    public NBTTagCompound convert(NBTTagCompound tag, Boolean value) {
        tag.setBoolean(name, value);
        return tag;
    }

}
