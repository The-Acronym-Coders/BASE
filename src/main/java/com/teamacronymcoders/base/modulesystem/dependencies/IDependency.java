package com.teamacronymcoders.base.modulesystem.dependencies;


import com.teamacronymcoders.base.modulesystem.ModuleHandler;

public interface IDependency {
    boolean isMet(ModuleHandler moduleHandler);

    String notMetMessage();
}
