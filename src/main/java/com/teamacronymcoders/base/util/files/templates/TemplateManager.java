package com.teamacronymcoders.base.util.files.templates;

import com.google.common.collect.Maps;
import com.teamacronymcoders.base.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Map;

@SideOnly(Side.CLIENT)
public class TemplateManager {
    private static Map<String, TemplateFile> templates = Maps.newHashMap();

    public static TemplateFile getTemplateFile(String name) {
        return getTemplateFile(new ResourceLocation(Reference.MODID, name));
    }

    public static TemplateFile getTemplateFile(ResourceLocation resourceLocation) {
        templates.computeIfAbsent(resourceLocation.toString(), key -> new TemplateFile(resourceLocation));
        TemplateFile file = templates.getOrDefault(resourceLocation.toString(), new TemplateFile(resourceLocation));
        return file.copy();
    }
}
