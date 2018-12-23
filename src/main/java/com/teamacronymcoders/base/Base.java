package com.teamacronymcoders.base;

import com.teamacronymcoders.base.capability.tool.ToolConfiguration;
import com.teamacronymcoders.base.command.CommandSubBase;
import com.teamacronymcoders.base.entities.dataserializers.BaseDataSerializers;
import com.teamacronymcoders.base.items.ItemEventHandler;
import com.teamacronymcoders.base.proxies.ModCommonProxy;
import com.teamacronymcoders.base.recipesystem.RecipeSystem;
import com.teamacronymcoders.base.util.LanguageHelper;
import com.teamacronymcoders.base.util.OreDictUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.server.command.CommandTreeBase;

import static com.teamacronymcoders.base.Reference.*;

@Mod(modid = MODID, name = NAME, version = VERSION, dependencies = DEPENDENCIES)
public class Base extends BaseModFoundation<Base> {
    public static final LanguageHelper languageHelper = new LanguageHelper(MODID);

    static {
        FluidRegistry.enableUniversalBucket();
    }

    @Instance(MODID)
    public static Base instance;

    @SidedProxy(clientSide = "com.teamacronymcoders.base.proxies.ModClientProxy", serverSide = "com.teamacronymcoders.base.proxies.ModCommonProxy")
    public static ModCommonProxy proxy;

    private CommandTreeBase baseCommand = new CommandSubBase(MODID);

    public Base() {
        super(MODID, NAME, VERSION, CreativeTabs.MISC);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        OreDictUtils.setup();
        BaseDataSerializers.registerSerializers();
        Capabilities.register();
        ToolConfiguration.configureTool();
        RecipeSystem.setup();
        MinecraftForge.EVENT_BUS.register(new ItemEventHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @EventHandler
    public void serverInit(FMLServerStartingEvent event) {
        event.registerServerCommand(this.getBaseCommand());
    }
    @Override
    public Base getInstance() {
        return instance;
    }

    public CommandTreeBase getBaseCommand() {
        return baseCommand;
    }
}
