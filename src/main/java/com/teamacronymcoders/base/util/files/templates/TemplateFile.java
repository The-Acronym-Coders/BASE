package com.teamacronymcoders.base.util.files.templates;

import net.minecraft.util.ResourceLocation;

import java.util.Locale;
import java.util.Map;

public class TemplateFile {
    private ResourceLocation name;
    private String fileContents;

    TemplateFile(ResourceLocation name, String fileContents) {
        this.name = name;
        this.fileContents = fileContents;
    }

    public TemplateFile copy() {
        return new TemplateFile(this.name, this.fileContents);
    }

    public void replaceContents(Map<String, String> replacements) {
        replacements.forEach((name, value) -> this.fileContents = this.fileContents.replace("##" + name.toUpperCase(Locale.US) + "##", value));
    }

    public String getFileContents() {
        return fileContents;
    }
}
