package com.teamacronymcoders.base.entities.dataserializers;

import net.minecraft.network.datasync.DataSerializers;

public class BaseDataSerializers {
    public static final ResourceLocationDataSerializer RESOURCE_LOCATION = new ResourceLocationDataSerializer();

    public static void registerSerializers() {
        DataSerializers.registerSerializer(RESOURCE_LOCATION);
    }
}
