package com.teamacronymcoders.base.modules.minetweaker;

import com.teamacronymcoders.base.modulesystem.ModuleBase;
import minetweaker.MineTweakerAPI;
import minetweaker.MineTweakerImplementationAPI;
import minetweaker.mc1102.brackets.ItemBracketHandler;
import minetweaker.runtime.providers.ScriptProviderDirectory;

import java.io.File;

public class MineTweakerRegistration {
    private MineTweakerRegistration() {

    }

    public static void init(ModuleBase module) {
        File scriptsDirectory = new File(module.getConfigRegistry().getConfigFolder(), "scripts");

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
            module.getLogger().fatal("SCRIPTS DIRECTORY HAS FAILED TO BE CREATED");
        }
    }
}
