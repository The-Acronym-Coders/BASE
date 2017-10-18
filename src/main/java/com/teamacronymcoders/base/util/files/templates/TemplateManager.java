package com.teamacronymcoders.base.util.files.templates;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Maps;
import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class TemplateManager {
    private static Cache<String, TemplateFile> templates = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES).build();

    public static TemplateFile getTemplateFile(String name) {
        return getTemplateFile(new ResourceLocation(Reference.MODID, name));
    }

    @Nonnull
    public static TemplateFile getTemplateFile(ResourceLocation resourceLocation) {
        TemplateFile file = null;
        try {
            file = templates.get(resourceLocation.toString(), () ->
                    new TemplateFile(resourceLocation, Base.instance.getLibProxy().getFileContents(resourceLocation)));
        } catch (ExecutionException e) {
            Base.instance.getLogger().getLogger().error(e);
        } finally {
            if (file == null) {
                file = new TemplateFile(new ResourceLocation("base:empty"), "");
            }
        }
        return file.copy();
    }
}
