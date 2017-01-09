package com.teamacronymcoders.base.modules.materials;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.api.materials.MaterialRegistry;
import com.teamacronymcoders.base.api.materials.MaterialType;
import com.teamacronymcoders.base.api.materials.MaterialType.EnumPartType;
import com.teamacronymcoders.base.creativetabs.CreativeTabCarousel;
import com.teamacronymcoders.base.modules.materials.blocks.BlockMaterial;
import com.teamacronymcoders.base.modules.materials.blocks.BlockProperties;
import com.teamacronymcoders.base.modules.materials.items.ItemPart;
import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import com.teamacronymcoders.base.reference.Reference;
import com.teamacronymcoders.base.registry.BlockRegistry;
import com.teamacronymcoders.base.registry.ItemRegistry;
import com.teamacronymcoders.base.registry.config.ConfigEntry;
import com.teamacronymcoders.base.registry.config.ConfigRegistry;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Module(Reference.MODID)
public class ModuleMaterials extends ModuleBase {
    public static final ItemPart GEAR = new ItemPart(EnumPartType.GEAR);
    public static final ItemPart DUST = new ItemPart(EnumPartType.DUST);
    public static final ItemPart PLATE = new ItemPart(EnumPartType.PLATE);
    public static final ItemPart NUGGET = new ItemPart(EnumPartType.NUGGET);
    public static final ItemPart INGOT = new ItemPart(EnumPartType.INGOT);

    public static Map<String, MaterialType> activeBaseMaterials;
    public static Map<MaterialType, BlockMaterial> oreBlocks;
    public static Map<MaterialType, BlockMaterial> storageBlocks;
    public static boolean tooLate = false;

    @Override
    public String getName() {
        return "Materials";
    }

    @Override
    public void configure(ConfigRegistry configRegistry) {
        activeBaseMaterials = new HashMap<>();
        for (BaseMaterial material : BaseMaterial.values()) {
            MaterialType materialType = material.getMaterialType();
            MaterialConfigEntry materialConfig = new MaterialConfigEntry(materialType.getName(), false);
            configRegistry.register(materialType.getName(), materialConfig);
            if (materialConfig.getBoolean(false)) {
                MaterialRegistry.registerMaterial(material.ordinal(), materialType);
                activeBaseMaterials.put(materialType.getName(), materialType);
            }
        }
        oreBlocks = new HashMap<>();
        storageBlocks = new HashMap<>();
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
    public void registerBlocks(ConfigRegistry configRegistry, BlockRegistry blockRegistry) {
        BlockProperties oreProperties = new BlockProperties(3, 5, "pickaxe", 2);
        BlockProperties storageProperties = new BlockProperties(5, 10, "pickaxe", 2);
        MaterialRegistry.getMaterials().forEach((id, materialType) -> {
            if(materialType.isTypeSet(EnumPartType.ORE)) {
                BlockMaterial blockMaterial = new BlockMaterial(materialType, EnumPartType.ORE, oreProperties);
                blockRegistry.register(blockMaterial);
                oreBlocks.put(materialType, blockMaterial);
            }
            if(materialType.isTypeSet(EnumPartType.BLOCK)) {
                BlockMaterial blockMaterial = new BlockMaterial(materialType, EnumPartType.BLOCK, storageProperties);
                blockRegistry.register(blockMaterial);
                storageBlocks.put(materialType, blockMaterial);
            }
        });
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        if(MaterialRegistry.getMaterials().size() > 0) {
            CreativeTabCarousel tabCarousel = new CreativeTabCarousel("base");
            tabCarousel.setIconStacks(GEAR.getAllSubItems(new ArrayList<>()));
            Base.instance.setCreativeTab(tabCarousel);
        }
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        MaterialRecipes.init();
    }

    public static class MaterialConfigEntry extends ConfigEntry {
        public MaterialConfigEntry(String materialName, Boolean value) {
            super("materials", materialName, Property.Type.BOOLEAN, Boolean.toString(value),
                    String.format("Should %s be registered as a material?", materialName));
        }
    }
}
