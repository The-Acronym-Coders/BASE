package com.teamacronymcoders.base.compat;

import com.teamacronymcoders.base.compat.minetweaker.MinetweakerCompat;
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
