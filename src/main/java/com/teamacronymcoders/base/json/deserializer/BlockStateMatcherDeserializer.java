package com.teamacronymcoders.base.json.deserializer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.*;
import com.teamacronymcoders.base.blocks.BlockStateMatcher;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class BlockStateMatcherDeserializer implements JsonDeserializer<BlockStateMatcher> {
    @Override
    public BlockStateMatcher deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json != null && json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();
            String blockName = JsonUtils.getString(jsonObject, "block");
            Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(blockName));
            if (block != null) {
                if (jsonObject.has("properties")) {
                    BlockStateContainer blockStateContainer = block.getBlockState();
                    JsonObject properties = jsonObject.getAsJsonObject("properties");
                    Map<IProperty<?>, List<?>> propertyMatching = Maps.newHashMap();
                    for (Map.Entry<String, JsonElement> entry : properties.entrySet()) {
                        IProperty<?> property = blockStateContainer.getProperty(entry.getKey());
                        if (property != null) {
                            List acceptableValues = Lists.newArrayList();
                            List<String> acceptableStringValues = Lists.newArrayList();
                            JsonElement jsonValues = entry.getValue();
                            if (jsonValues.isJsonArray()) {
                                jsonValues.getAsJsonArray().iterator()
                                        .forEachRemaining(value -> acceptableStringValues.add(value.getAsString()));
                            } else {
                                acceptableStringValues.add(jsonValues.getAsString());
                            }
                            for (String value: acceptableStringValues) {
                                //noinspection unchecked
                                property.parseValue(value).toJavaUtil().ifPresent(acceptableValues::add);
                            }
                            propertyMatching.put(property, acceptableValues);
                        } else {
                            throw new JsonParseException("Failed to find property: " + entry.getKey());
                        }
                    }
                    return new BlockStateMatcher(block, propertyMatching);
                } else {
                    return new BlockStateMatcher(block);
                }
            }
        }

        if (json == null) {
            throw new JsonParseException("Found null value for BlockState Input");
        } else {
            throw new JsonParseException("Failed to deserialize BlockState for: " + json.toString());
        }
    }
}
