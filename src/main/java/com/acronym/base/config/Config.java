package com.acronym.base.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Config {

    public static Configuration configuration;

    public static Configuration initConfig(File configFile) {
        if (configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
        return configuration;
    }

    public static void loadConfiguration() {
        ConfigMaterials.init(configuration);

        configuration.save();
    }
}
