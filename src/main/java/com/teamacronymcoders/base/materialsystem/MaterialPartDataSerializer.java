package com.teamacronymcoders.base.materialsystem;

import com.teamacronymcoders.base.entities.dataserializers.ResourceLocationDataSerializer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.io.IOException;

public class MaterialPartDataSerializer implements DataSerializer<MaterialPart> {
    @Override
    public void write(@Nonnull PacketBuffer buf, @Nonnull MaterialPart value) {
        buf.writeString(value.getRegistryName().getResourceDomain());
        buf.writeString(value.getRegistryName().getResourcePath());
    }

    @Override
    @Nonnull
    public MaterialPart read(@Nonnull PacketBuffer buf) throws IOException {
        ResourceLocation resourceLocation = new ResourceLocation(buf.readString(256),
                buf.readString(256));
        return MaterialsSystem.MATERIAL_PARTS.getValue(resourceLocation);
    }

    @Override
    @Nonnull
    public DataParameter<MaterialPart> createKey(int id) {
        return new DataParameter<>(id, this);
    }
}
