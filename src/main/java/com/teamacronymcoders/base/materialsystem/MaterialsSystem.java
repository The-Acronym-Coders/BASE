package com.teamacronymcoders.base.materialsystem;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.creativetabs.CreativeTabCarousel;
import com.teamacronymcoders.base.materialsystem.blocks.SubBlockPart;
import com.teamacronymcoders.base.materialsystem.items.ItemMaterialPart;
import com.teamacronymcoders.base.materialsystem.materials.Material;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.materialsystem.parts.PartType;
import com.teamacronymcoders.base.materialsystem.parts.ProvidedParts;
import com.teamacronymcoders.base.registry.ItemRegistry;
import com.teamacronymcoders.base.subblocksystem.SubBlockSystem;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaterialsSystem {
    private static final Map<String, Part> PART_MAP = new HashMap<>();
    private static final Map<String, Material> MATERIAL_MAP = new HashMap<>();
    public static final IForgeRegistry<MaterialPart> MATERIAL_PARTS = GameRegistry.findRegistry(MaterialPart.class);
    public static final ItemMaterialPart ITEM_MATERIAL_PART = new ItemMaterialPart();
    public static final MissingMaterialPart MISSING_MATERIAL_PART = new MissingMaterialPart();
    public static CreativeTabCarousel materialCreativeTab;

    public static void setup() {
        materialCreativeTab = new CreativeTabCarousel("base");
        ITEM_MATERIAL_PART.setCreativeTab(materialCreativeTab);
        Base.instance.getRegistry(ItemRegistry.class, "ITEM").register(ITEM_MATERIAL_PART);
        ProvidedParts.init();
    }

    public static void registerPart(Part part) {
        PART_MAP.put(part.getUnlocalizedName(), part);
    }

    public static void registerPartsForMaterial(Material material, String... partNames) {
        Arrays.stream(partNames).forEach(partName -> {
            Part part = PART_MAP.get(partName);
            if (part != null) {
                MaterialPart materialPart = new MaterialPart(material, part);
                MATERIAL_PARTS.register(materialPart);
                materialCreativeTab.addIconStacks(Lists.newArrayList(materialPart.getItemStack()));
                part.getPartType().setup(materialPart);
            } else {
                Base.instance.getLogger().error("Could not find part with name: " + partName);
            }
        });
    }
}
