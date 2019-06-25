package com.teamacronymcoders.base.json.deserializer;

import com.google.gson.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.Type;
import java.util.Map;

public class BlockStateDeserializer implements JsonDeserializer<BlockState> {
    @Override
    public BlockState deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json != null && json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();
            String blockName = JSONUtils.getString(jsonObject, "block");
            Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(blockName));
            if (block != null) {
                if (jsonObject.has("properties")) {
                    StateContainer blockStateContainer = block.getStateContainer();
                    BlockState blockState = block.getDefaultState();
                    JsonObject properties = jsonObject.getAsJsonObject("properties");
                    for (Map.Entry<String, JsonElement> entry : properties.entrySet()) {
                        IProperty<?> property = blockStateContainer.getProperty(entry.getKey());
                        if (property != null) {
                            blockState = setValueHelper(blockState, property, entry.getValue().getAsString());
                        } else {
                            throw new JsonParseException("Failed to find property: " + entry.getKey());
                        }
                    }
                    return blockState;
                } else {
                    return block.getDefaultState();
                }
            }
        }

        if (json == null) {
            throw new JsonParseException("Found null value for BlockState Input");
        } else {
            throw new JsonParseException("Failed to deserialize BlockState for: " + json.toString());
        }
    }

    private static <T extends Comparable<T>> BlockState setValueHelper(final BlockState blockState, final IProperty<T> property, final String stringValue) throws JsonParseException {
        return property.parseValue(stringValue)
                .map(propertyValue -> blockState.with(property, propertyValue))
                .orElseThrow(() -> new JsonParseException("Failed to find value " + stringValue + " for property " + property.getName()));
    }
}
