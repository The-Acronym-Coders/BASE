package com.teamacronymcoders.base.proxies;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.guisystem.target.GuiTarget;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.modulesystem.IModule;
import com.teamacronymcoders.base.modulesystem.proxies.IModuleProxy;
import com.teamacronymcoders.base.util.ClassLoading;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class LibCommonProxy {
    private IBaseMod mod;

    public void addOBJDomain() {
        //Only add Client Side
    }

    public void setItemModel(Item item, int metadata, ResourceLocation location) {
        //Only set Client Side
    }

    public void setAllItemModels(Item item, IHasModel model) {
        //Only set Client Side
    }

    public void registerFluidModel(Block fluidBlock, final ResourceLocation loc) {
        //Only done Client Side
    }

    public abstract IModuleProxy getModuleProxy(IModule module);

    @Nullable
    protected IModuleProxy getModuleProxy(String path) {
        IModuleProxy moduleProxy = null;

        if (path != null && !path.isEmpty()) {
            moduleProxy = ClassLoading.createInstanceOf(IModuleProxy.class, path);
        }

        return moduleProxy;
    }

    public abstract void openGui(@Nonnull GuiTarget guiTarget, @Nonnull NBTTagCompound context, boolean openGuiFromServer,
                                 EntityPlayer entityPlayer, World world);

    public IBaseMod getMod() {
        return this.mod;
    }

    public void setMod(IBaseMod mod) {
        this.mod = mod;
    }
}
