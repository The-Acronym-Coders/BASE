package com.teamacronymcoders.base.compat.minetweaker;

import com.teamacronymcoders.base.reference.Reference;
import minetweaker.MineTweakerAPI;
import minetweaker.MineTweakerImplementationAPI;
import minetweaker.mc1102.brackets.ItemBracketHandler;
import minetweaker.runtime.providers.ScriptProviderDirectory;

import java.io.File;

/**
 * Created by Jared.
 */
public class MinetweakerCompat {
    public MinetweakerCompat() {
        File global = new File(Reference.CONFIG_DIR, "scripts");
        if (!global.exists()) {
            global.mkdirs();
            global.mkdir();
        }

        MineTweakerAPI.registerBracketHandler(new ItemBracketHandler());
        ItemBracketHandler.rebuildItemRegistry();
        MineTweakerAPI.registerClass(IMaterialType.class);
        MineTweakerAPI.registerClass(Materials.class);

        MineTweakerImplementationAPI.setScriptProvider(new ScriptProviderDirectory(global));
        MineTweakerImplementationAPI.reload();
    }
}
