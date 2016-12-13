package com.teamacronymcoders.base.modules.materials.items;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.api.materials.MaterialRegistry;
import com.teamacronymcoders.base.api.materials.MaterialType;
import com.teamacronymcoders.base.items.IHasItemColor;
import com.teamacronymcoders.base.items.IHasOreDict;
import com.teamacronymcoders.base.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

/**
 * Created by Jared on 6/30/2016
 */
public class ItemPart extends ItemBase implements IHasOreDict, IHasItemColor {

    MaterialType.EnumPartType type;

    public ItemPart(MaterialType.EnumPartType type) {
        super(type.getName().toLowerCase());
        this.type = type;
        setHasSubtypes(true);
    }

    @Override
    public void getSubItems(@Nonnull Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        getAllSubItems(subItems);
    }

    @Override
    @Nonnull
    public String getUnlocalizedName(ItemStack stack) {
        MaterialType mat = MaterialRegistry.getFromID(stack.getItemDamage());
        if (mat != null && mat.isTypeSet(type))
            return String.format("item.base.%s.%s", this.type.name().toLowerCase(), mat.getName().toLowerCase());

        return "item.base.null_part";
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        MaterialType mat = MaterialRegistry.getFromID(stack.getItemDamage());
        if (mat != null && mat.isTypeSet(type))
            return String.format("%s %s", mat.getLocalizedName(), this.type.getLocalizedName());

        return TextFormatting.RED + Base.languageHelper.error("null_part");
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        MaterialType mat = MaterialRegistry.getFromID(stack.getItemDamage());
        if (mat != null && mat.isTypeSet(type)) {
            return mat.isHasEffect();
        }
        return super.hasEffect(stack);
    }

    @Override
    public List<String> getModelNames(List<String> modelNames) {
        modelNames.add(type.getName().toLowerCase());
        return modelNames;
    }

    @Override
    @Nonnull
    public Map<ItemStack, String> getOreDictNames(@Nonnull Map<ItemStack, String> names) {
        for (Map.Entry<Integer, MaterialType> ent : MaterialRegistry.getMaterials().entrySet()) {
            MaterialType mat = ent.getValue();
            ItemStack itemStack = new ItemStack(this, 1, ent.getKey());
            String oreDictName = type.getName().toLowerCase() + mat.getName().replace(" ", "");
            names.put(itemStack, oreDictName);
        }
        return names;
    }

    @Override
    public int getColorFromItemstack(@Nonnull ItemStack stack, int tintIndex) {
        MaterialType material = MaterialRegistry.getFromID(stack.getItemDamage());
        if (material != null && tintIndex == 0) {
            return material.getColour().getRGB();
        }
        return 0xFFFFFF;
    }

    @Override
    public List<ItemStack> getAllSubItems(List<ItemStack> itemStacks) {
        MaterialRegistry.getMaterials().entrySet().stream().filter(entry -> entry.getValue().isTypeSet(type))
                .forEach(entry -> itemStacks.add(new ItemStack(this, 1, entry.getKey())));
        return itemStacks;
    }
}
