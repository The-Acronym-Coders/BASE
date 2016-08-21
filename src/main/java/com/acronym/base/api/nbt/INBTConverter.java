package com.acronym.base.api.nbt;

import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Jared on 7/26/2016.
 */
public interface INBTConverter<E> {

    E convert(NBTTagCompound tag);

    INBTConverter<E> setKey(String key);

    NBTTagCompound convert(NBTTagCompound tag, E value);
}
