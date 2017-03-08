package com.teamacronymcoders.base.modulesystem.dependencies;

import com.teamacronymcoders.base.BaseMods;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.modulesystem.ModuleHandler;

public class ModModuleDependency extends ModDependency {
    private IBaseMod mod;
    private String modid;
    private String moduleName;

    public ModModuleDependency(String modid, String moduleName) {
        super(modid);
        this.mod = BaseMods.getBaseMod(modid);
        this.moduleName = moduleName;
    }

    @Override
    public boolean isMet(ModuleHandler moduleHandler) {
        return super.isMet(moduleHandler) && this.getMod() != null &&
                this.getMod().getModuleHandler().isModuleEnabled(this.getModuleName());
    }

    @Override
    public String notMetMessage() {
        return this.getMod() == null ? super.notMetMessage() : "Module " + this.getModuleName() + " from "
                + this.getModid() + " is not active.";
    }

    public IBaseMod getMod() {
        return mod;
    }

    public String getModuleName() {
        return moduleName;
    }
}
