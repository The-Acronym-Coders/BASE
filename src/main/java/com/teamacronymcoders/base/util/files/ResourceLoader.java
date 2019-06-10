package com.teamacronymcoders.base.util.files;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.FolderPackFinder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.io.File;

@OnlyIn(Dist.CLIENT)
public class ResourceLoader {
    private final File resourceFolder;

    public ResourceLoader() {
        this.resourceFolder = new File(Minecraft.getInstance().gameDir, "resources");
        BaseFileUtils.createFolder(this.resourceFolder);

        //TODO Confirm this works
        Minecraft.getInstance().getResourcePackList().addPackFinder(new FolderPackFinder(resourceFolder));


        createPackMcMeta();
    }

    private void createPackMcMeta() {
        String mcMeta = "{\"pack\":{\"pack_format\":4,\"description\":\"B.A.S.E External Resources\"}}";
        BaseFileUtils.writeStringToFile(mcMeta, new File(this.resourceFolder, "pack.mcmeta"));
    }

    public void createImportantFolders(String modid) {
        File modFolder = new File(resourceFolder, modid);
        BaseFileUtils.createFolder(modFolder);
        File lang = new File(modFolder, "lang");
        BaseFileUtils.createFolder(lang);
        File enUsLang = new File(lang, "en_us.lang");
        if (!enUsLang.exists()) {
            BaseFileUtils.writeStringToFile("", enUsLang);
        }
        File textures = new File(modFolder, "textures");
        BaseFileUtils.createFolder(textures);
        BaseFileUtils.createFolder(new File(textures, "blocks"));
        BaseFileUtils.createFolder(new File(textures, "items"));

        BaseFileUtils.createFolder(new File(modFolder, "blockstates"));

        File models = new File(modFolder, "models");
        BaseFileUtils.createFolder(models);
        BaseFileUtils.createFolder(new File(models, "block"));
        BaseFileUtils.createFolder(new File(models, "item"));

        File sounds = new File(modFolder, "sounds");
        BaseFileUtils.createFolder(sounds);
    }

}
