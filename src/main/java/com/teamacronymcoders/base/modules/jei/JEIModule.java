package com.teamacronymcoders.base.modules.jei;

import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;

import static com.teamacronymcoders.base.reference.Reference.MODID;

@Module(value = MODID, modsReq = "JEI")
public class JEIModule extends ModuleBase {
    @Override
    public String getName() {
        return "JEI";
    }
}
