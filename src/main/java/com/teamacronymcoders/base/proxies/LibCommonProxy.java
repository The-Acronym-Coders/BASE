package com.teamacronymcoders.base.proxies;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.blocks.IHasBlockColor;
import com.teamacronymcoders.base.blocks.IHasBlockStateMapper;
import com.teamacronymcoders.base.guisystem.network.PacketOpenGui;
import com.teamacronymcoders.base.guisystem.target.GuiTargetBase;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.items.IHasItemColor;
import com.teamacronymcoders.base.modulesystem.IModule;
import com.teamacronymcoders.base.modulesystem.proxies.IModuleProxy;
import com.teamacronymcoders.base.util.ClassLoading;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

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

    public abstract void openGui(@Nonnull GuiTargetBase guiTarget, @Nonnull NBTTagCompound context, boolean openGuiFromServer,
                                 EntityPlayer entityPlayer, World world);

    public void openGuiFromPacket(PacketOpenGui message, MessageContext ctx) {
        //Only done Client Side
    }

    public IBaseMod getMod() {
        return this.mod;
    }

    public void setMod(IBaseMod mod) {
        this.mod = mod;
    }

    public void registerItemColor(Item item, IHasItemColor itemColor) {
        //Only done Client Side
    }

    public void registerItemColor(Block block, IHasItemColor itemColor) {
        //Only done Client Side
    }

    public void registerBlockColor(IHasBlockColor blockColor) {
        //Only done Client Side
    }

    public void registerBlockStateMapper(Block block, IHasBlockStateMapper stateMapper) {
        //Only done Client Side
    }
}
