package com.teamacronymcoders.base.client.models.generator;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.util.files.BaseFileUtils;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.util.List;

public class ModelGenerator {
    private final File blockStatesFolder;
    private final File blockModelsFolder;
    private final File itemModelsFolder;
    private final boolean okayToRun;

    public ModelGenerator(IBaseMod mod) {
        File resourceFolder = mod.getResourceFolder();
        if (resourceFolder != null) {
            blockStatesFolder = new File(resourceFolder, "blockstates");
            BaseFileUtils.createFolder(blockStatesFolder);
            blockModelsFolder = new File(resourceFolder, "models/block");
            BaseFileUtils.createFolder(blockModelsFolder);
            itemModelsFolder = new File(resourceFolder, "models/item");
            BaseFileUtils.createFolder(itemModelsFolder);
            okayToRun = true;
        } else {
            blockStatesFolder = null;
            blockModelsFolder = null;
            itemModelsFolder = null;
            okayToRun = false;
        }
    }

    public void generate(IHasGeneratedModel model) {
        if (okayToRun) {
            List<IGeneratedModel> generatedModels = model.getGeneratedModels();
            generatedModels.forEach(resource -> {
                File file = null;
                switch(resource.getModelType()) {
                    case BLOCKSTATE:
                        file = new File(blockStatesFolder, resource.getName() + ".json");
                        break;
                    case BLOCK_MODEL:
                        file = new File(blockModelsFolder, resource.getName() + ".json");
                        break;
                    case ITEM_MODEL:
                        file = new File(itemModelsFolder, resource.getName() + ".json");
                        break;
                }
                if (!file.exists()) {
                    BaseFileUtils.writeStringToFile(resource.getJson(), file);
                }
            });
        }
    }
}
