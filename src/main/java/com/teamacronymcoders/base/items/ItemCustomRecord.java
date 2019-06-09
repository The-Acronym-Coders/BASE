package com.teamacronymcoders.base.items;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.client.models.generator.IHasGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.GeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.ModelType;
import com.teamacronymcoders.base.util.Coloring;
import com.teamacronymcoders.base.util.files.templates.TemplateFile;
import com.teamacronymcoders.base.util.files.templates.TemplateManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

public class ItemCustomRecord extends ItemRecord implements IHasGeneratedModel, IHasModel, IHasItemColor {
    private IBaseMod mod;
    private final Coloring color;
    private final String recordName;

    public ItemCustomRecord(String recordName, SoundEvent soundEvent, Coloring color) {
        super(recordName, soundEvent);
        this.setTranslationKey(recordName);
        this.color = color;
        this.recordName = recordName;
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
    public int getColorFromItemstack(@Nonnull ItemStack stack, int tintIndex) {
        return color != null && tintIndex == 1 ? color.getIntColor() : -1;
    }

    @Override
    public Item getItem() {
        return this;
    }

    @Override
    public List<String> getModelNames(List<String> modelNames) {
        modelNames.add(this.recordName);
        return modelNames;
    }

    @Override
    public List<IGeneratedModel> getGeneratedModels() {
        List<IGeneratedModel> models = Lists.newArrayList();

        TemplateFile templateFile;
        Map<String, String> replacements = Maps.newHashMap();

        templateFile = TemplateManager.getTemplateFile("item_model_overlaid");
        replacements.put("texture", "base:items/record_outside");
        replacements.put("texture_overlay", "base:items/record_color");


        templateFile.replaceContents(replacements);
        models.add(new GeneratedModel(this.recordName, ModelType.ITEM_MODEL,
                templateFile.getFileContents()));

        return models;
    }
}
