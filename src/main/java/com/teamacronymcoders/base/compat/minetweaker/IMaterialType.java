package com.teamacronymcoders.base.compat.minetweaker;


import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("com.acronym.base.IMaterialType")
public interface IMaterialType {

    @ZenMethod
    void registerBlock(float hardness, float resistance, String toolClass, int toolTier);

    @ZenMethod
    void registerDust();

    @ZenMethod
    void registerGear();

    @ZenMethod
    void registerIngot();

    @ZenMethod
    void registerNugget();

    @ZenMethod
    void registerOre(float hardness, float resistance, String toolClass, int toolTier);

    @ZenMethod
    void registerPlate();

    Object getInternal();

}
