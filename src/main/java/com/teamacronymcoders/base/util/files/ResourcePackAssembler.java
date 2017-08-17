package com.teamacronymcoders.base.util.files;

import com.teamacronymcoders.base.util.Platform;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.FileResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Borrowed from EnderCore
 * (https://github.com/SleepyTrousers/EnderCore/blob/1.10/src/main/java/com/enderio/core/common/util/ResourcePackAssembler.java)
 * <p>
 * A class that can be used to inject resources from files/folders outside your
 * mod resources. Useful for loading textures and other assets from the config
 * dir or elsewhere.
 * <p>
 * To use, first construct an instance of this class, then add all your assets
 * {@link #addFile(String, File)}.
 * <p>
 * Once all files have been added, {@link #assemble()} Will create a zip of all
 * the files in the {@link File directory} passed into the constructor.
 * <p>
 * Finally, {@link #inject()} will insert this resource pack into the game.
 * <p>
 * Also, {@link #setHasPackPng(Class)} allows your resource pack to have a logo.
 */
@SideOnly(Side.CLIENT)
public class ResourcePackAssembler {
    private static final String MC_META_BASE = "{\"pack\":{\"pack_format\":3,\"description\":\"%s\"}}";
    private static List<IResourcePack> defaultResourcePacks;
    private List<CustomFile> files = new ArrayList<CustomFile>();
    private File dir;
    private File zip;
    private String mcmeta;
    private String assetsPath;
    private boolean hasPackPng = false;
    private Class<?> jarClass;

    /**
     * @param directory The directory to assemble the resource pack in. The name of the
     *                  zip created will be the same as this folder, and it will be
     *                  created on the same level as the folder. This folder will be
     *                  <strong>WIPED</strong> on every call of {@link #assemble()} .
     * @param packName  The name of the resource pack.
     */
    public ResourcePackAssembler(File directory, String packName) {
        this.dir = directory;
        this.zip = new File(dir.getAbsolutePath() + ".zip");
        this.mcmeta = String.format(MC_META_BASE, packName);
        this.assetsPath = "/assets/";
    }

    /**
     * Enables the use of a pack.png.
     * <p>
     * Will cause your mod's jar to be searched for a resource pack logo at
     * assets/[modid]/pack.png.
     *
     * @param jarClass A class in your jar file.
     * @return The {@link ResourcePackAssembler} instance.
     */
    public ResourcePackAssembler setHasPackPng(Class<?> jarClass) {
        this.jarClass = jarClass;
        hasPackPng = true;
        return this;
    }

    /**
     * Adds a custom file to the pack. This can be added into any folder in the
     * pack you desire. Useful for one-off files such as sounds.json.
     *
     * @param path The path inside the resource pack to this file.
     * @param file The file to add.
     */
    public void addFile(String path, File file) {
        files.add(new CustomFile(assetsPath + path, file));
    }

    /**
     * Adds the custom file at the base directory.
     *
     * @param file The file to add.
     * @see #addFile(String, File)
     */
    public void addCustomFile(File file) {
        addFile(null, file);
    }

    /**
     * Assembles the resource pack. This creates a zip file with the name of the
     * {@link File directory} that was passed into the constructor on the same
     * level as that folder.
     *
     * @return The {@link ResourcePackAssembler} instance.
     */
    public ResourcePackAssembler assemble() {
        String pathToDir = dir.getAbsolutePath();
        File metaFile = new File(pathToDir + "/pack.mcmeta");

        try {
            writeNewFile(metaFile, mcmeta);

            for (CustomFile custom : files) {
                File directory = new File(pathToDir + (custom.ext != null ? "/" + custom.ext : ""));
                directory.mkdirs();
                FileUtils.copyFile(custom.file, new File(directory.getAbsolutePath() + "/" + custom.file.getName()));
            }

            BaseFileUtils.safeDelete(new File(dir, "/assets"));
            BaseFileUtils.zipFolderContents(dir, zip);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    /**
     * Inserts the resource pack into the game. Enabling the resource pack will
     * not be required, it will load automatically.
     * <p>
     * A cache of the pack zip will be kept in "resourcepack/[pack name].zip"
     * where "resourcepack" is a folder at the same level as the directory passed
     * into the constructor.
     */
    public void inject() {
        if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            try {
                if (defaultResourcePacks == null) {
                    defaultResourcePacks = ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), "defaultResourcePacks", "field_110449_ao", "ap");
                }

                File dest = new File(dir.getParent() + "/resourcepacks/" + zip.getName());
                BaseFileUtils.safeDelete(dest);
                FileUtils.copyFile(zip, dest);
                BaseFileUtils.safeDelete(zip);
                defaultResourcePacks.add(new FileResourcePack(dest));
            } catch (Exception e) {
                Platform.attemptLogExceptionToCurrentMod(e);
            }
        }
    }

    private void writeNewFile(File file, String defaultText) throws IOException {
        BaseFileUtils.safeDelete(file);
        file.delete();
        file.getParentFile().mkdirs();
        file.createNewFile();

        FileWriter fw = new FileWriter(file);
        fw.write(defaultText);
        fw.flush();
        fw.close();
    }

    private class CustomFile {
        private String ext;
        private File file;

        private CustomFile(String ext, File file) {
            this.ext = ext;
            this.file = file;
        }
    }
}
