package com.teamacronymcoders.base.json.deserializer;

import com.google.gson.*;
import net.minecraft.nbt.NBTTagCompound;

import java.lang.reflect.Type;
import java.util.Map;

public class NBTDeserializer implements JsonDeserializer<NBTTagCompound> {
    @Override
    public NBTTagCompound deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject()) {
            return parseObject(json.getAsJsonObject());
        }
        throw new JsonParseException("NBT is required to be an object");
    }

    private NBTTagCompound parseObject(JsonObject json) {
        NBTTagCompound tagCompound = new NBTTagCompound();

        for (Map.Entry<String, JsonElement> property: json.entrySet()) {
            String name = property.getKey();
            JsonElement jsonElement = property.getValue();
            if (jsonElement.isJsonObject()) {
                tagCompound.setTag(name, parseObject(json));
            } else if (json.isJsonArray()) {
                handleJsonArray(tagCompound, name, jsonElement.getAsJsonArray());
            } else if (json.isJsonPrimitive()) {
                handleJsonPrimitive(tagCompound, name, jsonElement.getAsJsonPrimitive());
            }
        }
        return tagCompound;
    }

    private void handleJsonPrimitive(NBTTagCompound tagCompound, String propertyName, JsonPrimitive jsonPrimitive) {
        if (jsonPrimitive.isBoolean()) {
            tagCompound.setBoolean(propertyName, jsonPrimitive.getAsBoolean());
        } else if (jsonPrimitive.isString()) {
            tagCompound.setString(propertyName, jsonPrimitive.getAsString());
        } else if (jsonPrimitive.isNumber()) {
            Number number = jsonPrimitive.getAsNumber();
            if (number instanceof Long) {
                tagCompound.setLong(propertyName, number.longValue());
            } else if (number instanceof Integer) {
                tagCompound.setInteger(propertyName, number.intValue());
            } else if (number instanceof Short) {
                tagCompound.setShort(propertyName, number.shortValue());
            } else if (number instanceof Double) {
                tagCompound.setDouble(propertyName, number.doubleValue());
            } else if (number instanceof Byte) {
                tagCompound.setByte(propertyName, number.byteValue());
            } else if (number instanceof Float) {
                tagCompound.setFloat(propertyName, number.floatValue());
            }
        }
    }

    private void handleJsonArray(NBTTagCompound tagCompound, String propertyName, JsonArray jsonArray) {

    }
}
