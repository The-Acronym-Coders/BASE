package com.teamacronymcoders.base.registrysystem;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class Registry<T extends ForgeRegistryEntry<T>> {
    public Map<ResourceLocation, T> items;

    public Collection<T> getAll() {
        return items.values();
    }

    public void addItem(T item) {
        items.put(Objects.requireNonNull(item.getRegistryName()), item);
    }
}
