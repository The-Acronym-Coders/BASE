package com.teamacronymcoders.base;

import com.teamacronymcoders.base.client.gui.GuiHandler;
import com.teamacronymcoders.base.network.PacketHandler;
import com.teamacronymcoders.base.proxies.LibCommonProxy;
import com.teamacronymcoders.base.util.ClassLoading;
import com.teamacronymcoders.base.util.logging.ILogger;
import com.teamacronymcoders.base.util.logging.ModLogger;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public abstract class BaseModFoundation<T extends BaseModFoundation> implements IBaseMod<T> {
    protected CreativeTabs creativeTab;
    protected ILogger logger;
    protected GuiHandler guiHandler;
    protected PacketHandler packetHandler;
    //protected ModuleHandler moduleHandler;
    //protected IRegistryHolder registryHolder;
    protected LibCommonProxy libProxy;
    private String modid;
    private String name;
    private String version;

    public BaseModFoundation(String modid, String name, String version, CreativeTabs creativeTab) {
        this.modid = modid;
        this.name = name;
        this.version = version;
        this.creativeTab = creativeTab;
        this.logger = new ModLogger(modid);
        this.packetHandler = new PacketHandler(modid);
    }

    public void preInit(FMLPreInitializationEvent event) {
        this.libProxy = ClassLoading.createProxy("com.teamacronymcoders.base.proxies.LibClientProxy",
                "com.teamacronymcoders.base.proxies.LibCommonProxy");
        this.getLibProxy().setMod(this);
        if (this.addOBJDomain()) {
            this.getLibProxy().addOBJDomain();
        }

        this.guiHandler = new GuiHandler(this);
        //this.registryHolder = new RegistryHolder(this, event.getModConfigurationDirectory());

        //this.moduleHandler = new ModuleHandler(this, event.getAsmData());
        //this.moduleHandler.setupModules();
        //this.moduleHandler.preInit(event);

        //this.getBoilerplateProxy().registerEvents();
        this.modPreInit(event);

        //this.getRegistryHolder().getAllRegistries().forEach((name, registry) -> registry.preInit());
    }

    public void modPreInit(FMLPreInitializationEvent event) {

    }

    public void init(FMLInitializationEvent event) {
        //this.moduleHandler.init(event);
        //this.getRegistryHolder().getAllRegistries().forEach((name, registry) -> registry.init());
    }

    public void postInit(FMLPostInitializationEvent event) {
        //moduleHandler.postInit(event);
        //this.getRegistryHolder().getAllRegistries().forEach((name, registry) -> registry.postInit());
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return this.creativeTab;
    }

    @Override
    public String getID() {
        return this.modid;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public String getPrefix() {
        return this.getID() + ":";
    }

    @Override
    public ILogger getLogger() {
        return this.logger;
    }

    @Override
    public GuiHandler getGuiHandler() {
        return this.guiHandler;
    }

    @Override
    public PacketHandler getPacketHandler() {
        return this.packetHandler;
    }

    /*
                @Override
                public IRegistryHolder getRegistryHolder() {
                    return registryHolder;
                }

                @Override
                public ModuleHandler getModuleHandler() {
                    return this.moduleHandler;
                }
            */
    @Override
    public LibCommonProxy getLibProxy() {
        return this.libProxy;
    }
}
