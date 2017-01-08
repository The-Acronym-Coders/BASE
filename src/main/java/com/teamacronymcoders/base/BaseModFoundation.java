package com.teamacronymcoders.base;

import com.teamacronymcoders.base.client.models.SafeModelLoader;
import com.teamacronymcoders.base.guisystem.GuiHandler;
import com.teamacronymcoders.base.modulesystem.ModuleHandler;
import com.teamacronymcoders.base.network.PacketHandler;
import com.teamacronymcoders.base.proxies.LibCommonProxy;
import com.teamacronymcoders.base.registry.*;
import com.teamacronymcoders.base.registry.config.ConfigRegistry;
import com.teamacronymcoders.base.registry.pieces.IRegistryPiece;
import com.teamacronymcoders.base.registry.pieces.RegistryPiece;
import com.teamacronymcoders.base.registry.pieces.RegistrySide;
import com.teamacronymcoders.base.savesystem.SaveLoader;
import com.teamacronymcoders.base.util.ClassLoading;
import com.teamacronymcoders.base.util.logging.ILogger;
import com.teamacronymcoders.base.util.logging.ModLogger;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public abstract class BaseModFoundation<T extends BaseModFoundation> implements IBaseMod<T>, IRegistryHolder {
    protected CreativeTabs creativeTab;
    protected ILogger logger;
    protected GuiHandler guiHandler;
    protected PacketHandler packetHandler;
    protected Map<String, Registry> registries;
    protected SafeModelLoader modelLoader;
    protected ModuleHandler moduleHandler;
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
        BaseMods.addBaseMod(this);
        this.libProxy = ClassLoading.createProxy("com.teamacronymcoders.base.proxies.LibClientProxy",
                "com.teamacronymcoders.base.proxies.LibServerProxy");
        this.getLibProxy().setMod(this);
        this.modelLoader = new SafeModelLoader(this);

        List<IRegistryPiece> registryPieces = this.getRegistryPieces(event.getAsmData());

        this.addRegistry("BLOCK", new BlockRegistry(this, registryPieces));
        this.addRegistry("ITEM", new ItemRegistry(this, registryPieces));
        this.addRegistry("ENTITY", new EntityRegistry(this, registryPieces));
        this.addRegistry("CONFIG", new ConfigRegistry(this, event.getModConfigurationDirectory(), this.useModAsConfigFolder()));

        SaveLoader.setConfigFolder(this.getRegistry(ConfigRegistry.class, "CONFIG").getTacFolder());

        if (this.addOBJDomain()) {
            this.getLibProxy().addOBJDomain();
        }

        this.guiHandler = new GuiHandler(this, event.getAsmData());

        this.beforeModuleHandlerInit(event);

        this.moduleHandler = new ModuleHandler(this, event.getAsmData());
        this.getModuleHandler().setupModules();
        this.getModuleHandler().preInit(event);

        this.afterModuleHandlerInit(event);

        this.getAllRegistries().forEach((name, registry) -> registry.preInit());
    }


    public void beforeModuleHandlerInit(FMLPreInitializationEvent event) {

    }

    public void afterModuleHandlerInit(FMLPreInitializationEvent event) {

    }

    public void init(FMLInitializationEvent event) {
        this.getModuleHandler().init(event);
        this.getAllRegistries().forEach((name, registry) -> registry.init());
    }

    public void postInit(FMLPostInitializationEvent event) {
        this.getModuleHandler().postInit(event);
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
    public String getConfigFolderName() {
        return this.getID().toUpperCase(Locale.ENGLISH);
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
    public ModuleHandler getModuleHandler() {
        return this.moduleHandler;
    }

    public boolean useModAsConfigFolder() {
        return true;
    }

    @Override
    public Map<String, Registry> getAllRegistries() {
        return this.registries;
    }

    @Override
    public void addRegistry(String name, Registry registry) {
        if (this.registries == null) {
            this.registries = new HashMap<>();
        }
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

    private List<IRegistryPiece> getRegistryPieces(ASMDataTable asmData) {
        List<IRegistryPiece> registryPieces;
        registryPieces = ClassLoading.getInstances(asmData, RegistryPiece.class, IRegistryPiece.class, aClass -> {
            RegistryPiece registryPiece = aClass.getAnnotation(RegistryPiece.class);
            RegistrySide side = registryPiece.value();
            boolean load = this.getLibProxy().isRightSide(side);
            if(load && !StringUtils.isNullOrEmpty(registryPiece.modid())) {
                load = Loader.isModLoaded(registryPiece.modid());
            }
            return load;
        });
        registryPieces.forEach(registryPiece -> {
            if(registryPiece instanceof IModAware) {
                ((IModAware) registryPiece).setMod(this);
            }
        });
        return registryPieces;
    }
}
