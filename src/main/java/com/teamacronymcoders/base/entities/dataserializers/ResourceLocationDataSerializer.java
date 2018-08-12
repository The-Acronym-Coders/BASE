package com.teamacronymcoders.base.entities.dataserializers;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.io.IOException;

public class ResourceLocationDataSerializer implements DataSerializer<ResourceLocation> {
    @Override
    public void write(@Nonnull PacketBuffer buf, @Nonnull ResourceLocation value) {
        buf.writeString(value.getNamespace());
        buf.writeString(value.getPath());
    }

    @Override
    @Nonnull
    public ResourceLocation read(@Nonnull PacketBuffer buf) throws IOException {
        return new ResourceLocation(buf.readString(256), buf.readString(256));
    }

    @Override
    @Nonnull
    public DataParameter<ResourceLocation> createKey(int id) {
        return new DataParameter<>(id, this);
    }

    @Override
    @Nonnull
    public ResourceLocation copyValue(@Nonnull ResourceLocation value) {
        return new ResourceLocation(value.getNamespace(), value.getPath());
    }
}
