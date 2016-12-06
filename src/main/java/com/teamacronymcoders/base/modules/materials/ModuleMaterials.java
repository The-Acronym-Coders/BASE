package com.teamacronymcoders.base.modules.materials;

import com.teamacronymcoders.base.api.materials.MaterialRegistry;
import com.teamacronymcoders.base.api.materials.MaterialType;
import com.teamacronymcoders.base.modules.materials.items.ItemPart;
import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import com.teamacronymcoders.base.reference.Reference;
import com.teamacronymcoders.base.registry.ItemRegistry;
import com.teamacronymcoders.base.registry.config.ConfigEntry;
import com.teamacronymcoders.base.registry.config.ConfigRegistry;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.util.HashMap;
import java.util.Map;

@Module(Reference.MODID)
public class ModuleMaterials extends ModuleBase{
    public static Map<String, MaterialType> activeBaseMaterials;

    public static final ItemPart GEAR = new ItemPart(MaterialType.EnumPartType.GEAR);
    public static final ItemPart DUST = new ItemPart(MaterialType.EnumPartType.DUST);
    public static final ItemPart PLATE = new ItemPart(MaterialType.EnumPartType.PLATE);
    public static final ItemPart NUGGET = new ItemPart(MaterialType.EnumPartType.NUGGET);
    public static final ItemPart INGOT = new ItemPart(MaterialType.EnumPartType.INGOT);

    @Override
    public String getName() {
        return "Materials";
    }

    @Override
    public void configure(ConfigRegistry configRegistry) {
        int materialID = 0;
        activeBaseMaterials = new HashMap<>();
        for(BaseMaterial material: BaseMaterial.values()) {
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
        itemRegistry.register(GEAR);
        itemRegistry.register(DUST);
        itemRegistry.register(PLATE);
        itemRegistry.register(NUGGET);
        itemRegistry.register(INGOT);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        MaterialRecipes.init();
        BaseMaterial.WOOD.getMaterialType().getTypes().add(MaterialType.EnumPartType.INGOT);
    }

    public static class MaterialConfigEntry extends ConfigEntry {
        public MaterialConfigEntry(String materialName, Boolean value) {
            super("BaseMaterial", materialName, Property.Type.BOOLEAN, Boolean.toString(value),
                    String.format("Should %s be registered as a material?", materialName));
        }
    }
}
