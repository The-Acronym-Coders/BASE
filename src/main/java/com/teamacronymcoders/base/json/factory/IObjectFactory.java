package com.teamacronymcoders.base.json.factory;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.minecraftforge.common.crafting.JsonContext;

public interface IObjectFactory<T> {
    T parse(JsonContext jsonContext, JsonElement jsonElement);
}
