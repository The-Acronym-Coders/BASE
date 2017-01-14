package com.teamacronymcoders.base.modules.minetweaker;

import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import com.teamacronymcoders.base.reference.Reference;
import com.teamacronymcoders.base.util.files.BaseFileUtils;
import minetweaker.MineTweakerAPI;
import minetweaker.MineTweakerImplementationAPI;
import minetweaker.mc1102.brackets.ItemBracketHandler;
import minetweaker.runtime.providers.ScriptProviderDirectory;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

@Module(value = Reference.MODID, modsReq = "MineTweaker3")
public class MinetweakerModule extends ModuleBase {
    public static boolean tooLate = false;

    @Override
    public String getName() {
        return "MineTweaker3";
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        File scriptsDirectory = new File(this.getConfigRegistry().getConfigFolder(), "scripts");
        BaseFileUtils.createFolder(scriptsDirectory);

        MineTweakerAPI.registerBracketHandler(new ItemBracketHandler());
        ItemBracketHandler.rebuildItemRegistry();
        MineTweakerAPI.registerClass(IMaterialType.class);
        MineTweakerAPI.registerClass(Materials.class);

        MineTweakerImplementationAPI.setScriptProvider(new ScriptProviderDirectory(scriptsDirectory));
        MineTweakerImplementationAPI.reload();

        tooLate = true;
    }
}
