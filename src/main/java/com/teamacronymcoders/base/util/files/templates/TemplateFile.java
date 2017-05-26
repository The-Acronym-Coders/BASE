package com.teamacronymcoders.base.util.files.templates;

import com.teamacronymcoders.base.Reference;
import com.teamacronymcoders.base.client.ClientHelper;
import com.teamacronymcoders.base.util.Platform;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class TemplateFile {
    private ResourceLocation name;
    private String fileContents;

    TemplateFile(ResourceLocation location) {
        location = new ResourceLocation(location.getResourceDomain(), "templates/" + location.getResourcePath() + ".json");
        IResource resource = ClientHelper.getResource(location);
        if (resource != null) {
            InputStream inputStream = resource.getInputStream();
            try {
                this.fileContents = IOUtils.toString(inputStream);
            } catch (IOException e) {
                Platform.attemptLogExceptionToCurrentMod(e);
            }
        }
    }

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
