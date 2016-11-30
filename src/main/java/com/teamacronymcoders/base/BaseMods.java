package com.teamacronymcoders.base;

import java.util.HashMap;
import java.util.Map;

public class BaseMods {
    private static Map<String, IBaseMod> mods = new HashMap<>();

    public static void addBaseMod(IBaseMod mod) {
        mods.put(mod.getID(), mod);
    }

    public static IBaseMod getBaseMod(String id) {
        return mods.get(id);
    }

    public static Map<String, IBaseMod> getBaseMods() {
        return mods;
    }
}
