package com.acronym.base;

import com.acronym.base.proxy.CommonProxy;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Map;


@Mod(modid = Reference.modid, name = Reference.name, version = Reference.version)
public class Base {


    public static final Logger logger = LogManager.getLogger("base");
    public static long totalTime = 0;


    @Mod.Instance("base")
    public static Base instance;

    public static boolean isDevEnv = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    public static boolean generateTextures = ((ArrayList) Launch.blackboard.get("ArgumentList")).contains("generateBaseTextures");


    @SidedProxy(clientSide = "com.acronym.base.proxy.ClientProxy", serverSide = "com.acronym.base.proxy.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        logger.log(Level.INFO, "Starting PreInit");
        long time = System.currentTimeMillis();
        for (Map.Entry<String, Object> ent : Launch.blackboard.entrySet()) {
            System.out.println(ent.getKey() + ":" + ent.getValue());
        }

        time = (System.currentTimeMillis() - time);
        totalTime += time;
        logger.log(Level.INFO, "Completed PreInit in: " + time + "ms");
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        logger.log(Level.INFO, "Starting Init");
        long time = System.currentTimeMillis();

        time = (System.currentTimeMillis() - time);
        totalTime += time;
        logger.log(Level.INFO, "Completed Init in: " + time + "ms");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        logger.log(Level.INFO, "Starting PostInit");
        long time = System.currentTimeMillis();


        time = (System.currentTimeMillis() - time);
        totalTime += time;
        logger.log(Level.INFO, "Completed PostInit in: " + time + "ms");

    }


    @EventHandler
    public void loadComplete(FMLLoadCompleteEvent e) {
        logger.log(Level.INFO, "Starting LoadComplete");
        long time = System.currentTimeMillis();


        time = (System.currentTimeMillis() - time);
        totalTime += time;
        logger.log(Level.INFO, "Completed LoadComplete in: " + time + "ms");
        logger.log(Level.INFO, "Loaded in: " + totalTime + "ms");
    }

}
