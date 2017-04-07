package com.teamacronymcoders.base.subblocksystem.blocks;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import static com.teamacronymcoders.base.Reference.MODID;

public abstract class SubBlockBase implements ISubBlock {
    private String name;
    private ResourceLocation textureLocation;

    public SubBlockBase(String name) {
        this.name = name;
        this.textureLocation = new ResourceLocation(MODID, name);
    }

    public String getName() {
        return this.name;
    }

    public String getLocalizedName() {
        return I18n.format("base.subblock." + name);
    }

    public ResourceLocation getTextureLocation() {
        return this.textureLocation;
    }

    public int getColor() {
        return -1;
    }
}
