package com.teamacronymcoders.base.json.deserializer;

import com.google.gson.*;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.math.BlockPos;

import java.lang.reflect.Type;

public class BlockPosDeserializer implements JsonDeserializer<BlockPos> {
    @Override
    public BlockPos deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json != null && json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();
            int x = JsonUtils.getInt(jsonObject, "x", 0);
            int y = JsonUtils.getInt(jsonObject, "y", 0);
            int z = JsonUtils.getInt(jsonObject, "z", 0);
            return new BlockPos(x, y, z);
        }

        return BlockPos.ORIGIN;
    }
}
