package com.teamacronymcoders.base.modulesystem;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.modulesystem.dependencies.IDependency;
import com.teamacronymcoders.base.registry.IRegistryHolder;
import com.teamacronymcoders.base.registry.config.ConfigEntry;
import com.teamacronymcoders.base.registry.config.ConfigRegistry;
import com.teamacronymcoders.base.util.ClassLoading;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

public class ModuleHandler {
    private SortedMap<String, IModule> modules = new TreeMap<>();
    private IRegistryHolder registryHolder;
    private IBaseMod mod;
    private String handlerName;

    public ModuleHandler(IBaseMod mod, ASMDataTable asmDataTable) {
        this(mod.getID(), mod, asmDataTable);
    }

    public ModuleHandler(String handlerName, IBaseMod mod, ASMDataTable asmDataTable) {
        this.mod = mod;
        this.handlerName = handlerName;
        this.registryHolder = mod.getRegistryHolder();
        this.modules = this.getModules(asmDataTable);
    }

    public void preInit(FMLPreInitializationEvent event) {
        this.modules.values().stream().filter(IModule::getIsActive).forEachOrdered(module -> module.preInit(event));
    }

    public void init(FMLInitializationEvent event) {
        this.modules.values().stream().filter(IModule::getIsActive).forEachOrdered(module -> module.init(event));
    }

    public void postInit(FMLPostInitializationEvent event) {
        this.modules.values().stream().filter(IModule::getIsActive).forEachOrdered(module -> module.postInit(event));
    }

    public void setupModules() {
        for (IModule module : getModules().values()) {
            if(module.isConfigurable()) {
                this.getConfig().addEntry(module.getName(), new ModuleConfigEntry(module));
                module.setIsActive(this.getConfig().getBoolean(module.getName(), module.getActiveDefault()));
            }

            module.setMod(this.mod);
            module.setModuleHandler(this);
        }

        this.modules.values().stream().filter(IModule::getIsActive).forEach(this::checkDependencies);
        this.modules.values().stream().filter(IModule::getIsActive).forEach(this::setProxy);
    }

    private void checkDependencies(IModule module) {
        module.getDependencies(new ArrayList<>())
                .forEach(dependency -> printModuleDependencyOutcome(module, dependency));
        if (module.getIsActive()) {
            this.mod.getLogger().info("Module " + module.getName() + " has successfully loaded");
        }
    }

    private void printModuleDependencyOutcome(IModule module, IDependency dependency) {
        if (!dependency.isMet(this)) {
            this.mod.getLogger().error("Module " + module.getName() + " did not load due to issue: " +
                    dependency.notMetMessage());
            module.setIsActive(false);
        }
    }

    private void setProxy(IModule module) {
        module.setModuleProxy(mod.getLibProxy().getModuleProxy(module));
    }

    public SortedMap<String, IModule> getModules() {
        return modules;
    }

    public void addModule(IModule module) {
        modules.put(module.getName(), module);
    }

    public IModule getModule(String name) {
        return modules.get(name);
    }

    public boolean isModuleEnabled(String name) {
        return getModule(name) != null && getModule(name).getIsActive();
    }

    private ConfigRegistry getConfig() {
        return this.registryHolder.getRegistry(ConfigRegistry.class, "CONFIG");
    }

    private TreeMap<String, IModule> getModules(@Nonnull ASMDataTable asmDataTable) {
        TreeMap<String, IModule> moduleMap = new TreeMap<>(new ModuleComparator(this));
        ClassLoading.getInstances(asmDataTable, Module.class, IModule.class, aClass -> {
            Module moduleAnnotation = aClass.getAnnotation(Module.class);
            boolean load = false;
            if(moduleAnnotation != null) {
                String modid = moduleAnnotation.value().trim();
                load = modid.equalsIgnoreCase("") || modid.equalsIgnoreCase(handlerName);
                load &= this.mod.getLibProxy().isRightSide(moduleAnnotation.side());
            }

            return load;
        }).forEach(module -> {
            if(!moduleMap.containsKey(module.getName())) {
                moduleMap.put(module.getName(), module);
            } else {
                throw new UnsupportedOperationException("Module Names must be Unique");
            }
        });
        return moduleMap;
    }

    private static class ModuleConfigEntry extends ConfigEntry {
        public ModuleConfigEntry(IModule module) {
            super("Module", module.getName() + " enabled", Property.Type.BOOLEAN, module.getActiveDefault() + "");
        }
    }
}
