package com.acronym.base.data;

import com.acronym.base.api.materials.MaterialType;
import com.acronym.base.api.materials.MaterialRegistry;
import com.acronym.base.items.BaseItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.apache.commons.lang3.tuple.MutablePair;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Recipes {

    public static void preInit() {
        for (Field f : Materials.class.getDeclaredFields()) {
            try {
                MaterialType mat = new MaterialType();
                mat = (MaterialType) f.get(mat);
                MaterialRegistry.registerMaterial(mat.getName().toLowerCase(), mat);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void init() {
        for (Map.Entry<MutablePair<String, Integer>, MaterialType> ent : MaterialRegistry.getMaterials().entrySet()) {

            MaterialType mat = ent.getValue();
            int matID = MaterialRegistry.getIDFromName(mat.getName());
            List<MaterialType.EnumPartType> types = Arrays.asList(mat.getTypes());

            if (types.contains(MaterialType.EnumPartType.INGOT)) {
                ItemStack ingot = new ItemStack(BaseItems.INGOT, 1, matID);
                if (types.contains(MaterialType.EnumPartType.GEAR)) {
                    ItemStack gear = new ItemStack(BaseItems.GEAR, 1, matID);
                    GameRegistry.addRecipe(new ShapedOreRecipe(gear, " i ", "ibi", " i ", 'i', ingot, 'b', Items.IRON_INGOT));
                }
                if (types.contains(MaterialType.EnumPartType.DUST)) {
                    ItemStack dust = new ItemStack(BaseItems.DUST, 1, matID);
                    GameRegistry.addSmelting(dust, ingot, 1);
                }
                if (types.contains(MaterialType.EnumPartType.NUGGET)) {
                    ItemStack nugget = new ItemStack(BaseItems.NUGGET, 9, matID);
                    GameRegistry.addRecipe(new ShapelessOreRecipe(nugget, ingot));
                    GameRegistry.addRecipe(new ShapelessOreRecipe(ingot, nugget, nugget, nugget, nugget, nugget, nugget, nugget, nugget, nugget));
                }
            } else {
                String ingot = "ingot" + mat.getName();
                if (!OreDictionary.getOres(ingot).isEmpty()) {
                    if (types.contains(MaterialType.EnumPartType.GEAR)) {
                        ItemStack gear = new ItemStack(BaseItems.GEAR, 1, matID);
                        GameRegistry.addRecipe(new ShapedOreRecipe(gear, " i ", "ibi", " i ", 'i', ingot, 'b', Items.IRON_INGOT));
                    }
                    if (types.contains(MaterialType.EnumPartType.DUST)) {
                        ItemStack dust = new ItemStack(BaseItems.DUST, 1, matID);
                        GameRegistry.addSmelting(dust, OreDictionary.getOres(ingot).get(0), 1);
                    }
                    if (types.contains(MaterialType.EnumPartType.NUGGET)) {
                        ItemStack nugget = new ItemStack(BaseItems.NUGGET, 9, matID);
                        GameRegistry.addRecipe(new ShapelessOreRecipe(nugget, ingot));
                        GameRegistry.addRecipe(new ShapelessOreRecipe(OreDictionary.getOres(ingot).get(0), nugget, nugget, nugget, nugget, nugget, nugget, nugget, nugget, nugget));
                    }
                } else {
                    String planks = "plank" + mat.getName();
                    if (!OreDictionary.getOres(planks).isEmpty()) {
                        if (types.contains(MaterialType.EnumPartType.GEAR)) {
                            ItemStack gear = new ItemStack(BaseItems.GEAR, 1, matID);
                            GameRegistry.addRecipe(new ShapedOreRecipe(gear, " i ", "ibi", " i ", 'i', planks, 'b', Items.IRON_INGOT));
                        }
                        if (types.contains(MaterialType.EnumPartType.DUST)) {
                            ItemStack dust = new ItemStack(BaseItems.DUST, 1, matID);
                            GameRegistry.addSmelting(dust, OreDictionary.getOres(planks).get(0), 1);
                        }
                        if (types.contains(MaterialType.EnumPartType.NUGGET)) {
                            ItemStack nugget = new ItemStack(BaseItems.NUGGET, 9, matID);
                            GameRegistry.addRecipe(new ShapelessOreRecipe(nugget, planks));
                            GameRegistry.addRecipe(new ShapelessOreRecipe(OreDictionary.getOres(planks).get(0), nugget, nugget, nugget, nugget, nugget, nugget, nugget, nugget, nugget));
                        }
                    }
                }
            }
        }
    }

    public static void postInit() {}
}
