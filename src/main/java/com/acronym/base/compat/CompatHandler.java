package com.acronym.base.compat;

import com.acronym.base.compat.minetweaker.MinetweakerCompat;
import net.minecraftforge.fml.common.Loader;

/**
 * Created by Jared.
 */
public class CompatHandler {

    public CompatHandler() {
        if (Loader.isModLoaded("MineTweaker3")) {
            new MinetweakerCompat();
        }
    }
}
