package com.teamacronymcoders.base.blocks;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.client.models.generator.IHasGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.GeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.ModelType;
import com.teamacronymcoders.base.items.itemblocks.ItemBlockModel;
import com.teamacronymcoders.base.util.files.templates.TemplateFile;
import com.teamacronymcoders.base.util.files.templates.TemplateManager;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.Map;

public class BlockBase extends BlockBaseNoModel implements IHasModel, IHasGeneratedModel {
    public BlockBase(Material mat) {
        super(mat);
    }

    public BlockBase(Material mat, String name) {
        super(mat, name);
        this.setItemBlock(new ItemBlockModel<>(this));
    }

    @Override
    public List<String> getModelNames(List<String> modelNames) {
        modelNames.add(this.getName());
        return modelNames;
    }

    @Override
    public List<IGeneratedModel> getGeneratedModels() {
        List<IGeneratedModel> models = Lists.newArrayList();
        this.getResourceLocations(Lists.newArrayList()).forEach(resourceLocation ->  {
            TemplateFile templateFile = TemplateManager.getTemplateFile("block");
            Map<String, String> replacements = Maps.newHashMap();

            replacements.put("texture", new ResourceLocation(resourceLocation.getResourceDomain(),
                    "blocks/" + resourceLocation.getResourcePath()).toString());
            templateFile.replaceContents(replacements);

            models.add(new GeneratedModel(resourceLocation.getResourcePath(), ModelType.BLOCKSTATE,
                    templateFile.getFileContents()));
        });

        return models;
    }
}
