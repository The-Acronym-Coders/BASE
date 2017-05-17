package com.teamacronymcoders.base.materialsystem.json;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.teamacronymcoders.base.materialsystem.json.element.Variant;
import com.teamacronymcoders.base.materialsystem.json.resources.IResource;
import com.teamacronymcoders.base.materialsystem.json.resources.ResourceType;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;

import java.util.Map;

public class BlockJson implements IResource {
    private Map<String, Variant> variants;

    public BlockJson(String modid, MaterialPart materialPart) {
        variants = Maps.newHashMap();
        variants.put("normal", new Variant(modid + ":" + materialPart.getPart().getUnlocalizedName()));
        variants.put("inventory", new Variant(modid + ":" + materialPart.getPart().getUnlocalizedName()));
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public ResourceType getType() {
        return ResourceType.BLOCKSTATE;
    }

    @Override
    public String getJson(Gson gson) {
        return gson.toJson(this);
    }

    public Map<String, Variant> getVariants() {
        return variants;
    }

    public void setVariants(Map<String, Variant> variants) {
        this.variants = variants;
    }
}
