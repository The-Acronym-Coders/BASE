package com.teamacronymcoders.base;

import com.teamacronymcoders.base.client.models.SafeModelLoader;
import com.teamacronymcoders.base.guisystem.GuiHandler;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.modulesystem.ModuleHandler;
import com.teamacronymcoders.base.network.PacketHandler;
import com.teamacronymcoders.base.proxies.LibCommonProxy;
import com.teamacronymcoders.base.registrysystem.IRegistryHolder;
import com.teamacronymcoders.base.subblocksystem.SubBlockSystem;
import com.teamacronymcoders.base.util.logging.ILogger;
import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.Nullable;

public interface IBaseMod<T> {
    T getInstance();

    CreativeTabs getCreativeTab();

    String getID();

    String getName();

    String getVersion();

    String getPrefix();

    boolean hasConfig();

    String getConfigFolderName();

    LibCommonProxy getLibProxy();

    ILogger getLogger();

    GuiHandler getGuiHandler();

    PacketHandler getPacketHandler();

    IRegistryHolder getRegistryHolder();

    SafeModelLoader getModelLoader();

    ModuleHandler getModuleHandler();

    boolean addOBJDomain();

    @Nullable
    MaterialSystem getMaterialSystem();

    @Nullable
    SubBlockSystem getSubBlockSystem();
}
