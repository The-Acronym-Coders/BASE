package com.acronym.base.compat.minetweaker;

import minetweaker.MineTweakerAPI;

/**
 * Created by Jared.
 */
public class MinetweakerCompat {
    public MinetweakerCompat() {
        MineTweakerAPI.registerClass(Materials.class);
    }
}
