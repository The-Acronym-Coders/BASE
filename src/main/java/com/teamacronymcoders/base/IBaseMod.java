package com.teamacronymcoders.base;

import com.teamacronymcoders.base.client.models.SafeModelLoader;
import com.teamacronymcoders.base.guisystem.GuiHandler;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.modulesystem.ModuleHandler;
import com.teamacronymcoders.base.network.PacketHandler;
import com.teamacronymcoders.base.proxies.LibCommonProxy;
import com.teamacronymcoders.base.registrysystem.IRegistryHolder;
import com.teamacronymcoders.base.subblocksystem.SubBlockSystem;
import com.teamacronymcoders.base.util.logging.ILogger;
import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.Nullable;
import java.io.File;

public interface IBaseMod<T> {
    T getInstance();

    @Nullable
    CreativeTabs getCreativeTab();

    String getID();

    String getName();

    String getVersion();

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

    default boolean hasExternalResources() {
        return false;
    }

    @Nullable
    MaterialUser getMaterialUser();

    @Nullable
    SubBlockSystem getSubBlockSystem();

    @Nullable
    File getResourceFolder();
}
