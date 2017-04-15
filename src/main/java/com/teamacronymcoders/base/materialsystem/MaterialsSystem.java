package com.teamacronymcoders.base.materialsystem;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.creativetabs.CreativeTabCarousel;
import com.teamacronymcoders.base.materialsystem.items.ItemMaterialPart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPartSave;
import com.teamacronymcoders.base.materialsystem.materialparts.MissingMaterialPart;
import com.teamacronymcoders.base.materialsystem.materials.Material;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.materialsystem.parts.PartType;
import com.teamacronymcoders.base.materialsystem.parts.ProvidedParts;
import com.teamacronymcoders.base.registrysystem.ItemRegistry;
import com.teamacronymcoders.base.savesystem.SaveLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaterialsSystem {
    private static final Map<String, Part> PART_MAP = new HashMap<>();
    private static final Map<String, Material> MATERIAL_MAP = new HashMap<>();
    private static final Map<String, PartType> PART_TYPE_MAP = new HashMap<>();
    private static final BiMap<Integer, MaterialPart> MATERIAL_PARTS_IDS = HashBiMap.create();
    private static final Map<String, Integer> NAME_MAPPING = Maps.newHashMap();

    private static int nextId = 0;

    public static ItemMaterialPart ITEM_MATERIAL_PART;
    public static final MissingMaterialPart MISSING_MATERIAL_PART = new MissingMaterialPart();
    public static CreativeTabCarousel materialCreativeTab;

    public static final List<Material.Builder> MATERIALS_NOT_BUILT = Lists.newArrayList();


    public static void setup() {
        NAME_MAPPING.putAll(SaveLoader.getSavedObject("material_part_ids", MaterialPartSave.class).getMaterialMappings());
        NAME_MAPPING.values().forEach(id -> {
            if (id > nextId) {
                nextId = id + 1;
            }
        });
        materialCreativeTab = new CreativeTabCarousel("base");
        Base.instance.setCreativeTab(materialCreativeTab);
        ProvidedParts.initPartTypes();
        ProvidedParts.initParts();
    }

    public static void setupItem() {
        if (ITEM_MATERIAL_PART == null) {
            ITEM_MATERIAL_PART = new ItemMaterialPart();
            ITEM_MATERIAL_PART.setCreativeTab(materialCreativeTab);
            Base.instance.getRegistry(ItemRegistry.class, "ITEM").register(ITEM_MATERIAL_PART);
        }
    }

    public static void finishUp() {
        MaterialPartSave save = new MaterialPartSave();
        save.setMaterialMappings(MATERIAL_PARTS_IDS);
        SaveLoader.saveObject("material_parts", save);
    }

    public static void registerPart(Part part) {
        PART_MAP.put(part.getName(), part);
    }

    public static void registerPartType(PartType partType) {
        PART_TYPE_MAP.put(partType.getName(), partType);
    }

    public static void registerMaterial(Material material) {
        MATERIAL_MAP.put(material.getName(), material);
    }

    public static Part getPart(String name) {
        return PART_MAP.get(name);
    }

    public static PartType getPartType(String name) {
        return PART_TYPE_MAP.get(name);
    }

    public static Material getMaterial(String name) {
        return MATERIAL_MAP.get(name);
    }

    public static int getMaterialPartId(MaterialPart materialPart) {
        return MATERIAL_PARTS_IDS.inverse().get(materialPart);
    }

    public static MaterialPart getMaterialPart(int itemDamage) {
        return MATERIAL_PARTS_IDS.get(itemDamage);
    }

    public static Map<Integer, MaterialPart> getMaterialParts() {
        return MATERIAL_PARTS_IDS;
    }

    public static List<MaterialPart> registerPartsForMaterial(Material material, String... partNames) throws MaterialException {
        List<MaterialPart> materialParts = Lists.newArrayList();
        for (String partName : partNames) {
            Part part = PART_MAP.get(partName);
            if (part != null) {
                MaterialPart materialPart = new MaterialPart(material, part);
                int id = nextId;
                if (NAME_MAPPING.containsKey(materialPart.getName())) {
                    id = NAME_MAPPING.get(materialPart.getName());
                }
                MATERIAL_PARTS_IDS.put(id, materialPart);
                materialCreativeTab.addIconStacks(Lists.newArrayList(materialPart.getItemStack()));
                part.getPartType().setup(materialPart);
                materialParts.add(materialPart);
            } else {
                throw new MaterialException("Could not find part with name: " + partName);
            }
        }
        return materialParts;
    }
}
