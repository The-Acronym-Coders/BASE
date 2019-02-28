package com.teamacronymcoders.base.json.factory;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class DefaultObjectFactory<T> implements IObjectFactory<T> {
    private static final Gson GSON = new Gson();

    private final Class<T> tClass;

    public DefaultObjectFactory(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public T parse(JsonElement jsonElement) {
        return GSON.fromJson(jsonElement, tClass);
    }
}
