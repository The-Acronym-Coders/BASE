package com.teamacronymcoders.base.json.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.Type;
import java.util.Optional;

public class EntityClassDeserializer implements JsonDeserializer<Class<? extends Entity>> {
    @Override
    public Class<? extends Entity> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return Optional.ofNullable(json)
                .map(JsonElement::getAsString)
                .map(ResourceLocation::new)
                .map(ForgeRegistries.ENTITIES::getValue)
                .map(EntityType::getEntityClass)
                .orElseThrow(() -> new JsonParseException("Found null value for Entity"));
    }
}
