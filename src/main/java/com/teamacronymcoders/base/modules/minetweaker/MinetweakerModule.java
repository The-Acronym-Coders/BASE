package com.teamacronymcoders.base.modules.minetweaker;

import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import com.teamacronymcoders.base.reference.Reference;
import minetweaker.MineTweakerAPI;
import minetweaker.MineTweakerImplementationAPI;
import minetweaker.mc1102.brackets.ItemBracketHandler;
import minetweaker.runtime.providers.ScriptProviderDirectory;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

@Module(Reference.MODID)
public class MinetweakerModule extends ModuleBase {
    @Override
    public String getName() {
        return "MineTweaker3";
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        File scriptsDirectory = new File(this.getConfigRegistry().getConfigFolder(), "scripts");

        boolean fileExists = scriptsDirectory.exists();
        if (!fileExists){
            fileExists = scriptsDirectory.mkdir();
        }

        if(fileExists) {
            MineTweakerAPI.registerBracketHandler(new ItemBracketHandler());
            ItemBracketHandler.rebuildItemRegistry();
            MineTweakerAPI.registerClass(IMaterialType.class);
            MineTweakerAPI.registerClass(Materials.class);

            MineTweakerImplementationAPI.setScriptProvider(new ScriptProviderDirectory(scriptsDirectory));
            MineTweakerImplementationAPI.reload();
        } else {
            this.getLogger().fatal("SCRIPTS DIRECTORY HAS FAILED TO BE CREATED");
        }
    }
}
