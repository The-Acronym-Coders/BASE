package com.teamacronymcoders.base.data;

import com.teamacronymcoders.base.api.materials.MaterialRegistry;
import com.teamacronymcoders.base.api.materials.MaterialType;
import com.teamacronymcoders.base.blocks.BaseBlocks;
import com.teamacronymcoders.base.config.ConfigMaterials;
import com.teamacronymcoders.base.items.BaseItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.Map;

import static com.teamacronymcoders.base.data.Materials.*;

public class Recipes {

    public static void preInit() {
        MaterialRegistry.registerNativeMaterial(0, WOOD);
        MaterialRegistry.registerNativeMaterial(1, STONE);
        MaterialRegistry.registerNativeMaterial(2, IRON);
        MaterialRegistry.registerNativeMaterial(3, GOLD);
        MaterialRegistry.registerNativeMaterial(4, DIAMOND);

        MaterialRegistry.registerNativeMaterial(5, COPPER);
        MaterialRegistry.registerNativeMaterial(6, TIN);
        MaterialRegistry.registerNativeMaterial(7, LEAD);
        MaterialRegistry.registerNativeMaterial(8, SILVER);
        MaterialRegistry.registerNativeMaterial(9, ELECTRUM);
        MaterialRegistry.registerNativeMaterial(10, NICKEL);
        MaterialRegistry.registerNativeMaterial(11, ALUMINUM);

    }

    public static void preInitLate() {
        for (Map.Entry<Integer, MaterialType> ent : MaterialRegistry.getMaterials().entrySet()) {
            MaterialType mat = ent.getValue();
            for (MaterialType.EnumPartType type : mat.getTypes()) {
                switch (type) {
                    case INGOT:
                        OreDictionary.registerOre("ingot" + mat.getName(), new ItemStack(BaseItems.INGOT, 1, ent.getKey()));
                        break;
                    case DUST:
                        OreDictionary.registerOre("dust" + mat.getName(), new ItemStack(BaseItems.DUST, 1, ent.getKey()));
                        break;
                    case GEAR:
                        OreDictionary.registerOre("gear" + mat.getName(), new ItemStack(BaseItems.GEAR, 1, ent.getKey()));
                        break;
                    case NUGGET:
                        OreDictionary.registerOre("nugget" + mat.getName(), new ItemStack(BaseItems.NUGGET, 1, ent.getKey()));
                        break;
                    case PLATE:
                        OreDictionary.registerOre("plate" + mat.getName(), new ItemStack(BaseItems.PLATE, 1, ent.getKey()));
                        break;
                    case BLOCK:
                        OreDictionary.registerOre("block" + mat.getName(), new ItemStack(BaseBlocks.storageBlockMap.get(ent.getValue())));
                        break;
                    case ORE:
                        OreDictionary.registerOre("ore" + mat.getName(), new ItemStack(BaseBlocks.oreBlockMap.get(ent.getValue())));
                        break;
                }
            }
        }
    }

