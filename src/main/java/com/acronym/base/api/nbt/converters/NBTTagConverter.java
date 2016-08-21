package com.acronym.base.api.nbt.converters;

import com.acronym.base.api.nbt.INBTConverter;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Jared on 7/26/2016.
 */
public class NBTTagConverter implements INBTConverter<NBTBase> {
    private String name;

    public NBTTagConverter() {
    }

    @Override
    public NBTTagConverter setKey(String key) {
        this.name = key;
        return this;
    }

    @Override
    public NBTBase convert(NBTTagCompound tag) {
        return tag.getTag(name);
    }

    @Override
    public NBTTagCompound convert(NBTTagCompound tag, NBTBase value) {
        tag.setTag(name, value);
        return tag;
    }

}
