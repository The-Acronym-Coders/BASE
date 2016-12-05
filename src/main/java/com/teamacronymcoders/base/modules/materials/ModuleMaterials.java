package com.teamacronymcoders.base.modules.materials;

import com.teamacronymcoders.base.api.materials.MaterialRegistry;
import com.teamacronymcoders.base.api.materials.MaterialType;
import com.teamacronymcoders.base.items.ItemPart;
import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import com.teamacronymcoders.base.reference.Reference;
import com.teamacronymcoders.base.registry.ItemRegistry;
import com.teamacronymcoders.base.registry.config.ConfigEntry;
import com.teamacronymcoders.base.registry.config.ConfigRegistry;
import net.minecraftforge.common.config.Property;

import java.util.HashMap;
import java.util.Map;

@Module(Reference.MODID)
public class ModuleMaterials extends ModuleBase{
    public static Map<String, MaterialType> activeBaseMaterials;

    @Override
    public String getName() {
        return "Materials";
    }

    @Override
    public void configure(ConfigRegistry configRegistry) {
        int materialID = 0;
        activeBaseMaterials = new HashMap<>();
        for(Material material: Material.values()) {
            MaterialType materialType = material.getMaterialType();
            MaterialConfigEntry materialConfig = new MaterialConfigEntry(materialType.getName(), false);
            configRegistry.register(materialType.getName(), materialConfig);
            if(materialConfig.getBoolean(false)) {
                MaterialRegistry.registerMaterial(materialID++, materialType);
                activeBaseMaterials.put(materialType.getName(), materialType);
            }
        }
    }

    @Override
    public void registerItems(ConfigRegistry configRegistry, ItemRegistry itemRegistry) {
        ItemPart GEAR = new ItemPart(MaterialType.EnumPartType.GEAR);
        ItemPart DUST = new ItemPart(MaterialType.EnumPartType.DUST);
        ItemPart PLATE = new ItemPart(MaterialType.EnumPartType.PLATE);
        ItemPart NUGGET = new ItemPart(MaterialType.EnumPartType.NUGGET);
        ItemPart INGOT = new ItemPart(MaterialType.EnumPartType.INGOT);
    }

    public static class MaterialConfigEntry extends ConfigEntry {
        public MaterialConfigEntry(String materialName, Boolean value) {
            super("Material", materialName, Property.Type.BOOLEAN, Boolean.toString(value),
                    String.format("Should %s be registered as a material?", materialName));
        }
    }
}
