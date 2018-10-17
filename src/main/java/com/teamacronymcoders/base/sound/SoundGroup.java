package com.teamacronymcoders.base.sound;

public class SoundGroup {
    public final String category;
    public final SoundGroupItem[] sounds;
    public final String subtitle;

    public SoundGroup(String category, SoundGroupItem[] sounds, String subtitle) {
        this.category = category;
        this.sounds = sounds;
        this.subtitle = subtitle;
    }
}
