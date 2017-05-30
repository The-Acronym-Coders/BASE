package com.teamacronymcoders.base.client.models.generator;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.util.Platform;
import com.teamacronymcoders.base.util.files.BaseFileUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.util.List;

@SideOnly(Side.CLIENT)
public class ModelGenerator {
    private static File blockStatesFolder;
    private static File blockModelsFolder;
    private static File itemModelsFolder;
    private static boolean isSetup = false;
    private static boolean okayToRun = false;

    public static void generate(IHasGeneratedModel model) {
        if (!isSetup) {
            setupFolders();
        }

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

    private static void setupFolders() {
        IBaseMod mod = Platform.getCurrentMod();
        if (mod != null && mod.getResourceFolder() != null) {
            blockStatesFolder = new File(mod.getResourceFolder(), "blockstates");
            BaseFileUtils.createFolder(blockStatesFolder);
            blockModelsFolder = new File(mod.getResourceFolder(), "models/block");
            BaseFileUtils.createFolder(blockModelsFolder);
            itemModelsFolder = new File(mod.getResourceFolder(), "models/item");
            BaseFileUtils.createFolder(itemModelsFolder);
            okayToRun = true;
        }
        isSetup = true;
    }
}
