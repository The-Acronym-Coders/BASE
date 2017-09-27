package com.teamacronymcoders.base.materialsystem.items;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.client.models.generator.IHasGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.GeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.ModelType;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.util.files.templates.TemplateFile;
import com.teamacronymcoders.base.util.files.templates.TemplateManager;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ItemMaterialArmor extends ItemArmor implements IHasModel, IHasGeneratedModel {
    private MaterialPart materialPart;
    private IBaseMod mod;

    public ItemMaterialArmor(MaterialPart materialPart, ArmorMaterial material, EntityEquipmentSlot equipmentSlot) {
        super(material, 0, equipmentSlot);
        this.materialPart = materialPart;
        this.setUnlocalizedName(materialPart.getMaterial().getUnlocalizedName() + "_" + equipmentSlot.getName().toLowerCase(Locale.US));
    }

    @Override
    public boolean hasColor(@Nonnull ItemStack stack) {
        return materialPart.isColorized();
    }

    @Override
    public int getColor(@Nonnull ItemStack stack) {
        return materialPart.getColor();
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack itemStack) {
        //noinspection deprecation
        return I18n.translateToLocalFormatted(this.materialPart.getPart()
                        .getUnlocalizedName() + "." + this.armorType.getName(),
                this.materialPart.getMaterial().getName());
    }

    @Override
    public IBaseMod getMod() {
        return mod;
    }

    @Override
    public void setMod(IBaseMod mod) {
        this.mod = mod;
    }

    @Override
    public Item getItem() {
        return this;
    }

    @Override
    public List<String> getModelNames(List<String> modelNames) {
        modelNames.add(materialPart.getMaterial().getUnlocalizedName() + "_" + this.armorType.getName().toLowerCase(Locale.US));
        return modelNames;
    }

    @Override
    public List<IGeneratedModel> getGeneratedModels() {
        List<IGeneratedModel> models = Lists.newArrayList();

        TemplateFile templateFile = TemplateManager.getTemplateFile("item_model");
        Map<String, String> replacements = Maps.newHashMap();
        replacements.put("texture", "base:items/" + this.armorType.getName().toLowerCase(Locale.US));
        templateFile.replaceContents(replacements);
        models.add(new GeneratedModel(materialPart.getMaterial().getUnlocalizedName() + "_" +
                this.armorType.getName().toLowerCase(Locale.US), ModelType.ITEM_MODEL, templateFile.getFileContents()));

        return models;
    }
}
