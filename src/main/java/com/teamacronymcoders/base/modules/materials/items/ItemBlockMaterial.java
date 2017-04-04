package com.teamacronymcoders.base.modules.materials.items;

import com.teamacronymcoders.base.api.materials.MaterialType;
import com.teamacronymcoders.base.api.materials.MaterialType.EnumPartType;
import com.teamacronymcoders.base.items.IHasItemColor;
import com.teamacronymcoders.base.items.IHasOreDict;
import com.teamacronymcoders.base.items.IHasRecipe;
import com.teamacronymcoders.base.items.ItemBlockGeneric;
import com.teamacronymcoders.base.modules.materials.blocks.BlockMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

public class ItemBlockMaterial extends ItemBlockGeneric<BlockMaterial> implements IHasItemColor, IHasOreDict, IHasRecipe {
    public ItemBlockMaterial(BlockMaterial block) {
        super(block);
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return this.getActualBlock().getLocalizedName();
    }

    @Override
    public List<String> getModelNames(List<String> modelNames) {
        modelNames.add(this.getPartType().getLowerCaseName());
        return modelNames;
    }

    @Override
    public int getColorFromItemstack(@Nonnull ItemStack stack, int tintIndex) {
        if (this.getMaterialType() != null && tintIndex == 0) {
            return this.getMaterialType().getColour().getRGB();
        }
        return 0xFFFFFF;
    }

    @Nonnull
    @Override
    public Map<ItemStack, String> getOreDictNames(@Nonnull Map<ItemStack, String> names) {
        String oreDictName = this.getPartType().getName().toLowerCase() + this.getMaterialType().getName().replace(" ", "");
        names.put(new ItemStack(this), oreDictName);
        return names;
    }

    @Override
    public List<IRecipe> getRecipes(List<IRecipe> recipes) {
        return this.getPartType().getRecipes(recipes, this.getMaterialType(), new ItemStack(this));
    }

    public MaterialType getMaterialType() {
        return this.getActualBlock().getMaterialType();
    }

    public EnumPartType getPartType() {
        return this.getActualBlock().getPartType();
    }
}
