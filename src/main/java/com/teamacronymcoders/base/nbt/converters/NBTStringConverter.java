package com.teamacronymcoders.base.nbt.converters;

import com.teamacronymcoders.base.nbt.INBTConverter;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Jared on 7/26/2016.
 */
public class NBTStringConverter implements INBTConverter<String> {
    private String name;

    public NBTStringConverter() {
    }

    @Override
    public NBTStringConverter setKey(String key) {
        this.name = key;
        return this;
    }

    @Override
    public String convert(NBTTagCompound tag) {
        return tag.getString(name);
    }

    @Override
    public NBTTagCompound convert(NBTTagCompound tag, String value) {
        tag.setString(name, value);
        return tag;
    }

}
