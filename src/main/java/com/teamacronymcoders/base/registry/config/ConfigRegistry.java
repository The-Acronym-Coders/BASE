package com.teamacronymcoders.base.registry.config;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.registry.Registry;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigRegistry extends Registry<ConfigEntry> {
    private List<IConfigListener> listeners = new ArrayList<>();
    private File tacFolder;
    private File modFolder;
    private boolean useModFolder;
    private Map<String, Configuration> configurationFiles;

    public ConfigRegistry(IBaseMod mod, File suggestConfigFile, boolean useModFolder) {
        super("CONFIG", mod);
        this.useModFolder = useModFolder;
        configurationFiles = new HashMap<>();
        if (suggestConfigFile.isDirectory()) {
            String configFileName = this.mod.getID();
            this.tacFolder = new File(suggestConfigFile, "ACRONYM");
            boolean folderExists = tacFolder.exists();
            if (!folderExists) {
                folderExists = tacFolder.mkdir();
            }

            if(folderExists && useModFolder) {
                this.modFolder = new File(this.tacFolder, this.mod.getID().toUpperCase());
                if(!this.modFolder.exists()) {
                    folderExists = this.modFolder.mkdir();
                }
                configFileName = "General";
            }

            if(folderExists) {
                addNewConfigFile(configFileName);
            } else {
                this.mod.getLogger().fatal("FAILED TO CREATE REQUIRED FOLDERS FOR CONFIG");
            }

        } else {
            configurationFiles.put(mod.getID(), new Configuration(suggestConfigFile));
        }
    }

    public void alertTheListeners(String name, ConfigEntry configEntry) {
        for (IConfigListener configListener : listeners) {
            configListener.onConfigChange(name, configEntry);
        }
    }

    public boolean addNewConfigFile(String fileName) {
        File parentFile = useModFolder ? modFolder : tacFolder;
        return addNewConfigFile(parentFile, fileName);
    }

    public boolean addNewConfigFile(File parent, String fileName) {
        File newConfig = new File(parent, fileName + ".cfg");
        boolean exists = newConfig.exists();
        if (!exists) {
            try {
                exists = newConfig.createNewFile();
            } catch (IOException exception) {
                mod.getLogger().getLogger().error("Failed to Create Config File: " + fileName, exception);
            }
        }

        if (exists) {
            configurationFiles.put(fileName, new Configuration(newConfig));
        } else {
            mod.getLogger().fatal(fileName + " configuration file was not created.");
        }
        return exists;
    }

    public void addCategoryComment(String name, String comment) {
        addCategoryComment(name, comment, this.mod.getID());
    }

    public void addCategoryComment(String name, String comment, String configName) {
        configurationFiles.get(configName).addCustomCategoryComment(name, comment);
    }

    public void addEntry(ConfigEntry entry) {
        addEntry(entry.getPropertyName(), entry);
    }

    public void addEntry(String name, ConfigEntry entry) {
        String configName = this.useModFolder ? "General" : this.mod.getID();
        addEntry(name, entry, configName);
    }

    public void addEntry(String name, ConfigEntry entry, String configName) {
        this.entries.put(name, entry);
        entry.toProperty(configurationFiles.get(configName));
        configurationFiles.get(configName).save();
    }

    public void updateEntry(String name, String value) {
        ConfigEntry configEntry = this.entries.get(name);
        if (configEntry != null) {
            configEntry.setValue(value);
            this.alertTheListeners(name, configEntry);
        } else {
            mod.getLogger().error("Config Entry for " + name + " not found");
        }
    }

    public ConfigEntry getEntry(String name) {
        return this.entries.get(name);
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        boolean returnValue = defaultValue;
        ConfigEntry configEntry = getEntry(name);
        if (configEntry != null) {
            returnValue = configEntry.getBoolean(defaultValue);
        }
        return returnValue;
    }

    public int getInt(String name, int defaultValue) {
        int returnValue = defaultValue;
        ConfigEntry configEntry = getEntry(name);
        if (configEntry != null) {
            returnValue = configEntry.getInt(defaultValue);
        }
        return returnValue;
    }

    public double getDouble(String name, double defaultValue) {
        double returnValue = defaultValue;
        ConfigEntry configEntry = getEntry(name);
        if (configEntry != null) {
            returnValue = configEntry.getDouble(defaultValue);
        }
        return returnValue;
    }

    public String getString(String name, String defaultValue) {
        String returnValue = defaultValue;
        ConfigEntry configEntry = getEntry(name);
        if (configEntry != null) {
            returnValue = configEntry.getString();
        }
        return returnValue;
    }

    @Override
    public boolean requiresPreInitRegister() {
        return false;
    }

    @Override
    public void register(String name, ConfigEntry entry) {
        addEntry(name, entry);
    }

    public void addListener(IConfigListener listener) {
        this.listeners.add(listener);
    }

    public File getConfigFolder() {
        return this.useModFolder ? this.modFolder : this.tacFolder;
    }
}
