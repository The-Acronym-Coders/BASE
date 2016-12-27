package com.teamacronymcoders.base.modules.materials;

import com.teamacronymcoders.base.api.materials.MaterialRegistry;
import com.teamacronymcoders.base.api.materials.MaterialType;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.Map;

public class MaterialRecipes {
    public static void init() {
        Map<String, MaterialType> activeMaterials = ModuleMaterials.activeBaseMaterials;
        MaterialType wood = activeMaterials.get("Wood");
        if (wood != null) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.GEAR, 1, MaterialRegistry.getIDFromMaterial(wood)), " x ", "x x", " x ", 'x', "stickWood"));
        }

        MaterialType stone = activeMaterials.get("Stone");
        if (stone != null) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.GEAR, 1, MaterialRegistry.getIDFromMaterial(stone)), " x ", "xsx", " x ", 'x', "stone", 's', "gearWood"));
        }

        MaterialType iron = activeMaterials.get("Iron");
        if (iron != null) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.GEAR, 1, MaterialRegistry.getIDFromMaterial(iron)), " x ", "xsx", " x ", 'x', "ingotIron", 's', "gearStone"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModuleMaterials.NUGGET, 9, MaterialRegistry.getIDFromMaterial(iron)), "ingotIron"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.IRON_INGOT), "xxx", "xxx", "xxx", 'x', "nuggetIron"));
            GameRegistry.addSmelting(new ItemStack(ModuleMaterials.DUST, 1, MaterialRegistry.getIDFromMaterial(iron)), new ItemStack(Items.IRON_INGOT), 50);
        }

        MaterialType gold = activeMaterials.get("Gold");
        if (gold != null) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.GEAR, 1, MaterialRegistry.getIDFromMaterial(gold)), " x ", "xsx", " x ", 'x', "ingotGold", 's', "gearIron"));
            GameRegistry.addSmelting(new ItemStack(ModuleMaterials.DUST, 1, MaterialRegistry.getIDFromMaterial(gold)), new ItemStack(Items.GOLD_INGOT), 50);
        }

        MaterialType diamond = activeMaterials.get("Diamond");
        if (diamond != null) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.GEAR, 1, MaterialRegistry.getIDFromMaterial(diamond)), " x ", "xsx", " x ", 'x', "gemDiamond", 's', "gearGold"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModuleMaterials.NUGGET, 9, MaterialRegistry.getIDFromMaterial(diamond)), "gemDiamond"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.DIAMOND), "xxx", "xxx", "xxx", 'x', "nuggetDiamond"));
            GameRegistry.addSmelting(new ItemStack(ModuleMaterials.DUST, 1, MaterialRegistry.getIDFromMaterial(diamond)), new ItemStack(Items.DIAMOND), 50);
        }

        MaterialType copper = activeMaterials.get("Copper");
        if (copper != null) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.GEAR, 1, MaterialRegistry.getIDFromMaterial(copper)), " x ", "xsx", " x ", 'x', "ingotCopper", 's', "gearIron"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModuleMaterials.NUGGET, 9, MaterialRegistry.getIDFromMaterial(copper)), "ingotCopper"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.INGOT, 1, MaterialRegistry.getIDFromMaterial(copper)), "xxx", "xxx", "xxx", 'x', "nuggetCopper"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.storageBlocks.get(copper)), "xxx", "xxx", "xxx", 'x', "ingotCopper"));
            GameRegistry.addSmelting(new ItemStack(ModuleMaterials.oreBlocks.get(copper)), new ItemStack(ModuleMaterials.INGOT, 1, MaterialRegistry.getIDFromMaterial(copper)), 50);
            GameRegistry.addSmelting(new ItemStack(ModuleMaterials.DUST, 1, MaterialRegistry.getIDFromMaterial(copper)), new ItemStack(ModuleMaterials.INGOT, 1, MaterialRegistry.getIDFromMaterial(copper)), 50);
        }

        MaterialType tin = activeMaterials.get("Tin");
        if (tin != null) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.GEAR, 1, MaterialRegistry.getIDFromMaterial(tin)), " x ", "xsx", " x ", 'x', "intogTin", 's', "gearIron"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModuleMaterials.NUGGET, 9, MaterialRegistry.getIDFromMaterial(tin)), "ingotTin"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.INGOT, 1, MaterialRegistry.getIDFromMaterial(tin)), "xxx", "xxx", "xxx", 'x', "nuggetTin"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.storageBlocks.get(tin)), "xxx", "xxx", "xxx", 'x', "ingotTin"));
            GameRegistry.addSmelting(new ItemStack(ModuleMaterials.oreBlocks.get(tin)), new ItemStack(ModuleMaterials.INGOT, 1, MaterialRegistry.getIDFromMaterial(tin)), 50);
            GameRegistry.addSmelting(new ItemStack(ModuleMaterials.DUST, 1, MaterialRegistry.getIDFromMaterial(tin)), new ItemStack(ModuleMaterials.INGOT, 1, MaterialRegistry.getIDFromMaterial(tin)), 50);
        }

        MaterialType lead = activeMaterials.get("Lead");
        if (lead != null) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.GEAR, 1, MaterialRegistry.getIDFromMaterial(lead)), " x ", "xsx", " x ", 'x', "ingotLead", 's', "gearIron"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModuleMaterials.NUGGET, 9, MaterialRegistry.getIDFromMaterial(lead)), "ingotLead"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.INGOT, 1, MaterialRegistry.getIDFromMaterial(lead)), "xxx", "xxx", "xxx", 'x', "nuggetLead"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.storageBlocks.get(lead)), "xxx", "xxx", "xxx", 'x', "ingotLead"));
            GameRegistry.addSmelting(new ItemStack(ModuleMaterials.DUST, 1, MaterialRegistry.getIDFromMaterial(lead)), new ItemStack(ModuleMaterials.INGOT, 1, MaterialRegistry.getIDFromMaterial(lead)), 50);
            GameRegistry.addSmelting(new ItemStack(ModuleMaterials.oreBlocks.get(lead)), new ItemStack(ModuleMaterials.INGOT, 1, MaterialRegistry.getIDFromMaterial(lead)), 50);
        }

        MaterialType silver = activeMaterials.get("Silver");
        if (silver != null) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.GEAR, 1, MaterialRegistry.getIDFromMaterial(silver)), " x ", "xsx", " x ", 'x', "ingotSilver", 's', "gearIron"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModuleMaterials.NUGGET, 9, MaterialRegistry.getIDFromMaterial(silver)), "ingotSilver"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.INGOT, 1, MaterialRegistry.getIDFromMaterial(silver)), "xxx", "xxx", "xxx", 'x', "nuggetSilver"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.storageBlocks.get(silver)), "xxx", "xxx", "xxx", 'x', "ingotSilver"));
            GameRegistry.addSmelting(new ItemStack(ModuleMaterials.oreBlocks.get(silver)), new ItemStack(ModuleMaterials.INGOT, 1, MaterialRegistry.getIDFromMaterial(silver)), 50);
            GameRegistry.addSmelting(new ItemStack(ModuleMaterials.DUST, 1, MaterialRegistry.getIDFromMaterial(silver)), new ItemStack(ModuleMaterials.INGOT, 1, MaterialRegistry.getIDFromMaterial(silver)), 50);
        }

        MaterialType electrum = activeMaterials.get("Electrum");
        if (electrum != null) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.GEAR, 1, MaterialRegistry.getIDFromMaterial(electrum)), " x ", "xsx", " x ", 'x', "ingotElectrum", 's', "gearIron"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModuleMaterials.NUGGET, 9, MaterialRegistry.getIDFromMaterial(electrum)), "ingotElectrum"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.INGOT, 1, MaterialRegistry.getIDFromMaterial(electrum)), "xxx", "xxx", "xxx", 'x', "nuggetElectrum"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.storageBlocks.get(electrum)), "xxx", "xxx", "xxx", 'x', "ingotElectrum"));
            GameRegistry.addSmelting(new ItemStack(ModuleMaterials.oreBlocks.get(electrum)), new ItemStack(ModuleMaterials.INGOT, 1, MaterialRegistry.getIDFromMaterial(electrum)), 50);
            GameRegistry.addSmelting(new ItemStack(ModuleMaterials.DUST, 1, MaterialRegistry.getIDFromMaterial(electrum)), new ItemStack(ModuleMaterials.INGOT, 1, MaterialRegistry.getIDFromMaterial(electrum)), 50);
        }

        MaterialType nickel = activeMaterials.get("Nickel");
        if (nickel != null) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.GEAR, 1, MaterialRegistry.getIDFromMaterial(nickel)), " x ", "xsx", " x ", 'x', "ingotNickel", 's', "gearIron"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModuleMaterials.NUGGET, 9, MaterialRegistry.getIDFromMaterial(nickel)), "ingotNickel"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.INGOT, 1, MaterialRegistry.getIDFromMaterial(nickel)), "xxx", "xxx", "xxx", 'x', "nuggetNickel"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.storageBlocks.get(nickel)), "xxx", "xxx", "xxx", 'x', "ingotNickel"));
            GameRegistry.addSmelting(new ItemStack(ModuleMaterials.oreBlocks.get(nickel)), new ItemStack(ModuleMaterials.INGOT, 1, MaterialRegistry.getIDFromMaterial(nickel)), 50);
            GameRegistry.addSmelting(new ItemStack(ModuleMaterials.DUST, 1, MaterialRegistry.getIDFromMaterial(nickel)), new ItemStack(ModuleMaterials.INGOT, 1, MaterialRegistry.getIDFromMaterial(nickel)), 50);
        }

        MaterialType aluminum = activeMaterials.get("Aluminum");
        if (aluminum != null) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.GEAR, 1, MaterialRegistry.getIDFromMaterial(aluminum)), " x ", "xsx", " x ", 'x', "ingotAluminum", 's', "gearIron"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModuleMaterials.NUGGET, 9, MaterialRegistry.getIDFromMaterial(aluminum)), "ingotAluminum"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.INGOT, 1, MaterialRegistry.getIDFromMaterial(aluminum)), "xxx", "xxx", "xxx", 'x', "nuggetAluminum"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.storageBlocks.get(aluminum)), "xxx", "xxx", "xxx", 'x', "ingotAluminum"));
            GameRegistry.addSmelting(new ItemStack(ModuleMaterials.oreBlocks.get(aluminum)), new ItemStack(ModuleMaterials.INGOT, 1, MaterialRegistry.getIDFromMaterial(aluminum)), 50);
            GameRegistry.addSmelting(new ItemStack(ModuleMaterials.DUST, 1, MaterialRegistry.getIDFromMaterial(aluminum)), new ItemStack(ModuleMaterials.INGOT, 1, MaterialRegistry.getIDFromMaterial(aluminum)), 50);
        }
    }
}
