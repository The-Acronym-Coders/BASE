package com.acronym.base;

import com.acronym.base.api.materials.MaterialRegistry;
import com.acronym.base.blocks.BaseBlocks;
import com.acronym.base.compat.CompatHandler;
import com.acronym.base.config.Config;
import com.acronym.base.data.Recipes;
import com.acronym.base.items.BaseItems;
import com.acronym.base.proxy.CommonProxy;
import com.acronym.base.reference.Reference;
import com.acronym.base.reference.TabBase;
import com.acronym.base.util.LanguageHelper;
import com.acronym.base.util.LogHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

import static com.acronym.base.reference.Reference.CONFIG_DIR;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.BUILD_VERSION, acceptedMinecraftVersions = "[" + Reference.MINECRAFT_VERSION + "]", dependencies = "after:MineTweaker3;")
public class Base {

    public static final LogHelper logger = new LogHelper(Reference.NAME);
    public static final LanguageHelper languageHelper = new LanguageHelper(Reference.MODID);
    private static long totalTime = 0;


    @Instance(Reference.MODID)
    public static Base INSTANCE;

    @SidedProxy(clientSide = "com.acronym.base.proxy.ClientProxy", serverSide = "com.acronym.base.proxy.CommonProxy")
    public static CommonProxy PROXY;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) throws Exception {
        logger.info("Starting PreInit");
        long time = System.currentTimeMillis();

        CONFIG_DIR = new File(e.getModConfigurationDirectory(), "ACRONYM");
        CONFIG_DIR = new File(CONFIG_DIR, "BASE");

        if (!CONFIG_DIR.exists())
            CONFIG_DIR.mkdir();
        Config.initConfig(new File(CONFIG_DIR, "General.cfg"));

        new CompatHandler();
        Recipes.preInit();
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
