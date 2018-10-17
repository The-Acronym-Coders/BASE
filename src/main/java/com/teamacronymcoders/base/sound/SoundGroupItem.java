package com.teamacronymcoders.base.sound;

import net.minecraft.util.ResourceLocation;

public class SoundGroupItem {
    public final ResourceLocation name;
    public final Boolean stream;

    public SoundGroupItem(ResourceLocation name, Boolean stream) {
        this.name = name;
        this.stream = stream;
    }
}
