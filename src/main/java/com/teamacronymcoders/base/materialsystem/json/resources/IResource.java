package com.teamacronymcoders.base.materialsystem.json.resources;

import com.google.gson.Gson;

public interface IResource {
    String getName();

    ResourceType getType();

    String getJson(Gson gson);
}
