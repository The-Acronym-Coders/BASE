package com.teamacronymcoders.base.client.models.wrapped;

import com.google.common.collect.Maps;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class WrappedModelLoader implements ICustomModelLoader {
    private static WrappedModelLoader instance;

    private Map<ResourceLocation, WrappedBlockEntry> models;

    public static WrappedModelLoader getInstance() {
        if (instance == null) {
            instance = new WrappedModelLoader();
        }
        return instance;
    }

    public WrappedModelLoader() {
        models = Maps.newHashMap();
        ModelLoaderRegistry.registerLoader(this);
    }

    @Override
    public boolean accepts(ResourceLocation modelLocation) {
        return models.containsKey(new ResourceLocation(modelLocation.getResourceDomain(), modelLocation.getResourcePath()));
    }

    @Override
    public IModel loadModel(ResourceLocation modelLocation) throws Exception {
        return new WrappedModel(models.get(new ResourceLocation(modelLocation.getResourceDomain(), modelLocation.getResourcePath())));
    }

    @Override
    public void onResourceManagerReload(@Nonnull IResourceManager resourceManager) {

    }

    public static void addModel(ResourceLocation resourceLocation, WrappedBlockEntry wrappedBlockEntry) {
        getInstance().models.put(resourceLocation, wrappedBlockEntry);
    }
}
