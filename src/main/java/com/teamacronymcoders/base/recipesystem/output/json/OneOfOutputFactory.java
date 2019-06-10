package com.teamacronymcoders.base.recipesystem.output.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.teamacronymcoders.base.json.factory.IObjectFactory;
import com.teamacronymcoders.base.recipesystem.loader.JsonRecipeLoader;
import com.teamacronymcoders.base.recipesystem.output.OneOfOutput;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.JsonContext;

public class OneOfOutputFactory implements IObjectFactory<OneOfOutput> {
    @Override
    public OneOfOutput parse(JsonContext jsonContext, JsonElement jsonElement) {
        JsonArray options = JsonUtils.getJsonArray(jsonElement.getAsJsonObject(), "options");
        return new OneOfOutput(JsonRecipeLoader.processOutputs(options, jsonContext));
    }
}
