package com.teamacronymcoders.base.modules.jei;

import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import com.teamacronymcoders.base.modulesystem.dependencies.IDependency;
import com.teamacronymcoders.base.modulesystem.dependencies.ModDependency;

import java.util.List;

import static com.teamacronymcoders.base.reference.Reference.MODID;

@Module(MODID)
public class JEIModule extends ModuleBase {
    @Override
    public String getName() {
        return "JEI";
    }

    @Override
    public List<IDependency> getDependencies(List<IDependency> dependencies) {
        dependencies.add(new ModDependency("JEI"));
        return dependencies;
    }
}
