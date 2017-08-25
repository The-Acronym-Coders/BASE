package com.teamacronymcoders.base.materialsystem;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.creativetabs.CreativeTabCarousel;
import com.teamacronymcoders.base.materialsystem.capabilities.MaterialPartCapability;
import com.teamacronymcoders.base.materialsystem.capabilities.MaterialPartHolderProvider;
import com.teamacronymcoders.base.materialsystem.compat.MaterialCompatLoader;
import com.teamacronymcoders.base.materialsystem.materialparts.MissingMaterialPart;
import com.teamacronymcoders.base.materialsystem.materials.Material;
import com.teamacronymcoders.base.materialsystem.materials.MaterialBuilder;
import com.teamacronymcoders.base.materialsystem.parts.GatherPartsEvent;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.materialsystem.parts.PartBuilder;
import com.teamacronymcoders.base.materialsystem.parttype.PartType;
import com.teamacronymcoders.base.util.TextUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.discovery.ASMDataTable;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MaterialSystem {
    public static MissingMaterialPart MISSING_MATERIAL_PART;
    public static CreativeTabCarousel materialCreativeTab;

    private static Map<String, Part> partMap = Maps.newHashMap();
    private static Map<String, Material> materialMap = Maps.newHashMap();
    private static Map<String, PartType> partTypeMap = Maps.newHashMap();
    private static Map<String, MaterialUser> users = Maps.newHashMap();

    public static List<MaterialBuilder> materialsNotBuilt = Lists.newArrayList();
    public static List<PartBuilder> partsNotBuilt = Lists.newArrayList();

    private static boolean isSetup = false;


    public static void setup(MaterialUser user, ASMDataTable dataTable) {
        if (!isSetup) {
            MaterialCompatLoader materialCompatLoader = new MaterialCompatLoader();
            materialCompatLoader.loadCompat(dataTable);

            materialCreativeTab = new CreativeTabCarousel("materials.base");

            try {
                MISSING_MATERIAL_PART = new MissingMaterialPart();
            } catch (MaterialException e) {
                Base.instance.getLogger().fatal("Failed to Create Missing Material Part, THIS IS BAD");
            }
            materialCompatLoader.doCompat();

            GatherPartsEvent gatherPartsEvent = new GatherPartsEvent();
            MinecraftForge.EVENT_BUS.post(gatherPartsEvent);
            gatherPartsEvent.getPartList().forEach(MaterialSystem::registerPart);
            gatherPartsEvent.getPartTypeList().forEach(MaterialSystem::registerPartType);

            MaterialPartCapability.register();
            isSetup = true;
        }
        users.put(user.getId(), user);
    }

    public static void registerPart(Part part) {
        partMap.put(TextUtils.toSnakeCase(part.getName()), part);
    }

    public static void registerPartType(PartType partType) {
        partTypeMap.put(partType.getName().toLowerCase(Locale.US), partType);
    }

    public static void registerMaterial(Material material) {
        materialMap.put(material.getName(), material);
    }

    public static Part getPart(String name) {
        return partMap.get(name.toLowerCase(Locale.US));
    }

    public static PartType getPartType(String name) {
        return partTypeMap.get(name.toLowerCase(Locale.US));
    }

    public static Material getMaterial(String name) {
        return materialMap.get(name);
    }

    public static MaterialUser getUser(String name) {
        return users.get(name);
    }
}
