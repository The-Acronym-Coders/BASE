package com.teamacronymcoders.base.util;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Optional;

public class RegistryUtils {
    public static <T extends IForgeRegistryEntry<T>> T getEntry(IForgeRegistry<T> registry, ResourceLocation resource) {
        Optional<T> value = Optional.ofNullable(registry.getValue(resource));
        return value.orElseThrow(() -> new IllegalArgumentException("Cannot get Entry for name: " + resource));
    }
}
