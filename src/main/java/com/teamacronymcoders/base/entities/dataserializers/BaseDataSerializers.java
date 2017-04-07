package com.teamacronymcoders.base.entities.dataserializers;

import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.util.ResourceLocation;

public class BaseDataSerializers {
    public static final DataSerializer<ResourceLocation> RESOURCE_LOCATION = new ResourceLocationDataSerializer();

    public static void registerSerializers() {
        DataSerializers.registerSerializer(RESOURCE_LOCATION);
    }
}
