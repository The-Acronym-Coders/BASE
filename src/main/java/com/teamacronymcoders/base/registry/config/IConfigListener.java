package com.teamacronymcoders.base.registry.config;

@FunctionalInterface
public interface IConfigListener {
    void onConfigChange(String name, ConfigEntry entry);
}
