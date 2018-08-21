package com.teamacronymcoders.base.json.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.lang.reflect.Type;
import java.util.function.Function;

public class EntityFactoryDeserializer implements JsonDeserializer<Function<World, ? extends Entity>> {
    @Override
    public Function<World, ? extends Entity> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        EntityEntry entityEntry = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(json.getAsString()));
        if (entityEntry != null) {
            return entityEntry::newInstance;
        }
        throw new JsonParseException("No Entity found for name: " + json.getAsString());
    }
}
