package com.teamacronymcoders.base.savesystem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teamacronymcoders.base.util.ClassLoading;
import com.teamacronymcoders.base.util.Platform;
import com.teamacronymcoders.base.util.files.BaseFileUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class SaveLoader {
    private static File configFolder;
    private static File saveFolder;
    private static Gson gson;

    public static <T> T getSavedObject(String name, Class<T> clazz) {
        createSaveFolder();
        File savedFile = new File(saveFolder, name + ".json");
        T savedObject = null;
        if (savedFile.exists()) {
            try {
                FileUtils.copyFile(savedFile, new File(saveFolder, savedFile.getName().replace(".json", "-original.json")));
            } catch (IOException e) {
                Platform.attemptLogErrorToCurrentMod("Tried to create original version of file, failed");
            }
            String json = BaseFileUtils.readFileToString(savedFile);
            if (json != null) {
                savedObject = gson.fromJson(json, clazz);
            }
        }
        if (savedObject == null) {
            savedObject = ClassLoading.createObjectInstance(clazz);
        }
        return savedObject;
    }

    public static <T> void saveObject(String name, T object) {
        createSaveFolder();
        File fileToSaveTo = new File(saveFolder, name + ".json");
        String json = gson.toJson(object);
        BaseFileUtils.writeStringToFile(json, fileToSaveTo);
    }

    public static void createSaveFolder() {
        if (saveFolder == null) {
            saveFolder = new File(configFolder, "saved");
            gson = new GsonBuilder().setPrettyPrinting().create();

            BaseFileUtils.createFolder(saveFolder);
        }
    }

    public static void setConfigFolder(File folder) {
        configFolder = folder;
    }
}
