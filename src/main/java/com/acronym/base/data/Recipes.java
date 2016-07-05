package com.acronym.base.data;

import com.acronym.base.api.materials.Material;
import com.acronym.base.api.materials.MaterialRegistry;
import com.acronym.base.items.BaseItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.Arrays;
import java.util.List;

public class Recipes {

    public static void preInit() {
        for (Materials material : Materials.values()) {
            Material mat = material.getMaterial();
            MaterialRegistry.registerMaterial(material.getName().toLowerCase(), mat);
        }
    }

    public static void init() {
        for (Materials material : Materials.values()) {
            Material mat = material.getMaterial();
            int matID = MaterialRegistry.getIDFromName(mat.getName());
            List<Material.EnumPartType> types = Arrays.asList(material.getTypes());
            if (types.contains(Material.EnumPartType.INGOT)) {
                ItemStack ingot = new ItemStack(BaseItems.INGOT, 1, matID);
                if (types.contains(Material.EnumPartType.GEAR)) {
                    ItemStack gear = new ItemStack(BaseItems.GEAR, 1, matID);
                    GameRegistry.addRecipe(new ShapedOreRecipe(gear, " i ", "ibi", " i ", 'i', ingot, 'b', Items.IRON_INGOT));
                }
                if (types.contains(Material.EnumPartType.DUST)) {
                    ItemStack dust = new ItemStack(BaseItems.DUST, 1, matID);
                    GameRegistry.addSmelting(dust, ingot, 1);
                }
                if (types.contains(Material.EnumPartType.NUGGET)) {
                    ItemStack nugget = new ItemStack(BaseItems.NUGGET, 9, matID);
                    GameRegistry.addRecipe(new ShapelessOreRecipe(nugget, ingot));
                    GameRegistry.addRecipe(new ShapelessOreRecipe(ingot, nugget, nugget, nugget, nugget, nugget, nugget, nugget, nugget, nugget));
                }
            } else {
                String ingot = "ingot" + material.getOreDictSuffix();
                if (!OreDictionary.getOres(ingot).isEmpty()) {
                    if (types.contains(Material.EnumPartType.GEAR)) {
                        ItemStack gear = new ItemStack(BaseItems.GEAR, 1, matID);
                        GameRegistry.addRecipe(new ShapedOreRecipe(gear, " i ", "ibi", " i ", 'i', ingot, 'b', Items.IRON_INGOT));
                    }
                    if (types.contains(Material.EnumPartType.DUST)) {
                        ItemStack dust = new ItemStack(BaseItems.DUST, 1, matID);
                        GameRegistry.addSmelting(dust, OreDictionary.getOres(ingot).get(0), 1);
                    }
                    if (types.contains(Material.EnumPartType.NUGGET)) {
                        ItemStack nugget = new ItemStack(BaseItems.NUGGET, 9, matID);
                        GameRegistry.addRecipe(new ShapelessOreRecipe(nugget, ingot));
                        GameRegistry.addRecipe(new ShapelessOreRecipe(OreDictionary.getOres(ingot).get(0), nugget, nugget, nugget, nugget, nugget, nugget, nugget, nugget, nugget));
                    }
                } else {
                    String planks = "plank" + material.getOreDictSuffix();
                    if (!OreDictionary.getOres(planks).isEmpty()) {
                        if (types.contains(Material.EnumPartType.GEAR)) {
                            ItemStack gear = new ItemStack(BaseItems.GEAR, 1, matID);
                            GameRegistry.addRecipe(new ShapedOreRecipe(gear, " i ", "ibi", " i ", 'i', planks, 'b', Items.IRON_INGOT));
                        }
                        if (types.contains(Material.EnumPartType.DUST)) {
                            ItemStack dust = new ItemStack(BaseItems.DUST, 1, matID);
                            GameRegistry.addSmelting(dust, OreDictionary.getOres(planks).get(0), 1);
                        }
                        if (types.contains(Material.EnumPartType.NUGGET)) {
                            ItemStack nugget = new ItemStack(BaseItems.NUGGET, 9, matID);
                            GameRegistry.addRecipe(new ShapelessOreRecipe(nugget, planks));
                            GameRegistry.addRecipe(new ShapelessOreRecipe(OreDictionary.getOres(planks).get(0), nugget, nugget, nugget, nugget, nugget, nugget, nugget, nugget, nugget));
                        }
                    }
                }
            }
        }
    }

    public static void postInit() {
    }
}
