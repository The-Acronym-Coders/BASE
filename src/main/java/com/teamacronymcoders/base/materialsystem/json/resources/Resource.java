package com.teamacronymcoders.base.materialsystem.json.resources;

public class Resource implements IResource {
    private String name;

    public Resource(String name, ResourceType resourceType, String json) {
        this.name = name;
        this.resourceType = resourceType;
        this.json = json;
    }

    private ResourceType resourceType;
    private String json;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ResourceType getResourceType() {
        return this.resourceType;
    }

    @Override
    public String getJson() {
        return this.json;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
