package com.teamacronymcoders.base;

import com.teamacronymcoders.base.api.materials.MaterialRegistry;
import com.teamacronymcoders.base.api.materials.MaterialType;
import com.teamacronymcoders.base.blocks.BaseBlocks;
import com.teamacronymcoders.base.compat.CompatHandler;
import com.teamacronymcoders.base.config.Config;
import com.teamacronymcoders.base.data.Materials;
import com.teamacronymcoders.base.data.Recipes;
import com.teamacronymcoders.base.items.BaseItems;
import com.teamacronymcoders.base.proxy.CommonProxy;
import com.teamacronymcoders.base.reference.Reference;
import com.teamacronymcoders.base.reference.TabBase;
import com.teamacronymcoders.base.util.LanguageHelper;
import com.teamacronymcoders.base.util.LogHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

import java.io.File;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.BUILD_VERSION, acceptedMinecraftVersions = "[" + Reference.MINECRAFT_VERSION + "]", dependencies = "after:MineTweaker3;")
public class Base {

    public static final LogHelper logger = new LogHelper(Reference.NAME);
    public static final LanguageHelper languageHelper = new LanguageHelper(Reference.MODID);
    private static long totalTime = 0;


    @Instance(Reference.MODID)
    public static Base INSTANCE;

    @SidedProxy(clientSide = "com.teamacronymcoders.proxy.ClientProxy", serverSide = "com.teamacronymcoders.proxy.CommonProxy")
    public static CommonProxy PROXY;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        logger.info("Starting PreInit");
        long time = System.currentTimeMillis();

        Reference.CONFIG_DIR = new File(e.getModConfigurationDirectory(), "ACRONYM");
        Reference.CONFIG_DIR = new File(Reference.CONFIG_DIR, "BASE");

        if (!Reference.CONFIG_DIR.exists())
            Reference.CONFIG_DIR.mkdir();
        Config.initConfig(new File(Reference.CONFIG_DIR, "General.cfg"));


        Recipes.preInit();
        new CompatHandler();
        BaseItems.preInit();
        BaseBlocks.preInit();
        PROXY.preInitBlocks();
        PROXY.initEvents();
        Recipes.preInitLate();

        time = (System.currentTimeMillis() - time);
        totalTime += time;
        logger.info(String.format("Completed PreInit in: %d ms", time));
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        logger.info("Starting Init");
        long time = System.currentTimeMillis();
        PROXY.initBlockRenders();
        PROXY.initItemRenders();
        PROXY.registerRenderers();
        Recipes.init();
        if (!MaterialRegistry.getMaterials().isEmpty()) {
            Reference.tab = new TabBase();
        }
        Materials.WOOD.getTypes().add(MaterialType.EnumPartType.INGOT);
        time = (System.currentTimeMillis() - time);
        totalTime += time;
        logger.info(String.format("Completed Init in: %d ms", time));
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        logger.info("Starting PostInit");
        long time = System.currentTimeMillis();
        Recipes.postInit();
        time = (System.currentTimeMillis() - time);
        totalTime += time;
        logger.info(String.format("Completed PostInit in: %d ms", time));
    }

    @EventHandler
    public void loadComplete(FMLLoadCompleteEvent e) {
        logger.info("Starting LoadComplete");
        long time = System.currentTimeMillis();
        time = (System.currentTimeMillis() - time);
        totalTime += time;
        logger.info(String.format("Completed LoadComplete in: %d ms", time));
        logger.info(String.format("Loaded In: %d ms", totalTime));
    }

}
