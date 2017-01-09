package com.teamacronymcoders.base.modules.texturegenerator;

import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import com.teamacronymcoders.base.reference.Reference;
import com.teamacronymcoders.base.util.Platform;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Module("")
public class ModuleTextureGeneration extends ModuleBase {
    @Override
    public String getName() {
        return "Texture Generation";
    }

    @Override
    public boolean getIsActive() {
        return Platform.generateBaseTextures();
    }

    @Override
    public boolean isConfigurable() {
        return false;
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        this.getBlockRegistry().getEntries().forEach((name, block) -> {
            String key = name.getResourcePath();
            writeBlockModelFile(key, key);
            writeLangFile(key, key);
        });
        this.getItemRegistry().getEntries().forEach((name, item) -> {
            String key = name.getResourcePath();
            writeItemModelFile(key, key);
            writeLangFile(key, key);
        });
    }

    private File getAssetDirectory() {
        File userDir = null;
        File currentDir = new File(System.getProperty("user.dir"));
        while (userDir == null) {
            if (currentDir.getParentFile().listFiles((dir, name) -> name.equals("src")).length == 0) {
                currentDir = currentDir.getParentFile();
            } else {
                userDir = currentDir.getParentFile();
            }
        }

        String assetPath = "src/main/resources/assets/" + this.getMod().getID() + "/";
        assetPath = assetPath.replace("/", File.separator);
        return new File(userDir, assetPath);
    }

    private void writeBlockModelFile(String key, String texture) {
        try {
            String modid = this.getMod().getID();
            File assetDir = getAssetDirectory();
            File baseBlockState = new File(assetDir, "blockstates" + File.separator + key + ".json");
            File baseBlockModel = new File(assetDir, "models/block/" + key + ".json");
            File baseItem = new File(assetDir, "models/item/" + key + ".json");

            FileHelper fileHelper = new FileHelper();

            if (!baseBlockState.exists()) {
                if (baseBlockState.createNewFile()) {
                    fileHelper.writeFile(baseBlockState, fileHelper.scanFile(modid, key, texture,
                            new File(System.getProperty("user.home") + "/getFluxed/baseBlockState.json")));
                }
            }

            if (!baseBlockModel.exists()) {
                if (baseBlockModel.createNewFile()) {
                    fileHelper.writeFile(baseBlockModel, fileHelper.scanFile(modid, key, texture,
                            new File(System.getProperty("user.home") + "/getFluxed/baseBlockModel.json")));
                }
            }

            if (!baseItem.exists()) {
                if (baseItem.createNewFile()) {
                    fileHelper.writeFile(baseItem, fileHelper.scanFile(modid, key, texture,
                            new File(System.getProperty("user.home") + "/getFluxed/baseBlockItem.json")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeLangFile(String key, String name) {
        try {
            File lang = new File(getAssetDirectory(), "/lang/en_US.lang");

            if (!lang.exists()) {
                lang.createNewFile();
            }
            Scanner scan = new Scanner(lang);
            List<String> content = new ArrayList<>();
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                content.add(line);
            }
            scan.close();
            if (!content.contains((String.format("tile.%s.name=%s", key, name))))
                content.add(String.format("tile.%s.name=%s", key, name));
            FileWriter write = new FileWriter(lang);
            for (String s : content) {
                write.write(s + "\n");
            }
            write.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeItemModelFile(String key, String texture) {
        File file = new File(new File(System.getProperty("user.dir")).getParentFile(), "src/main/resources/assets/" + Reference.MODID + "/models/item/" + key + ".json");

        if (!file.exists()) {
            try {
                file.createNewFile();
                FileHelper fileHelper = new FileHelper();
                fileHelper.writeFile(file, fileHelper.scanFile(this.getMod().getID(), key, texture,
                        new File(System.getProperty("user.home") + "/getFluxed/baseItem.json")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
