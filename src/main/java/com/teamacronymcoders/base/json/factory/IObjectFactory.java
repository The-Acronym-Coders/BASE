package com.teamacronymcoders.base.json.factory;

import com.google.gson.JsonElement;

public interface IObjectFactory<T> {
    T parse(JsonElement jsonElement);
}
