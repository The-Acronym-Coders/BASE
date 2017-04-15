package com.teamacronymcoders.base.registrysystem;

import java.util.Map;

public interface IRegistryHolder {
    Map<String, Registry> getAllRegistries();

    void addRegistry(String name, Registry registry);

    <R extends Registry> R getRegistry(Class<R> clazz, String name);
}
