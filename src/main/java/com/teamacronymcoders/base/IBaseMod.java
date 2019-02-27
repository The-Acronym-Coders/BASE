package com.teamacronymcoders.base;

import com.teamacronymcoders.base.guisystem.GuiHandler;
import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.modulesystem.ModuleHandler;
import com.teamacronymcoders.base.network.PacketHandler;
import com.teamacronymcoders.base.proxies.LibCommonProxy;
import com.teamacronymcoders.base.registrysystem.IRegistryHolder;
import com.teamacronymcoders.base.subblocksystem.SubBlockSystem;
import com.teamacronymcoders.base.util.logging.ILogger;
import net.minecraft.item.ItemGroup;

import javax.annotation.Nullable;
import java.io.File;
import java.util.List;

public interface IBaseMod<T> {
    T getInstance();

    @Nullable
    ItemGroup getItemGroup();

    String getID();

    String getName();

    boolean hasConfig();

    String getConfigFolderName();

    LibCommonProxy getLibProxy();

    ILogger getLogger();

    GuiHandler getGuiHandler();

    PacketHandler getPacketHandler();

    IRegistryHolder getRegistryHolder();

    ModuleHandler getModuleHandler();

    List<ModuleHandler> getOtherModuleHandlers();

    default boolean addOBJDomain() {
        return false;
    }

    default boolean hasExternalResources() {
        return false;
    }

    @Nullable
    MaterialUser getMaterialUser();

    @Nullable
    File getResourceFolder();
}
