package com.teamacronymcoders.base.modulesystem;

import com.teamacronymcoders.base.modulesystem.dependencies.IDependency;
import com.teamacronymcoders.base.modulesystem.dependencies.ModuleDependency;
import com.teamacronymcoders.base.util.Platform;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ModuleComparator implements Comparator<String> {
    private ModuleHandler moduleHandler;

    //Because TreeMaps sort by key, not value...
    public ModuleComparator(ModuleHandler moduleHandler) {
        this.moduleHandler = moduleHandler;
    }

    @Override
    public int compare(String moduleName1, String moduleName2) {
        int compareResult = 0;
        IModule module1 = moduleHandler.getModule(moduleName1);
        IModule module2 = moduleHandler.getModule(moduleName2);

        List<IDependency> module1Deps = new ArrayList<>();
        List<IDependency> module2Deps = new ArrayList<>();

        if (module1 != null) {
            module1Deps = module1.getDependencies(module1Deps);
        } else {
            compareResult = 1;
        }

        if (module2 != null) {
            module2Deps = module2.getDependencies(module2Deps);
        } else {
            compareResult = -1;
        }

        if (compareResult == 0) {
            boolean module1IsDepTo2 = false;
            boolean module2IsDepTo1 = false;

            for (IDependency dependency : module1Deps) {
                if (dependency instanceof ModuleDependency) {
                    module1IsDepTo2 |= ((ModuleDependency) dependency).getModuleName().equals(module2.getName());
                }
            }

            for (IDependency dependency : module2Deps) {
                if (dependency instanceof ModuleDependency) {
                    module2IsDepTo1 |= ((ModuleDependency) dependency).getModuleName().equals(module1.getName());
                }
            }

            if (module1IsDepTo2 && module2IsDepTo1) {
                Platform.attemptLogErrorToCurrentMod("CIRCULAR DEPENDENCIES FOUND. THINKS MAY GO WRONG");
            } else if (module1IsDepTo2) {
                compareResult = 1;
            } else if (module2IsDepTo1) {
                compareResult = -1;
            }
        }

        return compareResult;
    }
}
