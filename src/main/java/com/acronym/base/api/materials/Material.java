package com.acronym.base.api.materials;

import java.util.List;

/**
 * Created by Jared on 6/27/2016.
 */
public class Material {

    private String name;
    private int color;
    private List<Resource> resources;

    public Material(String name, int color, List<Resource> resources) {
        this.name = name;
        this.color = color;
        this.resources = resources;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
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

        if (getColor() != material.getColor()) return false;
        if (getName() != null ? !getName().equals(material.getName()) : material.getName() != null) return false;
        return getResources() != null ? getResources().equals(material.getResources()) : material.getResources() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getColor();
        result = 31 * result + (getResources() != null ? getResources().hashCode() : 0);
        return result;
    }
}
