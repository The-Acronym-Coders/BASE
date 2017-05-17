package com.teamacronymcoders.base.materialsystem.parttype;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.json.resources.IResource;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;

import javax.annotation.Nonnull;

public class PartType {
    private final MaterialSystem materialSystem;
    private final IBaseMod mod;
    private String name;

    public PartType(@Nonnull String name, IBaseMod mod) {
        this.name = name;
        this.mod = mod;
        this.materialSystem = mod.getMaterialSystem();
    }

    @Nonnull
    public String getName() {
        return this.name;
    }

    public void setup(@Nonnull MaterialPart materialPart) {
    }

    public IResource createJson(MaterialPart materialPart) {
        return null;
    }

    protected IBaseMod getMod() {
        return mod;
    }

    protected MaterialSystem getMaterialSystem() {
        return materialSystem;
    }
}
