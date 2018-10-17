package com.teamacronymcoders.base.sound;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class CustomSoundEvent extends SoundEvent implements IHasSoundGroup {
    private final ResourceLocation soundName;
    private final boolean stream;
    private final String category;

    public CustomSoundEvent(ResourceLocation soundName) {
        this(soundName, null, false);
    }

    public CustomSoundEvent(ResourceLocation soundName, String category, boolean stream) {
        super(soundName);
        this.soundName = soundName;
        this.stream = stream;
        this.category = category;
    }

    @Override
    public String getName() {
        return soundName.toString();
    }

    @Override
    public SoundGroup getSoundGroup() {
        return new SoundGroup(category, new SoundGroupItem[] {
            new SoundGroupItem(soundName, stream)
        }, String.format("subtitle.%s.%s", soundName.getNamespace(), soundName.getNamespace().replace("/", ".")));
    }
}
