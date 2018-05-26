package com.teamacronymcoders.base.client.models.sided;

import com.google.common.collect.ImmutableMap;
import com.teamacronymcoders.base.blocks.properties.SideType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelLoaderSidedBlock implements ICustomModelLoader {
    private static ModelLoaderSidedBlock instance;
    private static final String RESOURCE_LOCATION = "models/block/side_type/";

    private Map<String, List<BakedQuad>> modelCache = new HashMap<>();
    private List<String> domains = new ArrayList<>();

    public static ModelLoaderSidedBlock getInstance() {
        if (instance == null) {
            instance = new ModelLoaderSidedBlock();
            ModelLoaderRegistry.registerLoader(instance);
        }
        return instance;
    }

    @Override
    public void onResourceManagerReload(@Nonnull IResourceManager resourceManager) {
        modelCache.clear();
    }

    @Override
    public boolean accepts(@Nonnull ResourceLocation modelLocation) {
        return domains.contains(modelLocation.getResourceDomain()) && modelLocation.getResourcePath().contains(RESOURCE_LOCATION);
    }

    @Override
    @Nonnull
    public IModel loadModel(@Nonnull ResourceLocation modelLocation) {
        String resourcePath = modelLocation.getResourcePath();
        if (resourcePath.startsWith(RESOURCE_LOCATION)) {
            String sub = resourcePath.replaceFirst(RESOURCE_LOCATION, "");
            int firstSlash = sub.indexOf("/");
            String name = sub.substring(firstSlash + 1);
            String type = sub.substring(0, firstSlash);
            ImmutableMap.Builder<String, ResourceLocation> builder = ImmutableMap.builder();
            ITextureNamer textureNamer = SidedTypeRegistry.getTextureNamer(type);

            for (EnumFacing f : EnumFacing.VALUES) {
                for (SideType cfg : SideType.values()) {
                    String key = f.getName() + "_" + cfg.getName();
                    String tex = name + "_" + textureNamer.getTextureName(f, cfg);
                    builder.put(key, new ResourceLocation(modelLocation.getResourceDomain(), "blocks/" + tex));
                }
            }


            return new ModelSidedBlock(name, type, builder.build());
        }
        return ModelLoaderRegistry.getMissingModel();
    }

    public void addDomain(String modid) {
        domains.add(modid);
    }

    public Map<String, List<BakedQuad>> getModelCache() {
        return modelCache;
    }
}
