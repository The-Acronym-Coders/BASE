package com.teamacronymcoders.base.client.models.wrapped;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.animation.ModelBlockAnimation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class WrappedModelLoader implements ICustomModelLoader {
    private static WrappedModelLoader instance;

    private Map<ResourceLocation, WrappedBlockEntry> models;
    private List<String> modids;

    public static WrappedModelLoader getInstance() {
        if (instance == null) {
            instance = new WrappedModelLoader();
        }
        return instance;
    }

    public WrappedModelLoader() {
        models = Maps.newHashMap();
        modids = Lists.newArrayList();
        ModelLoaderRegistry.registerLoader(this);
    }

    @Override
    public boolean accepts(ResourceLocation modelLocation) {
        return modids.contains(modelLocation.getResourceDomain()) && models.containsKey(ensureNotModel(modelLocation));
    }

    @Override
    public IModel loadModel(ResourceLocation modelLocation) throws Exception {
        return new WrappedModel(models.get(ensureNotModel(modelLocation)));
    }

    private ResourceLocation ensureNotModel(ResourceLocation resourceLocation) {
        return new ResourceLocation(resourceLocation.getResourceDomain(), resourceLocation.getResourcePath());
    }

    @Override
    public void onResourceManagerReload(@Nonnull IResourceManager resourceManager) {

    }

    public static void addModel(ResourceLocation resourceLocation, WrappedBlockEntry wrappedBlockEntry) {
        if (!getInstance().modids.contains(resourceLocation.getResourceDomain())) {
            getInstance().modids.add(resourceLocation.getResourceDomain());
        }
        getInstance().models.put(resourceLocation, wrappedBlockEntry);
    }
}
