package com.teamacronymcoders.base.nbt.converters;

import com.teamacronymcoders.base.nbt.INBTConverter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

/**
 * Created by Jared on 7/26/2016.
 */
public class NBTBlockPosConverter implements INBTConverter<BlockPos> {

    private String name;

    public NBTBlockPosConverter() {
    }

    @Override
    public NBTBlockPosConverter setKey(String key) {
        this.name = key;
        return this;
    }

    @Override
    public BlockPos convert(NBTTagCompound tag) {
        return BlockPos.fromLong(tag.getLong(name));
    }

    @Override
    public NBTTagCompound convert(NBTTagCompound tag, BlockPos value) {
        tag.setLong(name, value.toLong());
        return tag;
    }
}

