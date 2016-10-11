package com.acronym.base.compat.minetweaker;

import com.acronym.base.api.materials.MaterialType;
import minetweaker.MineTweakerAPI;

/**
 * Created by Jared.
 */
public class BaseMaterialType implements IMaterialType {
    private final MaterialType type;

    public BaseMaterialType(MaterialType type) {
        this.type = type;
    }

    @Override
    public void registerGear() {
        MineTweakerAPI.apply(new Materials.Change(type));
    }

    @Override
    public void registerOre() {

    }

    @Override
    public Object getInternal() {
        return null;
    }
}
