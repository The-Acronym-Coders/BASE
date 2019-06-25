package com.teamacronymcoders.base.json.deserializer;

import com.google.gson.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.LongArrayNBT;

import java.lang.reflect.Type;
import java.util.Map;

public class NBTDeserializer implements JsonDeserializer<CompoundNBT> {
    @Override
    public CompoundNBT deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject()) {
            return parseObject(json.getAsJsonObject());
        }
        throw new JsonParseException("NBT is required to be an object");
    }

    private CompoundNBT parseObject(JsonObject json) {
        CompoundNBT tagCompound = new CompoundNBT();

        for (Map.Entry<String, JsonElement> property : json.entrySet()) {
            String name = property.getKey();
            JsonElement jsonElement = property.getValue();
            if (jsonElement.isJsonObject()) {
                tagCompound.put(name, parseObject(json));
            } else if (json.isJsonArray()) {
                handleJsonArray(tagCompound, name, jsonElement.getAsJsonArray());
            } else if (json.isJsonPrimitive()) {
                handleJsonPrimitive(tagCompound, name, jsonElement.getAsJsonPrimitive());
            }
        }
        return tagCompound;
    }

    private void handleJsonPrimitive(CompoundNBT tagCompound, String propertyName, JsonPrimitive jsonPrimitive) {
        if (jsonPrimitive.isBoolean()) {
            tagCompound.putBoolean(propertyName, jsonPrimitive.getAsBoolean());
        } else if (jsonPrimitive.isString()) {
            tagCompound.putString(propertyName, jsonPrimitive.getAsString());
        } else if (jsonPrimitive.isNumber()) {
            Number number = jsonPrimitive.getAsNumber();
            if (number instanceof Long) {
                tagCompound.putLong(propertyName, number.longValue());
            } else if (number instanceof Integer) {
                tagCompound.putInt(propertyName, number.intValue());
            } else if (number instanceof Short) {
                tagCompound.putShort(propertyName, number.shortValue());
            } else if (number instanceof Double) {
                tagCompound.putDouble(propertyName, number.doubleValue());
            } else if (number instanceof Byte) {
                tagCompound.putByte(propertyName, number.byteValue());
            } else if (number instanceof Float) {
                tagCompound.putFloat(propertyName, number.floatValue());
            }
        }
    }

    private void handleJsonArray(CompoundNBT tagCompound, String propertyName, JsonArray jsonArray) {
        boolean allLongs = true;
        boolean allInts = true;
        boolean allBytes = true;

        for (JsonElement element : jsonArray) {
            if (!element.isJsonPrimitive()) {
                throw new JsonParseException("Only Longs, Ints, and Bytes are supported in NBT Arrays");
            } else {
                JsonPrimitive primitive = element.getAsJsonPrimitive();
                if (primitive.isNumber()) {
                    Number number = primitive.getAsNumber();
                    if (number instanceof Byte) {
                        allLongs = false;
                        allInts = false;
                    } else if (number instanceof Integer) {
                        allLongs = false;
                        allBytes = false;
                    } else if (number instanceof Long) {
                        allInts = false;
                        allBytes = false;
                    } else {
                        throw new JsonParseException("Only Longs, Ints, and Bytes are supported in NBT Arrays");
                    }
                } else {
                    throw new JsonParseException("Only Longs, Ints, and Bytes are supported in NBT Arrays");
                }
            }
        }

        if (allLongs) {
            handleJsonLongArray(tagCompound, propertyName, jsonArray);
        } else if (allInts) {
            handleJsonIntArray(tagCompound, propertyName, jsonArray);
        } else if (allBytes) {
            handleJsonByteArray(tagCompound, propertyName, jsonArray);
        } else {
            throw new JsonParseException("NBT Arrays must all be the Same Type");
        }
    }

    private void handleJsonByteArray(CompoundNBT tagCompound, String propertyName, JsonArray jsonArray) {
        byte[] byteArray = new byte[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            byteArray[i] = jsonArray.get(i).getAsJsonPrimitive().getAsByte();
        }
        tagCompound.putByteArray(propertyName, byteArray);
    }

    private void handleJsonIntArray(CompoundNBT tagCompound, String propertyName, JsonArray jsonArray) {
        int[] intArray = new int[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            intArray[i] = jsonArray.get(i).getAsJsonPrimitive().getAsInt();
        }
        tagCompound.putIntArray(propertyName, intArray);
    }

    private void handleJsonLongArray(CompoundNBT tagCompound, String propertyName, JsonArray jsonArray) {
        long[] longArray = new long[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            longArray[i] = jsonArray.get(i).getAsJsonPrimitive().getAsLong();
        }
        tagCompound.put(propertyName, new LongArrayNBT(longArray));
    }
}
