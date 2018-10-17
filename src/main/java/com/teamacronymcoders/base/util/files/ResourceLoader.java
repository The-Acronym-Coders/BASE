package com.teamacronymcoders.base.util.files;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.registrysystem.SoundEventRegistry;
import com.teamacronymcoders.base.sound.IHasSoundGroup;
import com.teamacronymcoders.base.sound.SoundGroup;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@SideOnly(Side.CLIENT)
public class ResourceLoader {
    private File resourceFolder;
    private final Gson gson;

    public ResourceLoader() {
        gson = new Gson();
    }

    public void setup() throws NoSuchFieldException, IllegalAccessException {
        Field minecraftDirField = Loader.class.getDeclaredField("minecraftDir");
        minecraftDirField.setAccessible(true);
        Object minecraftDirObject = minecraftDirField.get(null);
        if (minecraftDirObject instanceof File) {
            File minecraftDir = (File)minecraftDirObject;
            this.resourceFolder = new File(minecraftDir, "resources");
            BaseFileUtils.createFolder(this.resourceFolder);

            List<IResourcePack> defaultResourcePacks = ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), "defaultResourcePacks", "field_110449_ao", "ap");
            defaultResourcePacks.add(new DirectoryResourcePack(this.resourceFolder));
        }

        createPackMcMeta();
        prepareResources();
    }

    private void prepareResources() {
        fixLangFolder();
    }

    private void fixLangFolder() {
        File[] modFolders = this.resourceFolder.listFiles(File::isDirectory);
        if (Objects.nonNull(modFolders)) {
            Arrays.stream(modFolders)
                    .map(modFolder -> new File(modFolder, "lang"))
                    .filter(File::exists)
                    .filter(File::isDirectory)
                    .map(File::listFiles)
                    .filter(Objects::nonNull)
                    .flatMap(Arrays::stream)
                    .forEach(this::fixLangFile);
        }

    }

    Pattern pattern = Pattern.compile(" += +");

    private void fixLangFile(File file) {
        String fileString = BaseFileUtils.readFileToString(file);
        fileString = pattern.matcher(fileString).replaceAll("=");
        BaseFileUtils.writeStringToFile(fileString, file);
    }

    private void createPackMcMeta() {
        String mcMeta = "{\"pack\":{\"pack_format\":3,\"description\":\"B.A.S.E External Resources\"}}";
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

    public void handleSoundsJsonForMod(IBaseMod mod) {
        Map<String, SoundGroup> soundGroups = mod.getRegistryHolder().getRegistry(SoundEventRegistry.class, "SOUND_EVENT")
                .getEntries().values().parallelStream()
                .filter(soundEvent -> soundEvent instanceof IHasSoundGroup)
                .map(soundEvent -> (IHasSoundGroup)soundEvent)
                .map(soundEvent -> Pair.of(soundEvent.getName(), soundEvent.getSoundGroup()))
                .collect(Collectors.toMap(Pair::getLeft, Pair::getRight));

        if (!soundGroups.values().isEmpty()) {
            File soundsJson = new File(new File(resourceFolder, mod.getID()), "sounds.json");
            Map<String, SoundGroup> currentSoundGroups = gson.fromJson(BaseFileUtils.readFileToString(soundsJson),
                    new TypeToken<Map<String, SoundGroup>>(){}.getType());

            soundGroups.forEach(currentSoundGroups::putIfAbsent);

            BaseFileUtils.writeStringToFile(gson.toJson(currentSoundGroups), soundsJson);
        }
    }

}
