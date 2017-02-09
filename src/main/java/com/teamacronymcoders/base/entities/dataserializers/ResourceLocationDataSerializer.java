package com.teamacronymcoders.base.entities.dataserializers;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.io.IOException;

public class ResourceLocationDataSerializer implements DataSerializer<ResourceLocation> {
    @Override
    public void write(@Nonnull PacketBuffer buf,@Nonnull ResourceLocation value) {
        buf.writeString(value.getResourceDomain());
        buf.writeString(value.getResourcePath());
    }

    @Override
    @Nonnull
    public ResourceLocation read(@Nonnull PacketBuffer buf) throws IOException {
        return new ResourceLocation(buf.readStringFromBuffer(256), buf.readStringFromBuffer(256));
    }

    @Override
    @Nonnull
    public DataParameter<ResourceLocation> createKey(int id) {
        return new DataParameter<>(id, this);
    }
}
