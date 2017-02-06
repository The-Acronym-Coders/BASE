package com.teamacronymcoders.base.materialsystem;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.materialsystem.items.ItemMaterialPart;
import com.teamacronymcoders.base.materialsystem.materials.Material;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.materialsystem.parts.ProvidedParts;
import com.teamacronymcoders.base.reference.Reference;
import com.teamacronymcoders.base.registry.BlockRegistry;
import com.teamacronymcoders.base.registry.ItemRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import net.minecraftforge.fml.common.registry.RegistryBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaterialsSystem {
    private static final Map<String, Part> parts = new HashMap<>();
    public static final IForgeRegistry<MaterialPart> MATERIAL_PARTS = GameRegistry.findRegistry(MaterialPart.class);

    public static void setup() {
        Base.instance.getRegistry(ItemRegistry.class, "ITEM").register(new ItemMaterialPart());
        ProvidedParts.init();
    }

    public static void registerPart(Part part) {
        parts.put(part.getUnlocalizedName(), part);
    }

    public static void registerPartsForMaterial(Material material, String... partNames) {
        Arrays.stream(partNames).forEach(partName -> MATERIAL_PARTS.register(new MaterialPart(material, parts.get(partName))));
    }
}
