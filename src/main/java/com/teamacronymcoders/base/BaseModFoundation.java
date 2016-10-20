package com.teamacronymcoders.base;

import com.teamacronymcoders.base.client.gui.GuiHandler;
import com.teamacronymcoders.base.client.models.SafeModelLoader;
import com.teamacronymcoders.base.network.PacketHandler;
import com.teamacronymcoders.base.proxies.LibCommonProxy;
import com.teamacronymcoders.base.registry.*;
import com.teamacronymcoders.base.util.ClassLoading;
import com.teamacronymcoders.base.util.logging.ILogger;
import com.teamacronymcoders.base.util.logging.ModLogger;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Map;

public abstract class BaseModFoundation<T extends BaseModFoundation> implements IBaseMod<T>, IRegistryHolder {
    protected CreativeTabs creativeTab;
    protected ILogger logger;
    protected GuiHandler guiHandler;
    protected PacketHandler packetHandler;
    protected Map<String, Registry> registries;
    protected SafeModelLoader modelLoader;
    //protected ModuleHandler moduleHandler;
    protected LibCommonProxy libProxy;
    private String modid;
    private String modName;
    private String version;

    public BaseModFoundation(String modid, String name, String version, CreativeTabs creativeTab) {
        this.modid = modid;
        this.modName = name;
        this.version = version;
        this.creativeTab = creativeTab;
        this.logger = new ModLogger(modid);
        this.packetHandler = new PacketHandler(modid);
    }

    public void preInit(FMLPreInitializationEvent event) {
        this.libProxy = ClassLoading.createProxy("com.teamacronymcoders.base.proxies.LibClientProxy",
                "com.teamacronymcoders.base.proxies.LibCommonProxy");
        this.getLibProxy().setMod(this);
        this.modelLoader = new SafeModelLoader(this);

        this.addRegistry("BLOCK", new BlockRegistry(this));
        this.addRegistry("ITEM", new ItemRegistry(this));
        this.addRegistry("ENTITY", new EntityRegistry(this));

        if (this.addOBJDomain()) {
            this.getLibProxy().addOBJDomain();
        }

        this.guiHandler = new GuiHandler(this);

        //this.moduleHandler = new ModuleHandler(this, event.getAsmData());
        //this.moduleHandler.setupModules();
        //this.moduleHandler.preInit(event);

        this.modPreInit(event);

        this.getAllRegistries().forEach((name, registry) -> registry.preInit());
    }

    public void modPreInit(FMLPreInitializationEvent event) {

    }

    public void init(FMLInitializationEvent event) {
        //this.moduleHandler.init(event);
        this.getAllRegistries().forEach((name, registry) -> registry.init());
    }

    public void postInit(FMLPostInitializationEvent event) {
        //moduleHandler.postInit(event);
        this.getAllRegistries().forEach((name, registry) -> registry.postInit());
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
        return this.modName;
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

    @Override
    public IRegistryHolder getRegistryHolder() {
        return this;
    }

    @Override
    public LibCommonProxy getLibProxy() {
        return this.libProxy;
    }

    @Override
    public SafeModelLoader getModelLoader() {
        return this.modelLoader;
    }

    @Override
    public Map<String, Registry> getAllRegistries() {
        return this.registries;
    }

    @Override
    public void addRegistry(String name, Registry registry) {
        this.registries.put(name, registry);
    }

    @Override
    public <R extends Registry> R getRegistry(Class<R> clazz, String name) {
        Registry registry = registries.get(name);

        if (clazz.isInstance(registry)) {
            return clazz.cast(registry);
        }

        return null;
    }
}
