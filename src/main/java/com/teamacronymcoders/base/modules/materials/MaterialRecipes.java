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
        }

        MaterialType gold = activeMaterials.get("Gold");
        if (gold != null) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.GEAR, 1, MaterialRegistry.getIDFromMaterial(gold)), " x ", "xsx", " x ", 'x', "ingotGold", 's', "gearIron"));
        }

        MaterialType diamond = activeMaterials.get("Diamond");
        if (diamond != null) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.GEAR, 1, MaterialRegistry.getIDFromMaterial(diamond)), " x ", "xsx", " x ", 'x', "gemDiamond", 's', "gearGold"));
        }

        MaterialType copper = activeMaterials.get("Copper");
        if (copper != null) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.GEAR, 1, MaterialRegistry.getIDFromMaterial(copper)), " x ", "xsx", " x ", 'x', "ingotCopper", 's', "gearIron"));
        }

        MaterialType tin = activeMaterials.get("Tin");
        if (tin != null) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.GEAR, 1, MaterialRegistry.getIDFromMaterial(tin)), " x ", "xsx", " x ", 'x', "intogTin", 's', "gearIron"));
        }

        MaterialType lead = activeMaterials.get("Lead");
        if (lead != null) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.GEAR, 1, MaterialRegistry.getIDFromMaterial(lead)), " x ", "xsx", " x ", 'x', "ingotLead", 's', "gearIron"));
        }

        MaterialType silver = activeMaterials.get("Silver");
        if (silver != null) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.GEAR, 1, MaterialRegistry.getIDFromMaterial(silver)), " x ", "xsx", " x ", 'x', "ingotSilver", 's', "gearIron"));
        }

        MaterialType electrum = activeMaterials.get("Electrum");
        if (electrum != null) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.GEAR, 1, MaterialRegistry.getIDFromMaterial(electrum)), " x ", "xsx", " x ", 'x', "ingotElectrum", 's', "gearIron"));
        }

        MaterialType nickel = activeMaterials.get("Nickel");
        if (nickel != null) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.GEAR, 1, MaterialRegistry.getIDFromMaterial(nickel)), " x ", "xsx", " x ", 'x', "ingotNickel", 's', "gearIron"));
        }

        MaterialType aluminum = activeMaterials.get("Aluminum");
        if (aluminum != null) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModuleMaterials.GEAR, 1, MaterialRegistry.getIDFromMaterial(aluminum)), " x ", "xsx", " x ", 'x', "ingotAluminum", 's', "gearIron"));
        }
    }
}
