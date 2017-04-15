package com.teamacronymcoders.base.registrysystem.config;

@FunctionalInterface
public interface IConfigListener {
    void onConfigChange(String name, ConfigEntry entry);
}
