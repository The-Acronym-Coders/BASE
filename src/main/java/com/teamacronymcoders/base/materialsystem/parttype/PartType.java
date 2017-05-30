package com.teamacronymcoders.base.materialsystem.parttype;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

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

    protected IBaseMod getMod() {
        return mod;
    }

    protected MaterialSystem getMaterialSystem() {
        return materialSystem;
    }
}
