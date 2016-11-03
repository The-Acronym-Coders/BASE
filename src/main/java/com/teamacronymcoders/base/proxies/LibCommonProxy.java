package com.teamacronymcoders.base.proxies;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.modulesystem.IModule;
import com.teamacronymcoders.base.modulesystem.IModuleProxy;
import com.teamacronymcoders.base.util.ClassLoading;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.List;

public class LibCommonProxy {
    private IBaseMod mod;

    public void addOBJDomain() {
        //Only add Client Side
    }

    public void setItemModel(Item item, int metadata, ResourceLocation location) {
        //Only set Client Side
    }

    public void setAllItemModels(Item item, List<ResourceLocation> locations) {
        //Only set Client Side
    }

    public void registerFluidModel(Block fluidBlock, final ResourceLocation loc) {
        //Only done Client Side
    }

    public IModuleProxy getModuleProxy(IModule module) {
        return getModuleProxy(module.getServerProxyPath());
    }

    @Nullable
    protected IModuleProxy getModuleProxy(String path) {
        IModuleProxy moduleProxy = null;

        if(path != null && !path.isEmpty()) {
            moduleProxy = ClassLoading.createInstanceOf(IModuleProxy.class, path);
        }

        return moduleProxy;
    }

    public IBaseMod getMod() {
        return this.mod;
    }

    public void setMod(IBaseMod mod) {
        this.mod = mod;
    }
}
