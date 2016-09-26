package com.acronym.base.config;

import com.acronym.base.api.materials.MaterialType;
import com.acronym.base.data.Materials;
import net.minecraftforge.common.config.Configuration;

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
    }

    public static void init(Configuration configuration) {
        Map<MaterialType, Boolean> materials = new HashMap<>();
        materialMap.forEach((key, value) -> {
            materials.put(key, configuration.get("Materials", key.getName(), value, String.format("Should %s be registered as a material?", key.getName())).getBoolean());
        });
        materialMap.clear();
        materialMap.putAll(materials);
    }

}
