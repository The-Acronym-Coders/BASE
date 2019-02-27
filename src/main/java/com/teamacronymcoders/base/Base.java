package com.teamacronymcoders.base;

import com.teamacronymcoders.base.capability.tool.ToolConfiguration;
import com.teamacronymcoders.base.command.CommandSubBase;
import com.teamacronymcoders.base.entities.dataserializers.BaseDataSerializers;
import com.teamacronymcoders.base.items.ItemEventHandler;
import com.teamacronymcoders.base.proxies.ModCommonProxy;
import com.teamacronymcoders.base.recipesystem.RecipeSystem;
import com.teamacronymcoders.base.util.OreDictUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.server.command.CommandTreeBase;

import static com.teamacronymcoders.base.Base.ID;

@Mod(ID)
public class Base extends BaseModFoundation<Base> {
    public static final String ID = "base";

    static {
        FluidRegistry.enableUniversalBucket();
    }

    public static Base instance;

    @SidedProxy(clientSide = "com.teamacronymcoders.base.proxies.ModClientProxy", serverSide = "com.teamacronymcoders.base.proxies.ModCommonProxy")
    public static ModCommonProxy proxy;

    private CommandTreeBase baseCommand = new CommandSubBase(MODID);

    public Base() {
        super(ID, ItemGroup.MISC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
    }

    public void preInit(FMLCommonSetupEvent event) {
        super.preInit(event);
        OreDictUtils.setup();
        BaseDataSerializers.registerSerializers();
        Capabilities.register();
        ToolConfiguration.configureTool();
        RecipeSystem.setup();
        MinecraftForge.EVENT_BUS.register(new ItemEventHandler());
    }

    @Override
    public Base getInstance() {
        return instance;
    }

    public CommandTreeBase getBaseCommand() {
        return baseCommand;
    }
}
