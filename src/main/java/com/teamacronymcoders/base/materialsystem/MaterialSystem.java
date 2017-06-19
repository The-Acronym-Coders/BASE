package com.teamacronymcoders.base.materialsystem;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.creativetabs.CreativeTabCarousel;
import com.teamacronymcoders.base.materialsystem.compat.MaterialCompatLoader;
import com.teamacronymcoders.base.materialsystem.items.ItemMaterialPart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPartSave;
import com.teamacronymcoders.base.materialsystem.materialparts.MissingMaterialPart;
import com.teamacronymcoders.base.materialsystem.materials.Material;
import com.teamacronymcoders.base.materialsystem.materials.MaterialBuilder;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.materialsystem.parts.PartBuilder;
import com.teamacronymcoders.base.materialsystem.parttype.PartType;
import com.teamacronymcoders.base.materialsystem.parts.ProvidedParts;
import com.teamacronymcoders.base.registrysystem.ItemRegistry;
import com.teamacronymcoders.base.savesystem.SaveLoader;
import com.teamacronymcoders.base.util.TextUtils;
import net.minecraftforge.fml.common.discovery.ASMDataTable;

import java.util.*;

public class MaterialSystem {
    public static MissingMaterialPart MISSING_MATERIAL_PART;

    private IBaseMod mod;

    private Map<String, Part> partMap = new HashMap<>();
    private Map<String, Material> materialMap = new HashMap<>();
    private Map<String, PartType> partTypeMap = new HashMap<>();
    private BiMap<Integer, MaterialPart> materialPartBiMap = HashBiMap.create();
    private Map<String, Integer> nameMapping = Maps.newHashMap();

    private int nextId = 0;

    public ItemMaterialPart itemMaterialPart;
    public CreativeTabCarousel materialCreativeTab;

    public List<MaterialBuilder> materialsNotBuilt = Lists.newArrayList();
    public List<PartBuilder> partsNotBuilt = Lists.newArrayList();


    public MaterialSystem(IBaseMod mod) {
        this.mod = mod;
    }

    public void setup(ASMDataTable dataTable) {
        MaterialCompatLoader materialCompatLoader = new MaterialCompatLoader();
        materialCompatLoader.loadCompat(dataTable);

        nameMapping.putAll(SaveLoader.getSavedObject("material_parts_" + mod.getID(), MaterialPartSave.class).getMaterialMappings());
        nameMapping.values().forEach(id -> {
            if (id > nextId) {
                nextId = id + 1;
            }
        });
        materialCreativeTab = new CreativeTabCarousel("materials." + mod.getID());
        setupItem();
        try {
            MISSING_MATERIAL_PART = new MissingMaterialPart(this.getMod(), this);
        } catch (MaterialException e) {
            mod.getLogger().fatal("Failed to Create Missing Material Part, THIS IS BAD");
        }
        if (mod.getSubBlockSystem() != null) {
            ProvidedParts providedParts = new ProvidedParts(this.mod, this);
            providedParts.initPartsAndTypes();
            materialCompatLoader.doCompat(this);
        } else {
            mod.getLogger().fatal("Failed to find subBlockSystem");
        }

    }

    public void setupItem(MaterialPart materialPart) {
        setupItem();
        itemMaterialPart.addMaterialPart(materialPartBiMap.inverse().get(materialPart), materialPart);
    }

    public void setupItem() {
        if (itemMaterialPart == null) {
            itemMaterialPart = new ItemMaterialPart(this);
            itemMaterialPart.setCreativeTab(materialCreativeTab);
            mod.getRegistryHolder().getRegistry(ItemRegistry.class, "ITEM").register(itemMaterialPart);
        }
    }

    public void finishUp() {
        List<MaterialPart> parts = Lists.newArrayList(materialPartBiMap.values());
        for (MaterialPart materialPart : parts) {
            materialPart.setup();
        }
        MaterialPartSave save = new MaterialPartSave();
        save.setMaterialMappings(materialPartBiMap);
        SaveLoader.saveObject("material_parts_" + mod.getID(), save);
    }

    public void registerPart(Part part) {
        partMap.put(TextUtils.toSnakeCase(part.getName()), part);
    }

    public void registerPartType(PartType partType) {
        partTypeMap.put(partType.getName(), partType);
    }

    public void registerMaterial(Material material) {
        materialMap.put(material.getName(), material);
    }

    public Part getPart(String name) {
        return partMap.get(name);
    }

    public PartType getPartType(String name) {
        return partTypeMap.get(name);
    }

    public Material getMaterial(String name) {
        return materialMap.get(name);
    }

    public int getMaterialPartId(MaterialPart materialPart) {
        return materialPartBiMap.inverse().get(materialPart);
    }

    public MaterialPart getMaterialPart(int itemDamage) {
        return materialPartBiMap.get(itemDamage);
    }

    public Map<Integer, MaterialPart> getMaterialParts() {
        return materialPartBiMap;
    }

    @SuppressWarnings("UnusedReturnValue")
    public List<MaterialPart> registerPartsForMaterial(Material material, String... partNames) throws MaterialException {
        List<MaterialPart> materialParts = Lists.newArrayList();
        for (String partName : partNames) {
            Part part = partMap.get(partName.toLowerCase(Locale.US));
            if (part != null) {
                MaterialPart materialPart = new MaterialPart(this, material, part);
                this.registerMaterialPart(materialPart);
                materialParts.add(materialPart);
            } else {
                throw new MaterialException("Could not find part with name: " + partName);
            }
        }
        return materialParts;
    }

    public void registerMaterialPart(MaterialPart materialPart) {
        int id = nextId++;
        if (nameMapping.containsKey(materialPart.getUnlocalizedName())) {
            id = nameMapping.get(materialPart.getUnlocalizedName());
        }
        materialPartBiMap.put(id, materialPart);
    }

    public IBaseMod getMod() {
        return mod;
    }
}
