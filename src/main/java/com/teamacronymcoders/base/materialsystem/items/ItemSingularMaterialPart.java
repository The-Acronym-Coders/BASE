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
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.util.files.templates.TemplateFile;
import com.teamacronymcoders.base.util.files.templates.TemplateManager;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

public class ItemSingularMaterialPart extends ItemBase implements IHasItemColor, IHasOreDict, IHasGeneratedModel {
    private final MaterialPart materialPart;
    private final int burnTime;

    public ItemSingularMaterialPart(MaterialPart materialPart) {
        super("material_part");
        this.materialPart = materialPart;
        this.burnTime = materialPart.getData().getValue("burn", 0, Integer::parseInt);;
    }

    @Override
    public List<String> getModelNames(List<String> modelNames) {
        modelNames.add(materialPart.getUnlocalizedName());
        return modelNames;
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
        return this.materialPart;
    }

    @Nonnull
    @Override
    public Map<ItemStack, String> getOreDictNames(@Nonnull Map<ItemStack, String> names) {

        for (String oreName : materialPart.getAllOreDictStrings()) {
            names.put(new ItemStack(this), oreName);
        }

        return names;
    }

    @Override
    public List<IGeneratedModel> getGeneratedModels() {
        List<IGeneratedModel> models = Lists.newArrayList();

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

        return models;
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        return burnTime;
    }

}
