package com.teamacronymcoders.base.materialsystem.items;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.teamacronymcoders.base.client.models.generator.IHasGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.GeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.ModelType;
import com.teamacronymcoders.base.items.IHasItemColor;
import com.teamacronymcoders.base.items.IHasOreDict;
import com.teamacronymcoders.base.items.ItemBase;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.parttype.ItemPartType;
import com.teamacronymcoders.base.util.files.templates.TemplateFile;
import com.teamacronymcoders.base.util.files.templates.TemplateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemMaterialPart extends ItemBase implements IHasItemColor, IHasOreDict, IHasGeneratedModel {
    private Map<Integer, MaterialPart> itemMaterialParts;
    private final String modid;
    
    public ItemMaterialPart(String modid) {
        super("material_part");
        this.setHasSubtypes(true);
        this.modid = modid;
    }

    public ItemMaterialPart(String modid, Integer integer) {
        this("material_part_" + integer);
    }

    @Override
    public List<String> getModelNames(List<String> modelNames) {
        return modelNames;
    }

    @Override
    public List<ItemStack> getAllSubItems(List<ItemStack> itemStacks) {
        this.getItemMaterialParts().values().forEach(materialPart -> itemStacks.add(materialPart.getItemStack()));
        return itemStacks;
    }

    @Override
    public int getColorFromItemstack(@Nonnull ItemStack itemStack, int tintIndex) {
        MaterialPart materialPart = this.getMaterialPartFromItemStack(itemStack);
        if (materialPart.getPart().hasOverlayTexture()) {
            return tintIndex == 1 ? this.getMaterialPartFromItemStack(itemStack).getColor() : -1;
        } else {
            return tintIndex == 0 ? this.getMaterialPartFromItemStack(itemStack).getColor() : -1;
        }
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack itemStack) {
        return this.getMaterialPartFromItemStack(itemStack).getLocalizedName();
    }

    @Override
    public boolean hasEffect(@Nonnull ItemStack itemStack) {
        return this.getMaterialPartFromItemStack(itemStack).hasEffect();
    }

    @Nonnull
    private MaterialPart getMaterialPartFromItemStack(ItemStack itemStack) {
        MaterialPart materialPart = this.itemMaterialParts.get(itemStack.getItemDamage());
        return materialPart != null ? materialPart : MaterialSystem.MISSING_MATERIAL_PART;
    }

    public Map<Integer, MaterialPart> getItemMaterialParts() {
        if (itemMaterialParts == null) {
            itemMaterialParts = new HashMap<>();
        }
        return itemMaterialParts;
    }

    @Nonnull
    @Override
    public Map<ItemStack, String> getOreDictNames(@Nonnull Map<ItemStack, String> names) {
        for (Map.Entry<Integer, MaterialPart> entry : this.getItemMaterialParts().entrySet()) {
            for (String oreName : entry.getValue().getAllOreDictStrings()) {
                names.put(new ItemStack(this, 1, entry.getKey()), oreName);
            }
        }
        return names;
    }

    public void addMaterialPart(int id, MaterialPart materialPart) {
        this.getItemMaterialParts().put(id, materialPart);
    }

    @Override
    public List<ResourceLocation> getResourceLocations(List<ResourceLocation> resourceLocations) {
        this.getItemMaterialParts().forEach((id, materialPart) -> resourceLocations.add(
                new ResourceLocation(modid, materialPart.getUnlocalizedName())));
        return resourceLocations;
    }

    @Override
    public List<IGeneratedModel> getGeneratedModels() {
        List<IGeneratedModel> models = Lists.newArrayList();
        for (MaterialPart materialPart : this.getItemMaterialParts().values()) {
            TemplateFile templateFile;
            Map<String, String> replacements = Maps.newHashMap();

            if (materialPart.getPart().hasOverlayTexture()) {
                templateFile = TemplateManager.getTemplateFile("item_model_overlaid");
                replacements.put("texture", materialPart.getPart().getOwnerId() + ":items/" + materialPart.getPart().getShortUnlocalizedName());
                replacements.put("texture_overlay", materialPart.getPart().getOwnerId() + ":items/" + materialPart.getPart().getShortUnlocalizedName() + "_overlay");
            } else {
                templateFile = TemplateManager.getTemplateFile("item_model");
                replacements.put("texture", materialPart.getPart().getOwnerId() + ":items/" + materialPart.getPart().getShortUnlocalizedName());
            }

            templateFile.replaceContents(replacements);
            models.add(new GeneratedModel(materialPart.getUnlocalizedName(), ModelType.ITEM_MODEL, templateFile.getFileContents()));
        }
        return models;
    }

    @Override
    public int getItemBurnTime(ItemStack stack) {
        return getMaterialPartFromItemStack(stack).getData().getValue(ItemPartType.BURN_DATA_NAME, 0, Integer::parseInt);
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public int getItemStackLimit(ItemStack stack)
    {
        return getMaterialPartFromItemStack(stack).getData().getValue(ItemPartType.STACKSIZE_DATA_NAME, this.getItemStackLimit(), Integer::parseInt);
    }

}
