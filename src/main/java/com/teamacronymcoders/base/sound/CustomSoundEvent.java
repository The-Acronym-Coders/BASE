package com.teamacronymcoders.base.sound;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class CustomSoundEvent extends SoundEvent implements IHasSoundGroup {
    private final ResourceLocation soundName;
    private final boolean stream;

    public CustomSoundEvent(ResourceLocation soundName) {
        this(soundName, false);
    }

    public CustomSoundEvent(ResourceLocation soundName, boolean stream) {
        super(soundName);
        this.setRegistryName(soundName);
        this.soundName = soundName;
        this.stream = stream;
    }

    @Override
    public String getName() {
        return soundName.getPath().replace("/", ".");
    }

    @Override
    public SoundGroup getSoundGroup() {
        return new SoundGroup(new SoundGroupItem[] {
            new SoundGroupItem(soundName.toString(), stream)
        }, String.format("subtitle.%s.%s", soundName.getNamespace(), soundName.getPath().replace("/", ".")));
    }
}
