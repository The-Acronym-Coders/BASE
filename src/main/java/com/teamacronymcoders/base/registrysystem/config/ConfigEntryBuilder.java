package com.teamacronymcoders.base.registrysystem.config;

import net.minecraftforge.common.config.Property.Type;

public class ConfigEntryBuilder {
    private String category = "General";
    private String propertyName;
    private boolean isArray = false;
    private Type type = Type.STRING;
    private String value;
    private String comment;
    private boolean guiChangeable;


    private ConfigEntryBuilder() {

    }

    public static ConfigEntryBuilder getBuilder(String propertyName, String value) {
        ConfigEntryBuilder configEntryBuilder = new ConfigEntryBuilder();
        configEntryBuilder.propertyName = propertyName;
        configEntryBuilder.value = value;
        return configEntryBuilder;
    }

    public ConfigEntry build() {
        return new ConfigEntry(category, propertyName, type, value, comment, guiChangeable, isArray);
    }

    public ConfigEntryBuilder copy(ConfigEntryBuilder configEntryBuilder) {
        ConfigEntryBuilder newInstance = new ConfigEntryBuilder();
        newInstance.category = configEntryBuilder.category;
        newInstance.propertyName = configEntryBuilder.propertyName;
        newInstance.isArray = configEntryBuilder.isArray;
        newInstance.type = configEntryBuilder.type;
        newInstance.comment = configEntryBuilder.comment;
        newInstance.guiChangeable = configEntryBuilder.guiChangeable;
        return newInstance;
    }

    public String getCategory() {
        return category;
    }

    public ConfigEntryBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public ConfigEntryBuilder setPropertyName(String propertyName) {
        this.propertyName = propertyName;
        return this;
    }

    public boolean isArray() {
        return isArray;
    }

    public ConfigEntryBuilder setArray(boolean array) {
        isArray = array;
        return this;
    }

    public Type getType() {
        return type;
    }

    public ConfigEntryBuilder setType(Type type) {
        this.type = type;
        return this;
    }

    public String getValue() {
        return value;
    }

    public ConfigEntryBuilder setValue(String value) {
        this.value = value;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public ConfigEntryBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public boolean isGuiChangeable() {
        return guiChangeable;
    }

    public ConfigEntryBuilder setGuiChangeable(boolean guiChangeable) {
        this.guiChangeable = guiChangeable;
        return this;
    }
}
