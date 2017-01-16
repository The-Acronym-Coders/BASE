package com.teamacronymcoders.base.modulesystem.dependencies;

import com.teamacronymcoders.base.modulesystem.ModuleHandler;
import net.minecraftforge.fml.common.Loader;

public class ModDependency implements IDependency {
    private String modid;
    private boolean silent;

    public ModDependency(String modid) {
        this(modid, false);
    }

    public ModDependency(String modid, boolean silent) {
        this.modid = modid;
        this.silent = silent;
    }

    @Override
    public boolean isSilent() {
        return this.silent;
    }

    @Override
    public boolean isMet(ModuleHandler moduleHandler) {
        return Loader.isModLoaded(this.getModid());
    }

    @Override
    public String notMetMessage() {
        return "Mod with modid: " + this.getModid() + " was not found";
    }

    public String getModid() {
        return modid;
    }
}
