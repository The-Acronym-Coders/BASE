package com.teamacronymcoders.base;

import com.teamacronymcoders.base.featuresystem.FeatureHandler;
import com.teamacronymcoders.base.subblocksystem.SubBlockSystem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.teamacronymcoders.base.Reference.*;

@Mod(modid = LATE_MODID, name = LATE_NAME, version = VERSION, dependencies = LATE_DEPENDENCIES)
public class BaseLate {
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if(FeatureHandler.isFeatureActivated("SUB_BLOCKS")) {
            SubBlockSystem.createBlocks();
        }
    }
}
