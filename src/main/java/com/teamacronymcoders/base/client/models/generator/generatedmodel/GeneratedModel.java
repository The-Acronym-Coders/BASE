package com.teamacronymcoders.base.client.models.generator.generatedmodel;

public class GeneratedModel implements IGeneratedModel {
    private String name;

    public GeneratedModel(String name, ModelType modelType, String json) {
        this.name = name;
        this.modelType = modelType;
        this.json = json;
    }

    private ModelType modelType;
    private String json;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ModelType getModelType() {
        return this.modelType;
    }

    @Override
    public String getJson() {
        return this.json;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModelType(ModelType modelType) {
        this.modelType = modelType;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
