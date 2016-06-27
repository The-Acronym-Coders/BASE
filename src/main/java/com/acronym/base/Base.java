package com.acronym.base;

import com.acronym.base.data.Recipes;
import com.acronym.base.items.BaseItems;
import com.acronym.base.proxy.CommonProxy;
import com.acronym.base.reference.Reference;
import com.acronym.base.util.LanguageHelper;
import com.acronym.base.util.LogHelper;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Base {


    public static final LogHelper logger = new LogHelper(Reference.MODID);
    public static final LanguageHelper languageHelper = new LanguageHelper(Reference.MODID);
    public static long totalTime = 0;

    int i = 0235;


    

    @Mod.Instance(Reference.MODID)
    public static Base instance;

    @Deprecated
    /*
     * Use com.acronym.base.util.Platform.isDevEnv()
     */
    public static boolean isDevEnv = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    public static boolean generateTextures = ((ArrayList) Launch.blackboard.get("ArgumentList")).contains("generateBaseTextures");

    @SidedProxy(clientSide = "com.acronym.base.proxy.ClientProxy", serverSide = "com.acronym.base.proxy.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        logger.info("Starting PreInit");
        long time = System.currentTimeMillis();
        time = (System.currentTimeMillis() - time);
        BaseItems.preInit();
        Recipes.preInit();
        totalTime += time;
        logger.info("Completed PreInit in: " + time + "ms");
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        logger.info("Starting Init");
        long time = System.currentTimeMillis();
        BaseItems.init();
        Recipes.init();
        time = (System.currentTimeMillis() - time);
        totalTime += time;
        logger.info("Completed Init in: " + time + "ms");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        logger.info("Starting PostInit");
        long time = System.currentTimeMillis();
        Recipes.postInit();
        time = (System.currentTimeMillis() - time);
        totalTime += time;
        logger.info("Completed PostInit in: " + time + "ms");
    }


    @EventHandler
    public void loadComplete(FMLLoadCompleteEvent e) {
        logger.info("Starting LoadComplete");
        long time = System.currentTimeMillis();
        time = (System.currentTimeMillis() - time);
        totalTime += time;
        logger.info("Completed LoadComplete in: " + time + "ms");
        logger.info("Loaded in: " + totalTime + "ms");
    }

}
