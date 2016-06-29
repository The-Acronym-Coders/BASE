package com.acronym.base.data;

import com.acronym.base.api.materials.Material;
import com.acronym.base.api.materials.Resource;
import com.acronym.base.api.materials.registries.MaterialRegistry;
import com.acronym.base.util.ColourHelper;
import com.acronym.base.util.ResourceUtils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jared on 6/27/2016.
 */
public class Recipes {

    public static void preInit() {

    }

    public static void init() {
        MaterialRegistry.addMaterial("Copper", Materials.copper);
        MaterialRegistry.addMaterial("Tin", Materials.tin);
        for (String s : OreDictionary.getOreNames()) {
            if (s.startsWith("ingot") && !OreDictionary.getOres("ore" + s.substring(5)).isEmpty()) {
                String name = s.substring(5);
                List<ItemStack> list = OreDictionary.getOres(s);
                for (ItemStack item : list) {
                    try {
                        int colour = ColourHelper.getColour(ResourceUtils.getResourceFromItem(item).getInputStream());
                        MaterialRegistry.addMaterial(name, new Material(name, colour, Arrays.asList(new Resource[]{new Resource(String.format("ingot%s", name)), new Resource(String.format("dust%s", name)), new Resource(String.format("ore%s", name))})));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void postInit() {

    }
}
