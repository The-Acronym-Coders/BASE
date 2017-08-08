package com.teamacronymcoders.base.util.files;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

@SideOnly(Side.CLIENT)
public class ResourceLoader {
    private ResourcePackAssembler assembler;
    private File resourceFolder;

    public ResourceLoader(File resourceFolder) {
        this.resourceFolder = resourceFolder;
    }

    public void assembleResourcePack() {
        assembler = new ResourcePackAssembler(resourceFolder, "Resources");

        copyFilesFromFolder("", resourceFolder);
        assembler.assemble().inject();
    }

    private void copyFilesFromFolder(String path, File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    copyFilesFromFolder(path + "/" + file.getName(), file);
                } else {
                    assembler.addFile(path, file);
                }
            }
        }
    }

    public void createImportantFolders(String modid) {
        File modFolder = new File(resourceFolder, modid);
        File lang = new File(modFolder, "lang");
        BaseFileUtils.createFolder(lang);
        File enUsLang = new File(lang, "en_us.lang");
        BaseFileUtils.createFile(enUsLang);
        File textures = new File(resourceFolder, "textures");
        BaseFileUtils.createFolder(textures);
        BaseFileUtils.createFolder(new File(textures, "blocks"));
        BaseFileUtils.createFolder(new File(textures, "items"));

        BaseFileUtils.createFolder(new File(resourceFolder, "blockstates"));

        File models = new File(resourceFolder, "models");
        BaseFileUtils.createFolder(models);
        BaseFileUtils.createFolder(new File(models, "block"));
        BaseFileUtils.createFolder(new File(models, "item"));
    }

}
