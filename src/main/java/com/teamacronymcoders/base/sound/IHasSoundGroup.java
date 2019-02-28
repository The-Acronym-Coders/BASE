package com.teamacronymcoders.base.sound;

import net.minecraft.util.ResourceLocation;

public interface IHasSoundGroup {
    ResourceLocation getName();

    SoundGroup getSoundGroup();
}
