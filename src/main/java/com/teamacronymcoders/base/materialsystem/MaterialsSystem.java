package com.teamacronymcoders.base.materialsystem;

import com.teamacronymcoders.base.materialsystem.materials.Material;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.materialsystem.parts.ProvidedParts;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import net.minecraftforge.fml.common.registry.RegistryBuilder;

import java.util.*;

public class MaterialsSystem {
    private static final Map<String, Part> parts = new HashMap<>();
    public static final IForgeRegistry<MaterialPart> MATERIAL_PARTS = new RegistryBuilder<MaterialPart>().create();

    public static void initParts() {
        ProvidedParts.init();
    }

    public static void registerPart(Part part) {
        parts.put(part.getUnlocalizedName(), part);
    }

    public static void registerPartsForMaterial(Material material, String... partNames) {
        Arrays.stream(partNames).forEach(partName -> MATERIAL_PARTS.register(new MaterialPart(material, parts.get(partName))));
    }
}
