package com.teamacronymcoders.base.items;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.client.models.generator.IHasGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.*;
import com.teamacronymcoders.base.util.files.templates.TemplateFile;
import com.teamacronymcoders.base.util.files.templates.TemplateManager;

import net.minecraft.util.ResourceLocation;

public class ItemBase extends ItemBaseNoModel implements IHasModel, IHasGeneratedModel {
    public ItemBase(String name) {
        this("", name);
    }

    public ItemBase(String texturePath, String name) {
        super(texturePath, name);
    }

    @Override
    public List<String> getModelNames(List<String> modelNames) {
        modelNames.add(texturePath + name);
        return modelNames;
    }
    
    @Override
    public List<IGeneratedModel> getGeneratedModels() {
        List<IGeneratedModel> models = Lists.newArrayList();
        this.getResourceLocations(Lists.newArrayList()).forEach(resourceLocation ->  {
            TemplateFile templateFile = TemplateManager.getTemplateFile("item_model");
            Map<String, String> replacements = Maps.newHashMap();

            replacements.put("texture", new ResourceLocation(resourceLocation.getNamespace(),
                    "items/" + resourceLocation.getPath()).toString());
            templateFile.replaceContents(replacements);

            models.add(new GeneratedModel(resourceLocation.getPath(), ModelType.ITEM_MODEL,
                    templateFile.getFileContents()));
        });

        return models;
    }
}
