package com.teamacronymcoders.base.recipes;

import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

import java.util.Arrays;

public class BasicRecipes {
    public static IRecipe createCompressedRecipe(ItemStack input, ItemStack output) {
        ItemStack[] inputArray = new ItemStack[9];
        Arrays.fill(inputArray, input);
        return new ShapedRecipes(3, 3, inputArray, output);
    }

    public static IRecipe createUnCompressingRecipe(ItemStack input, ItemStack output) {
        ItemStack newOutput = new ItemStack(output.getItem(), 9, output.getItemDamage());
        return new ShapelessRecipes(newOutput, Lists.newArrayList(input));
    }
}
