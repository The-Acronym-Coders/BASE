package com.teamacronymcoders.base.sound;

public class SoundGroup {
    public final SoundGroupItem[] sounds;
    public final String subtitle;

    public SoundGroup(SoundGroupItem[] sounds, String subtitle) {
        this.sounds = sounds;
        this.subtitle = subtitle;
    }
}
