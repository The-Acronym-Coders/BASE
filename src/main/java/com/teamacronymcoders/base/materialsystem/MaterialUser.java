package com.teamacronymcoders.base.materialsystem;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.materialsystem.items.ItemMaterialPart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPartSave;
import com.teamacronymcoders.base.materialsystem.materials.Material;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.registrysystem.ItemRegistry;
import com.teamacronymcoders.base.savesystem.SaveLoader;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MaterialUser {
    private final IBaseMod mod;

    private Map<String, Integer> nameMapping = Maps.newHashMap();
    private int nextId = 0;
    private BiMap<Integer, MaterialPart> materialPartBiMap = HashBiMap.create();
    private ItemMaterialPart itemMaterialPart;

    public MaterialUser(IBaseMod mod) {
        this.mod = mod;
    }

    public void setup() {
        nameMapping.putAll(SaveLoader.getSavedObject("material_parts_" + mod.getID(), MaterialPartSave.class).getMaterialMappings());
        nameMapping.values().forEach(id -> {
            if (id > nextId) {
                nextId = id + 1;
            }
        });
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

    public void setupItem() {
        if (itemMaterialPart == null) {
            itemMaterialPart = new ItemMaterialPart(this);
            itemMaterialPart.setCreativeTab(MaterialSystem.materialCreativeTab);
            mod.getRegistryHolder().getRegistry(ItemRegistry.class, "ITEM").register(itemMaterialPart);
        }
    }

    public IBaseMod getMod() {
        return mod;
    }

    public ItemMaterialPart getItemMaterialPart() {
        return itemMaterialPart;
    }

    public void registerItemMaterialPart(MaterialPart materialPart) {
        setupItem();
        itemMaterialPart.addMaterialPart(materialPartBiMap.inverse().get(materialPart), materialPart);
    }

    public MaterialPart getMaterialPart(int itemDamage) {
        return materialPartBiMap.getOrDefault(itemDamage, MaterialSystem.MISSING_MATERIAL_PART);
    }

    public int getMaterialPartId(MaterialPart materialPart) {
        return materialPartBiMap.inverse().get(materialPart);
    }

    public Map<Integer, MaterialPart> getMaterialParts() {
        return materialPartBiMap;
    }

    @SuppressWarnings("UnusedReturnValue")
    public List<MaterialPart> registerPartsForMaterial(Material material, String... partNames) throws MaterialException {
        List<MaterialPart> materialParts = Lists.newArrayList();
        for (String partName : partNames) {
            Part part = MaterialSystem.getPart(partName);
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

    public String getId() {
        return this.getMod().getID();
    }

    public void logError(String errorMessage) {
        this.getMod().getLogger().warning(errorMessage);
    }
}
