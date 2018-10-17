package com.teamacronymcoders.base.registrysystem;

import com.teamacronymcoders.base.IBaseMod;
import net.minecraft.util.SoundEvent;

public class SoundEventRegistry extends Registry<SoundEvent> {
    public SoundEventRegistry(IBaseMod mod) {
        super("SOUND_EVENT", mod);
    }
}
