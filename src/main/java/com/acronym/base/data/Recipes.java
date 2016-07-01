package com.acronym.base.data;

import com.acronym.base.api.materials.Material;
import com.acronym.base.api.registries.MaterialRegistry;

import java.lang.reflect.Field;

/**
 * Created by Jared on 6/27/2016.
 */
public class Recipes {

    public static void preInit() {

    }

    public static void init() {
        for (Field f : Materials.class.getDeclaredFields()) {
            try {
                Material mat = new Material();
                mat = (Material) f.get(mat);
                MaterialRegistry.addMaterial(mat.getName().toLowerCase(), mat);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
//        for (String s : OreDictionary.getOreNames()) {
//            if (s.startsWith("ingot") && !OreDictionary.getOres("ore" + s.substring(5)).isEmpty()) {
//                String name = s.substring(5);
//                List<ItemStack> list = OreDictionary.getOres(s);
//                for (ItemStack item : list) {
//                    try {
//                        int colour = ColourHelper.getColour(ResourceUtils.getResourceFromItem(item).getInputStream());
//                        MaterialRegistry.addMaterial(name, new Material(name, colour, Arrays.asList(new Resource[]{new Resource(String.format("ingot%s", name)), new Resource(String.format("dust%s", name)), new Resource(String.format("ore%s", name))})));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
    }

    public static void postInit() {

    }
}
