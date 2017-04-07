package com.teamacronymcoders.base.api.materials;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.recipes.BasicRecipes;
import com.teamacronymcoders.base.util.OreDictUtils;
import com.teamacronymcoders.base.util.TextUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MaterialType {

    private String name;
    private Color colour;
    private ArrayList<EnumPartType> types;
    private boolean hasEffect;


    public MaterialType() {
        this("null", Color.white, false, new ArrayList<EnumPartType>());
    }

    public MaterialType(String name, Color colour, boolean hasEffect, ArrayList<EnumPartType> types) {
        this.name = name;
        this.colour = colour;
        this.hasEffect = hasEffect;
        this.types = types;
    }

    public MaterialType(String name, Color colour, boolean hasEffect) {
        this.name = name;
        this.colour = colour;
        this.hasEffect = hasEffect;
        this.types = new ArrayList<>();
    }

    public MaterialType(String name, Color colour, boolean hasEffect, EnumPartType... types) {
        this(name, colour, hasEffect, new ArrayList<EnumPartType>(Arrays.asList(types)));
    }

    public String getName() {
        return name;
    }

    public String getUnlocalizedName() {
        return "material.base." + this.name.toLowerCase();
    }

    public String getLocalizedName() {
        return Base.languageHelper.none(getName());
    }

    public Color getColour() {
        return colour;
    }

    public ArrayList<EnumPartType> getTypes() {
        return types;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public boolean isTypeSet(EnumPartType type) {
        return this.getTypes().contains(type);
    }

    public boolean isHasEffect() {
        return hasEffect;
    }

    public void setHasEffect(boolean hasEffect) {
        this.hasEffect = hasEffect;
    }

    public enum EnumPartType {
        BLOCK {
            @Override
            public List<IRecipe> getRecipes(List<IRecipe> recipes, MaterialType materialType, ItemStack currentPart) {
                if (!materialType.isTypeSet(INGOT)) {
                    this.addCompressedRecipe(recipes, materialType, currentPart, "ingot", true);
                }

                return recipes;
            }
        },
        DUST {
            @Override
            public List<IRecipe> getRecipes(List<IRecipe> recipes, MaterialType materialType, ItemStack currentPart) {
                if (!materialType.isTypeSet(INGOT)) {
                    this.addFurnaceRecipe(materialType, currentPart, "ingot", false);
                }
                return recipes;
            }
        },
        GEAR,
        INGOT {
            @Override
            public List<IRecipe> getRecipes(List<IRecipe> recipes, MaterialType materialType, ItemStack currentPart) {
                this.addCompressedRecipe(recipes, materialType, currentPart, "nugget", true);
                this.addCompressedRecipe(recipes, materialType, currentPart, "block", false);
                this.addFurnaceRecipe(materialType, currentPart, "dust", true);
                this.addFurnaceRecipe(materialType, currentPart, "ore", true);
                return recipes;
            }
        },
        NUGGET {
            @Override
            public List<IRecipe> getRecipes(List<IRecipe> recipes, MaterialType materialType, ItemStack currentPart) {
                if (!materialType.isTypeSet(INGOT)) {
                    this.addCompressedRecipe(recipes, materialType, currentPart, "ingot", false);
                }
                this.addFurnaceRecipe(materialType, currentPart, "poorOre", true);
                return recipes;
            }
        },
        ORE {
            @Override
            public List<IRecipe> getRecipes(List<IRecipe> recipes, MaterialType materialType, ItemStack currentPart) {
                if (!materialType.isTypeSet(INGOT)) {
                    this.addFurnaceRecipe(materialType, currentPart, "ingot", false);
                }
                return recipes;
            }
        },
        POOR_ORE {
            @Override
            public List<IRecipe> getRecipes(List<IRecipe> recipes, MaterialType materialType, ItemStack currentPart) {
                if (!materialType.isTypeSet(NUGGET)) {
                    this.addFurnaceRecipe(materialType, currentPart, "nugget", false);
                }
                return recipes;
            }

            @Override
            public String getOreDictName(MaterialType materialType) {
                return "poorOre" + TextUtils.removeSpecialCharacters(materialType.getName());
            }
        },
        PLATE;

        public String getUnlocalizedName() {
            return "base.part." + this.getName().toLowerCase();
        }

        public String getLocalizedName() {
            return Base.languageHelper.none(getUnlocalizedName());
        }

        public String getOreDictName(MaterialType materialType) {
            return this.getName().toLowerCase() + TextUtils.removeSpecialCharacters(materialType.getName());
        }

        public String getName() {
            return this.name();
        }

        public String getLowerCaseName() {
            return this.getName().toLowerCase();
        }

        public List<IRecipe> getRecipes(List<IRecipe> recipes, MaterialType materialType, ItemStack currentPart) {
            return recipes;
        }

        protected void addCompressedRecipe(List<IRecipe> recipes, MaterialType materialType, ItemStack currentPart,
                                           String otherOreDict, boolean currentPartIsResult) {
            String materialName = materialType.getName().replace(" ", "");
            ItemStack newPart = OreDictUtils.getPreferredItemStack(otherOreDict + materialName);
            if (newPart != null) {
                if (currentPartIsResult) {
                    recipes.add(BasicRecipes.createCompressedRecipe(newPart, currentPart));
                    recipes.add(BasicRecipes.createUnCompressingRecipe(currentPart, newPart));
                } else {
                    recipes.add(BasicRecipes.createCompressedRecipe(currentPart, newPart));
                    recipes.add(BasicRecipes.createUnCompressingRecipe(newPart, currentPart));
                }
            }
        }

        protected void addFurnaceRecipe(MaterialType materialType, ItemStack currentPart, String otherOreDict, boolean currentPartIsResult) {
            String materialName = materialType.getName().replace(" ", "");
            ItemStack newPart = OreDictUtils.getPreferredItemStack(otherOreDict + materialName);
            if (newPart != null) {
                if (currentPartIsResult) {
                    FurnaceRecipes.instance().addSmeltingRecipe(newPart, currentPart, 1);
                } else {
                    FurnaceRecipes.instance().addSmeltingRecipe(currentPart, newPart, 1);
                }
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MaterialType{");
        sb.append("name='").append(name).append('\'');
        sb.append(", colour=").append(colour);
        sb.append(", types=").append(types);
        sb.append('}');

        return sb.toString();
    }
}
