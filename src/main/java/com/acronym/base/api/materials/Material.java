package com.acronym.base.api.materials;

import java.util.List;

/**
 * Created by Jared on 6/27/2016.
 */
public class Material {

    private String name;
    private int colour;
    private List<Resource> resources;

    public Material(String name, int colour, List<Resource> resources) {
        this.name = name;
        this.colour = colour;
        this.resources = resources;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Material)) return false;

        Material material = (Material) o;

        if (getColour() != material.getColour()) return false;
        if (getName() != null ? !getName().equals(material.getName()) : material.getName() != null) return false;
        return getResources() != null ? getResources().equals(material.getResources()) : material.getResources() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getColour();
        result = 31 * result + (getResources() != null ? getResources().hashCode() : 0);
        return result;
    }
}
