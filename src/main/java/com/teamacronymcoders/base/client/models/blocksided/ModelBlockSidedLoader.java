package com.teamacronymcoders.base.client.models.blocksided;

import com.google.common.collect.ImmutableMap;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.blocks.properties.SideType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.teamacronymcoders.base.client.models.blocksided.BakedModelBlockSided.TYPES;

@SideOnly(Side.CLIENT)
public class ModelBlockSidedLoader implements ICustomModelLoader {
    private String modid;
    private static final String MODEL_PREFIX = "confSides_";
    private static final String RESOURCE_LOCATION = "models/block/smartmodel/" + MODEL_PREFIX;
    //TODO: Find the other clearing source
    public static HashMap<String, List<BakedQuad>> modelCache = new HashMap<>();

    public ModelBlockSidedLoader(IBaseMod mod) {
        this.modid = mod.getID();
        ModelLoaderRegistry.registerLoader(this);
    }

    @Override
    public void onResourceManagerReload(@Nonnull IResourceManager resourceManager) {
        modelCache.clear();
    }

    @Override
    public boolean accepts(ResourceLocation modelLocation) {
        return modelLocation.getResourceDomain().equals(modid) && modelLocation.getResourcePath().contains(RESOURCE_LOCATION);
    }

    @Override
    public IModel loadModel(ResourceLocation modelLocation) throws IOException {
        String resourcePath = modelLocation.getResourcePath();
        int pos = resourcePath.indexOf(MODEL_PREFIX);
        if (pos >= 0) {
            pos += MODEL_PREFIX.length();
            String sub = resourcePath.substring(pos);
            String name = sub;
            String type = null;
            ImmutableMap.Builder<String, ResourceLocation> builder = ImmutableMap.builder();
            for (Map.Entry<String, ITextureNamer> e : TYPES.entrySet()) {
                if (sub.startsWith(e.getKey())) {
                    type = e.getKey();
                    name = sub.substring(type.length());
                    for (EnumFacing f : EnumFacing.VALUES) {
                        for (SideType cfg : SideType.values()) {
                            String key = f.getName() + "_" + cfg.getName();
                            String tex = name + "_" + e.getValue().getTextureName(f, cfg);
                            builder.put(key, new ResourceLocation(modid, "blocks/" + tex));
                        }
                    }
                }
            }

            return new ModelBlockSided(modid, name, type, builder.build());
        }
        return ModelLoaderRegistry.getMissingModel();
    }
}
