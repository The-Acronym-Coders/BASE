package com.teamacronymcoders.base.materialsystem.items;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.Reference;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.client.models.generator.IHasGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.GeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.ModelType;
import com.teamacronymcoders.base.items.IHasItemColor;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.util.ItemStackUtils;
import com.teamacronymcoders.base.util.files.templates.TemplateFile;
import com.teamacronymcoders.base.util.files.templates.TemplateManager;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class ItemMaterialArmor extends ItemArmor implements IHasModel, IHasItemColor, IHasGeneratedModel {
    private MaterialPart materialPart;
    private IBaseMod mod;
    private NonNullList<ItemStack> repairList;

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

    @Override
    public int getColorFromItemstack(@Nonnull ItemStack stack, int tintIndex) {
        return this.getColor(stack);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return String.format("%s:textures/models/armor/material_layer_%d.png", Reference.MODID, (isLegSlot(slot) ? 2 : 1));
    }

    @Override
    public boolean hasOverlay(@Nonnull ItemStack stack) {
        return false;
    }

    private boolean isLegSlot(EntityEquipmentSlot slot) {
        return slot == EntityEquipmentSlot.LEGS;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, @Nullable ItemStack repair) {
        return repair != null && this.getRepairItems().stream().anyMatch(repairItem -> ItemStack.areItemsEqual(repair, repairItem));
    }

    public NonNullList<ItemStack> getRepairItems() {
        if (repairList == null) {
            repairList = OreDictionary.getOres(this.getIngotName());
        }
        return repairList;
    }

    public String getIngotName() {
        return this.materialPart.getOreDictString().replace("armor", "ingot");
    }
}
