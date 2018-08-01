package com.teamacronymcoders.base.recipesystem.loadcondition;

import net.minecraftforge.fml.common.Loader;

public class ModExistsLoadCondition implements ILoadCondition {
    private final String modid;

    public ModExistsLoadCondition(String modid) {
        this.modid = modid;
    }

    @Override
    public boolean shouldLoad() {
        return Loader.isModLoaded(modid);
    }
}
