package com.teamacronymcoders.base.config;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.api.materials.MaterialType;
import com.teamacronymcoders.base.data.Materials;
import com.teamacronymcoders.base.registry.config.ConfigEntry;
import com.teamacronymcoders.base.registry.config.ConfigRegistry;
import net.minecraftforge.common.config.Property;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jared.
 */
public class ConfigMaterials {

    public static Map<MaterialType, Boolean> materialMap = new HashMap<>();

    static {
        materialMap.put(Materials.WOOD, false);
        materialMap.put(Materials.STONE, false);
        materialMap.put(Materials.IRON, false);
        materialMap.put(Materials.GOLD, false);
        materialMap.put(Materials.DIAMOND, false);

        materialMap.put(Materials.COPPER, false);
        materialMap.put(Materials.TIN, false);
        materialMap.put(Materials.LEAD, false);
        materialMap.put(Materials.SILVER, false);
        materialMap.put(Materials.ELECTRUM, false);
        materialMap.put(Materials.NICKEL, false);
        materialMap.put(Materials.ALUMINUM, false);
    }

    public static void init(Base mod) {
        Map<MaterialType, Boolean> materials = new HashMap<>();
        materialMap.forEach((key, value) -> {
            MaterialConfigEntry configEntry = new MaterialConfigEntry(key.getName(), value);
            mod.getRegistry(ConfigRegistry.class, "CONFIG").addEntry(configEntry);
            materials.put(key, configEntry.getBoolean(value));
        });
        materialMap.clear();
        materialMap.putAll(materials);
    }

    public static class MaterialConfigEntry extends ConfigEntry {
        public MaterialConfigEntry(String materialName, Boolean value) {
            super("Materials", materialName, Property.Type.BOOLEAN, Boolean.toString(value),
                    String.format("Should %s be registered as a material?", materialName));
        }
    }
}
