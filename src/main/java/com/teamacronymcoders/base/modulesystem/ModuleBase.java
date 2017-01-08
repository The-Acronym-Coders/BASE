package com.teamacronymcoders.base.modulesystem;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.modulesystem.dependencies.IDependency;
import com.teamacronymcoders.base.modulesystem.proxies.IModuleProxy;
import com.teamacronymcoders.base.registry.BlockRegistry;
import com.teamacronymcoders.base.registry.EntityRegistry;
import com.teamacronymcoders.base.registry.IRegistryHolder;
import com.teamacronymcoders.base.registry.ItemRegistry;
import com.teamacronymcoders.base.registry.config.ConfigRegistry;
import com.teamacronymcoders.base.util.logging.ILogger;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public abstract class ModuleBase implements IModule {
    private boolean isActive = true;
    private ModuleHandler moduleHandler;
    private IRegistryHolder registryHolder;
    private IBaseMod mod;
    private IModuleProxy moduleProxy;

    @Override
    public List<IDependency> getDependencies(List<IDependency> dependencies) {
        return dependencies;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        this.configure(this.getConfigRegistry());
        this.registerBlocks(this.getConfigRegistry(), this.getBlockRegistry());
        this.registerItems(this.getConfigRegistry(), this.getItemRegistry());
        this.registerEntities(this.getConfigRegistry(), this.getEntityRegistry());
        if (this.getModuleProxy() != null) {
            this.getModuleProxy().preInit(event);
        }
    }

    @Override
    public void init(FMLInitializationEvent event) {
        if (this.getModuleProxy() != null) {
            this.getModuleProxy().init(event);
        }
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        if (this.getModuleProxy() != null) {
            this.getModuleProxy().postInit(event);
        }
    }

    public void configure(ConfigRegistry configRegistry) {

    }

    public void registerItems(ConfigRegistry configRegistry, ItemRegistry itemRegistry) {

    }

    public void registerBlocks(ConfigRegistry configRegistry, BlockRegistry blockRegistry) {

    }

    public void registerEntities(ConfigRegistry configRegistry, EntityRegistry entityRegistry) {

    }

    @Override
    @Nullable
    public String getClientProxyPath() {
        return "";
    }

    @Override
    @Nullable
    public String getServerProxyPath() {
        return "";
    }

    @Override
    @Nullable
    public IModuleProxy getModuleProxy() {
        return moduleProxy;
    }

    @Override
    public void setModuleProxy(IModuleProxy moduleProxy) {
        this.moduleProxy = moduleProxy;
    }

    @Override
    public boolean getIsActive() {
        return isActive;
    }

    @Override
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    @Override
    public boolean getActiveDefault() {
        return true;
    }

    @Override
    public IBaseMod getMod() {
        return this.mod;
    }

    @Override
    public void setMod(@Nonnull IBaseMod mod) {
        this.registryHolder = mod.getRegistryHolder();
        this.mod = mod;
    }

    @Override
    public void setModuleHandler(@Nonnull ModuleHandler moduleHandler) {
        this.moduleHandler = moduleHandler;
    }

    public ItemRegistry getItemRegistry() {
        return this.registryHolder.getRegistry(ItemRegistry.class, "ITEM");
    }

    public BlockRegistry getBlockRegistry() {
        return this.registryHolder.getRegistry(BlockRegistry.class, "BLOCK");
    }

    public EntityRegistry getEntityRegistry() {
        return this.registryHolder.getRegistry(EntityRegistry.class, "ENTITY");
    }

    public ConfigRegistry getConfigRegistry() {
        return this.registryHolder.getRegistry(ConfigRegistry.class, "CONFIG");
    }

    public ILogger getLogger() {
        return this.mod.getLogger();
    }

    public boolean isOtherModuleActive(String name) {
        return moduleHandler.isModuleEnabled(name);
    }
}
