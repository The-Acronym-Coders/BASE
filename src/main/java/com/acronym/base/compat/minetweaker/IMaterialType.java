package com.acronym.base.compat.minetweaker;


import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("com.acronym.base.IMaterialType")
public interface IMaterialType {

    @ZenMethod
    void registerGear();

    @ZenMethod
    void registerOre();

    Object getInternal();

}