    public static void init() {

        if (ConfigMaterials.materialMap.get(WOOD)) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.GEAR, 1, MaterialRegistry.getIDFromMaterial(WOOD)), " x ", "x x", " x ", 'x', "stickWood"));
        }
        if (ConfigMaterials.materialMap.get(STONE)) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.GEAR, 1, MaterialRegistry.getIDFromMaterial(STONE)), " x ", "xsx", " x ", 'x', "stone", 's', "gearWood"));
        }
        if (ConfigMaterials.materialMap.get(IRON)) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.GEAR, 1, MaterialRegistry.getIDFromMaterial(IRON)), " x ", "xsx", " x ", 'x', "ingotIron", 's', "gearStone"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BaseItems.NUGGET, 9, MaterialRegistry.getIDFromMaterial(IRON)), "ingotIron"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.IRON_INGOT), "xxx", "xxx", "xxx", 'x', "nuggetIron"));
            GameRegistry.addSmelting(new ItemStack(BaseItems.DUST, 1, MaterialRegistry.getIDFromMaterial(IRON)), new ItemStack(Items.IRON_INGOT), 50);
        }
        if (ConfigMaterials.materialMap.get(GOLD)) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.GEAR, 1, MaterialRegistry.getIDFromMaterial(Materials.GOLD)), " x ", "xsx", " x ", 'x', "ingotGold", 's', "gearIron"));
            GameRegistry.addSmelting(new ItemStack(BaseItems.DUST, 1, MaterialRegistry.getIDFromMaterial(Materials.GOLD)), new ItemStack(Items.GOLD_INGOT), 50);
        }
        if (ConfigMaterials.materialMap.get(DIAMOND)) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.GEAR, 1, MaterialRegistry.getIDFromMaterial(Materials.DIAMOND)), " x ", "xsx", " x ", 'x', "gemDiamond", 's', "gearGold"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BaseItems.NUGGET, 9, MaterialRegistry.getIDFromMaterial(Materials.DIAMOND)), "gemDiamond"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.DIAMOND), "xxx", "xxx", "xxx", 'x', "nuggetDiamond"));
            GameRegistry.addSmelting(new ItemStack(BaseItems.DUST, 1, MaterialRegistry.getIDFromMaterial(Materials.DIAMOND)), new ItemStack(Items.DIAMOND), 50);
        }
        if (ConfigMaterials.materialMap.get(COPPER)) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.GEAR, 1, MaterialRegistry.getIDFromMaterial(COPPER)), " x ", "xsx", " x ", 'x', "ingotCopper", 's', "gearIron"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BaseItems.NUGGET, 9, MaterialRegistry.getIDFromMaterial(COPPER)), "ingotCopper"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(COPPER)), "xxx", "xxx", "xxx", 'x', "nuggetCopper"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseBlocks.storageBlockMap.get(COPPER)), "xxx", "xxx", "xxx", 'x', "ingotCopper"));
            GameRegistry.addSmelting(new ItemStack(BaseBlocks.oreBlockMap.get(COPPER)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(COPPER)), 50);
            GameRegistry.addSmelting(new ItemStack(BaseItems.DUST, 1, MaterialRegistry.getIDFromMaterial(COPPER)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(COPPER)), 50);
        }
        if (ConfigMaterials.materialMap.get(TIN)) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.GEAR, 1, MaterialRegistry.getIDFromMaterial(Materials.TIN)), " x ", "xsx", " x ", 'x', "intogTin", 's', "gearIron"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BaseItems.NUGGET, 9, MaterialRegistry.getIDFromMaterial(Materials.TIN)), "ingotTin"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.TIN)), "xxx", "xxx", "xxx", 'x', "nuggetTin"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseBlocks.storageBlockMap.get(Materials.TIN)), "xxx", "xxx", "xxx", 'x', "ingotTin"));
            GameRegistry.addSmelting(new ItemStack(BaseBlocks.oreBlockMap.get(Materials.TIN)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.TIN)), 50);
            GameRegistry.addSmelting(new ItemStack(BaseItems.DUST, 1, MaterialRegistry.getIDFromMaterial(Materials.TIN)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.TIN)), 50);
        }
        if (ConfigMaterials.materialMap.get(LEAD)) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.GEAR, 1, MaterialRegistry.getIDFromMaterial(Materials.LEAD)), " x ", "xsx", " x ", 'x', "ingotLead", 's', "gearIron"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BaseItems.NUGGET, 9, MaterialRegistry.getIDFromMaterial(Materials.LEAD)), "ingotLead"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.LEAD)), "xxx", "xxx", "xxx", 'x', "nuggetLead"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseBlocks.storageBlockMap.get(Materials.LEAD)), "xxx", "xxx", "xxx", 'x', "ingotLead"));
            GameRegistry.addSmelting(new ItemStack(BaseItems.DUST, 1, MaterialRegistry.getIDFromMaterial(Materials.LEAD)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.LEAD)), 50);
            GameRegistry.addSmelting(new ItemStack(BaseBlocks.oreBlockMap.get(Materials.LEAD)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.LEAD)), 50);
        }
        if (ConfigMaterials.materialMap.get(SILVER)) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.GEAR, 1, MaterialRegistry.getIDFromMaterial(Materials.SILVER)), " x ", "xsx", " x ", 'x', "ingotSilver", 's', "gearIron"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BaseItems.NUGGET, 9, MaterialRegistry.getIDFromMaterial(Materials.SILVER)), "ingotSilver"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.SILVER)), "xxx", "xxx", "xxx", 'x', "nuggetSilver"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseBlocks.storageBlockMap.get(Materials.SILVER)), "xxx", "xxx", "xxx", 'x', "ingotSilver"));
            GameRegistry.addSmelting(new ItemStack(BaseBlocks.oreBlockMap.get(Materials.SILVER)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.SILVER)), 50);
            GameRegistry.addSmelting(new ItemStack(BaseItems.DUST, 1, MaterialRegistry.getIDFromMaterial(Materials.SILVER)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.SILVER)), 50);
        }

        if (ConfigMaterials.materialMap.get(ELECTRUM)) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.GEAR, 1, MaterialRegistry.getIDFromMaterial(Materials.ELECTRUM)), " x ", "xsx", " x ", 'x', "ingotElectrum", 's', "gearIron"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BaseItems.NUGGET, 9, MaterialRegistry.getIDFromMaterial(Materials.ELECTRUM)), "ingotElectrum"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.ELECTRUM)), "xxx", "xxx", "xxx", 'x', "nuggetElectrum"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseBlocks.storageBlockMap.get(Materials.ELECTRUM)), "xxx", "xxx", "xxx", 'x', "ingotElectrum"));
            GameRegistry.addSmelting(new ItemStack(BaseBlocks.oreBlockMap.get(Materials.ELECTRUM)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.ELECTRUM)), 50);
            GameRegistry.addSmelting(new ItemStack(BaseItems.DUST, 1, MaterialRegistry.getIDFromMaterial(Materials.ELECTRUM)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(Materials.ELECTRUM)), 50);
        }
        if (ConfigMaterials.materialMap.get(NICKEL)) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.GEAR, 1, MaterialRegistry.getIDFromMaterial(NICKEL)), " x ", "xsx", " x ", 'x', "ingotNickel", 's', "gearIron"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BaseItems.NUGGET, 9, MaterialRegistry.getIDFromMaterial(NICKEL)), "ingotNickel"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(NICKEL)), "xxx", "xxx", "xxx", 'x', "nuggetNickel"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseBlocks.storageBlockMap.get(NICKEL)), "xxx", "xxx", "xxx", 'x', "ingotNickel"));
            GameRegistry.addSmelting(new ItemStack(BaseBlocks.oreBlockMap.get(NICKEL)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(NICKEL)), 50);
            GameRegistry.addSmelting(new ItemStack(BaseItems.DUST, 1, MaterialRegistry.getIDFromMaterial(NICKEL)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(NICKEL)), 50);
        }
        if (ConfigMaterials.materialMap.get(ALUMINUM)) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.GEAR, 1, MaterialRegistry.getIDFromMaterial(ALUMINUM)), " x ", "xsx", " x ", 'x', "ingotAluminum", 's', "gearIron"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BaseItems.NUGGET, 9, MaterialRegistry.getIDFromMaterial(ALUMINUM)), "ingotAluminum"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(ALUMINUM)), "xxx", "xxx", "xxx", 'x', "nuggetAluminum"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BaseBlocks.storageBlockMap.get(ALUMINUM)), "xxx", "xxx", "xxx", 'x', "ingotAluminum"));
            GameRegistry.addSmelting(new ItemStack(BaseBlocks.oreBlockMap.get(ALUMINUM)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(NICKEL)), 50);
            GameRegistry.addSmelting(new ItemStack(BaseItems.DUST, 1, MaterialRegistry.getIDFromMaterial(ALUMINUM)), new ItemStack(BaseItems.INGOT, 1, MaterialRegistry.getIDFromMaterial(NICKEL)), 50);
        }
    }

    public static void postInit() {
    }
}
