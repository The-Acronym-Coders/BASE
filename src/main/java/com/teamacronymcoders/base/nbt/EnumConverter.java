package com.teamacronymcoders.base.nbt;


import com.teamacronymcoders.base.nbt.converters.*;

/**
 * Created by Jared on 7/26/2016.
 */
public enum EnumConverter {
    BLOCKPOS(NBTBlockPosConverter.class),
    BOOLEAN(NBTBooleanConverter.class),
    BYTEARR(NBTByteArrayConverter.class),
    BYTE(NBTByteConverter.class),
    DOUBLE(NBTDoubleConverter.class),
    FLOAT(NBTFloatConverter.class),
    FLUIDTANK(NBTFluidTankConverter.class),
    INT(NBTIntConverter.class),
    INTARR(NBTIntArrayConverter.class),
    ITEMSTACKHANDLER(NBTItemStackHandlerConverter.class),
    LONG(NBTLongConverter.class),
    SHORT(NBTShortConverter.class),
    STRING(NBTStringConverter.class),
    TAG(NBTTagConverter.class);


    Class<? extends INBTConverter> converter;

    EnumConverter(Class<? extends INBTConverter> converter) {
        this.converter = converter;
    }

    public Class<? extends INBTConverter> getConverter() {
        return converter;
    }
}
