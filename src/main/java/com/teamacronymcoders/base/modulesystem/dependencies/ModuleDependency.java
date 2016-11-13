package com.teamacronymcoders.base.modulesystem.dependencies;

import com.teamacronymcoders.base.modulesystem.ModuleHandler;

public class ModuleDependency implements IDependency {
    private String moduleName;

    public ModuleDependency(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public boolean isMet(ModuleHandler moduleHandler) {
        return moduleHandler.isModuleEnabled(this.getModuleName());
    }

    @Override
    public String notMetMessage() {
        return "Module with name: " + getModuleName() + " is not active";
    }

    public String getModuleName() {
        return moduleName;
    }
}
