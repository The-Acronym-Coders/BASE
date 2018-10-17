package com.teamacronymcoders.base;

import com.teamacronymcoders.base.guisystem.GuiHandler;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.modulesystem.ModuleHandler;
import com.teamacronymcoders.base.network.PacketHandler;
import com.teamacronymcoders.base.proxies.LibCommonProxy;
import com.teamacronymcoders.base.registrysystem.*;
import com.teamacronymcoders.base.registrysystem.config.ConfigRegistry;
import com.teamacronymcoders.base.registrysystem.pieces.IRegistryPiece;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPiece;
import com.teamacronymcoders.base.registrysystem.pieces.RegistrySide;
import com.teamacronymcoders.base.savesystem.SaveLoader;
import com.teamacronymcoders.base.subblocksystem.SubBlockSystem;
import com.teamacronymcoders.base.util.ClassLoading;
import com.teamacronymcoders.base.util.OreDictUtils;
import com.teamacronymcoders.base.util.Platform;
import com.teamacronymcoders.base.util.logging.ILogger;
import com.teamacronymcoders.base.util.logging.ModLogger;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.StringUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import javax.annotation.Nullable;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseModFoundation<T extends BaseModFoundation> implements IBaseMod<T>, IRegistryHolder {
    protected CreativeTabs creativeTab;
    protected ILogger logger;
    protected GuiHandler guiHandler;
    protected PacketHandler packetHandler;
    protected Map<String, Registry> registries;
    protected ModuleHandler moduleHandler;
    protected LibCommonProxy libProxy;
    protected MaterialUser materialUser;
    protected SubBlockSystem subBlockSystem;
    private String modid;
    private String modName;
    private String version;
    private File resourceFolder;
    private File minecraftFolder;

    public BaseModFoundation(String modid, String name, String version, CreativeTabs creativeTab) {
        this(modid, name, version, creativeTab, false);
    }

    public BaseModFoundation(String modid, String name, String version, CreativeTabs creativeTab, boolean optionalSystems) {
        this.modid = modid;
        this.modName = name;
        this.version = version;
        this.creativeTab = creativeTab;
        this.logger = new ModLogger(modid);
        this.packetHandler = new PacketHandler(modid);
        if (optionalSystems) {
            materialUser = new MaterialUser(this);
            subBlockSystem = new SubBlockSystem(this);
            OreDictUtils.addDefaultModId(modid);
        }

        this.libProxy = ClassLoading.createProxy("com.teamacronymcoders.base.proxies.LibClientProxy",
                "com.teamacronymcoders.base.proxies.LibServerProxy");
        this.getLibProxy().setMod(this);

        if (hasExternalResources()) {
            this.libProxy.createResourceLoader(modid);
        }
    }

    public void preInit(FMLPreInitializationEvent event) {
        BaseMods.addBaseMod(this);

        this.minecraftFolder = event.getModConfigurationDirectory().getParentFile();
        this.resourceFolder = new File(minecraftFolder, "resources");

        this.createRegistries(event, this.getRegistryPieces(event.getAsmData()));
        if (this.useDefaultRegistryEventHandler()) {
            MinecraftForge.EVENT_BUS.register(new RegistryEventHandler(this));
        }

        this.registerBlocks(this.getRegistry(BlockRegistry.class, "BLOCK"));
        this.registerItems(this.getRegistry(ItemRegistry.class, "ITEM"));

        if (this.getMaterialUser() != null) {
            MaterialSystem.setup(this.getMaterialUser(), event.getAsmData());
            this.getMaterialUser().setup();
        }
        if (this.addOBJDomain()) {
            this.getLibProxy().addOBJDomain();
        }

        this.guiHandler = new GuiHandler(this);

        this.beforeModuleHandlerInit(event);

        this.moduleHandler = new ModuleHandler(this, event.getAsmData());
        this.getModuleHandler().setupModules();
        this.getOtherModuleHandlers().forEach(ModuleHandler::setupModules);
        this.getModuleHandler().preInit(event);
        this.getOtherModuleHandlers().forEach(otherHandler -> otherHandler.preInit(event));

        this.afterModuleHandlerInit(event);
        this.finalizeOptionalSystems();

        this.getAllRegistries().forEach((name, registry) -> registry.preInit());
    }

    public void createRegistries(FMLPreInitializationEvent event, List<IRegistryPiece> registryPieces) {
        this.addRegistry("BLOCK", new BlockRegistry(this, registryPieces));
        this.addRegistry("ITEM", new ItemRegistry(this, registryPieces));
        this.addRegistry("ENTITY", new EntityRegistry(this, registryPieces));
        if (this.hasConfig()) {
            this.addRegistry("CONFIG", new ConfigRegistry(this, event.getModConfigurationDirectory(), this.useModAsConfigFolder()));
            SaveLoader.setConfigFolder(this.getRegistry(ConfigRegistry.class, "CONFIG").getTacFolder());
        }
        this.addRegistry("SOUND_EVENT", new SoundEventRegistry(this));
    }

    public void registerBlocks(BlockRegistry registry) {

    }

    public void registerItems(ItemRegistry registry) {

    }

    public void beforeModuleHandlerInit(FMLPreInitializationEvent event) {

    }

    public void afterModuleHandlerInit(FMLPreInitializationEvent event) {

    }

    public void finalizeOptionalSystems() {
        if (this.getMaterialUser() != null) {
            this.getMaterialUser().finishUp();
        }

        if (this.getSubBlockSystem() != null) {
            this.getSubBlockSystem().createBlocks();
        }
    }

    public void init(FMLInitializationEvent event) {
        this.getModuleHandler().init(event);
        this.getOtherModuleHandlers().forEach(otherHandler -> otherHandler.init(event));

        this.getAllRegistries().forEach((name, registry) -> registry.init());
    }

    public void postInit(FMLPostInitializationEvent event) {
        this.getModuleHandler().postInit(event);
        this.getOtherModuleHandlers().forEach(otherHandler -> otherHandler.postInit(event));

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
    public boolean hasConfig() {
        return true;
    }

    @Override
    public String getConfigFolderName() {
        return this.getID();
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
    public ModuleHandler getModuleHandler() {
        return this.moduleHandler;
    }

    @Override
    public List<ModuleHandler> getOtherModuleHandlers() {
        return Collections.emptyList();
    }

    @Override
    public boolean addOBJDomain() {
        return false;
    }

    @Nullable
    @Override
    public MaterialUser getMaterialUser() {
        return this.materialUser;
    }

    @Nullable
    @Override
    public SubBlockSystem getSubBlockSystem() {
        return this.subBlockSystem;
    }

    @Nullable
    public File getResourceFolder() {
        File returnFolder = null;
        if (hasExternalResources()) {
            returnFolder = new File(resourceFolder, modid);
        } else if (Platform.isDevEnv()) {
            //Kinda nasty but makes the template system work.
            returnFolder = new File(resourceFolder.getParentFile().getParentFile(),
                    "/src/main/resources/assets/" + modid + "/");
        }
        return returnFolder;
    }

    public boolean useDefaultRegistryEventHandler() {
        return true;
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
            if (load && !StringUtils.isNullOrEmpty(registryPiece.modid())) {
                load = Loader.isModLoaded(registryPiece.modid());
            }
            return load;
        });
        registryPieces.forEach(registryPiece -> {
            if (registryPiece instanceof IModAware) {
                ((IModAware) registryPiece).setMod(this);
            }
        });
        return registryPieces;
    }

    public File getMinecraftFolder() {
        return minecraftFolder;
    }
}
