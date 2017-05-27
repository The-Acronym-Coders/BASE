package com.teamacronymcoders.base.client.models.generator;

import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.util.files.BaseFileUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.util.List;

import static com.teamacronymcoders.base.client.models.generator.generatedmodel.ModelType.BLOCKSTATE;

@SideOnly(Side.CLIENT)
public class ModelGenerator {
    private static File blockstateFolder;
    private static File blockModelsFolder;
    private static File itemModelsFolder;
    private static boolean isSetup = false;

    public static void generate(IHasGeneratedModel model) {
        if (!isSetup) {
            setupFolders();
        }

        List<IGeneratedModel> generatedModels = model.getGeneratedModels();
        generatedModels.forEach(resource -> {
            File file = null;
            switch(resource.getModelType()) {
                case BLOCKSTATE:
                    file = new File(blockstateFolder, resource.getName() + ".json");
                    break;
                case BLOCK_MODEL:
                    file = new File(blockModelsFolder, resource.getName() + ".json");
                    break;
                case ITEM_MODEL:
                    file = new File(itemModelsFolder, resource.getName() + ".json");
                    break;
            }
            if (file != null && !file.exists()) {
                BaseFileUtils.writeStringToFile(resource.getJson(), file);
            }
        });

    }

    private static void setupFolders() {

    }
}
