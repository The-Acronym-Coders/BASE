package com.teamacronymcoders.base;

import com.teamacronymcoders.base.proxies.LibCommonProxy;
import com.teamacronymcoders.base.util.logging.ILogger;
import net.minecraft.creativetab.CreativeTabs;

public interface IBaseMod<T> {
    T getInstance();

    CreativeTabs getCreativeTab();

    String getID();

    String getName();

    String getVersion();

    String getPrefix();

    LibCommonProxy getLibProxy();

    ILogger getLogger();

    //GuiHandler getGuiHandler();

    //PacketHandler getPacketHandler();

    //IRegistryHolder getRegistryHolder();

    //ModuleHandler getModuleHandler();

    default boolean addOBJDomain() {
        return false;
    }
}
