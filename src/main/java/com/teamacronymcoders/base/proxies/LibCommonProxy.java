package com.teamacronymcoders.base.proxies;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.registrysystem.pieces.RegistrySide;
import net.minecraft.util.ResourceLocation;

public abstract class LibCommonProxy {
    private IBaseMod mod;

    public void addOBJDomain() {
        // Only add Client RegistrySide
    }

    public void addSidedBlockDomain() {

    }

    public RegistrySide getRegistrySide() {
        return RegistrySide.BOTH;
    }

    public boolean isRightSide(RegistrySide side) {
        // Check both and getRegistrySide since it can be overrode to not be both
        return side == RegistrySide.BOTH || side == this.getRegistrySide();
    }

    public IBaseMod getMod() {
        return this.mod;
    }

    public void setMod(IBaseMod mod) {
        this.mod = mod;
    }

    public String getFileContents(ResourceLocation resourceLocation) {
        return "";
    }

    public void createResourceLoader(String modid) {

    }

    public void handleSounds() {

    }
}
