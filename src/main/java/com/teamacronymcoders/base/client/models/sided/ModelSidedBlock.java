package com.teamacronymcoders.base.client.models.sided;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.teamacronymcoders.base.blocks.properties.SideType;
import com.teamacronymcoders.base.client.models.ModelUtils;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.function.Function;

public class ModelSidedBlock implements IModel {
    private final String name;
    private final String type;
    private final ImmutableMap<String, ResourceLocation> textures;

    public ModelSidedBlock(String name, String type, ImmutableMap<String, ResourceLocation> textures) {
        this.name = name;
        this.type = type;
        this.textures = textures;
    }

    @Override
    @Nonnull
    public Collection<ResourceLocation> getDependencies() {
        return ImmutableList.of();
    }

    @Override
    @Nonnull
    public Collection<ResourceLocation> getTextures() {
        return textures.values();
    }

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        TextureAtlasSprite[][] tex = new TextureAtlasSprite[6][3];
        for (EnumFacing f : EnumFacing.VALUES) {
            for (SideType cfg : SideType.values()) {
                ResourceLocation resourceLocation = textures.get(f.getName() + "_" + cfg.getName());
                if (resourceLocation != null) {
                    tex[f.ordinal()][cfg.ordinal()] = ModelUtils.getBlockSprite(resourceLocation);
                }
            }
        }

        return new BakedModelSidedBlock(name, tex);
    }

    @Override
    @Nonnull
    public IModel retexture(ImmutableMap<String, String> textures) {
        String newName = this.name;
        ImmutableMap.Builder<String, ResourceLocation> builder = ImmutableMap.builder();
        for (EnumFacing f : EnumFacing.VALUES) {
            for (SideType cfg : SideType.values()) {
                String key = f.getName() + "_" + cfg.getName();
                ResourceLocation resourceLocation = this.textures.get(key);
                if (textures.containsKey(key)) {
                    resourceLocation = new ResourceLocation(textures.get(key));
                } else if (textures.containsKey(f.getName())) {
                    ITextureNamer namer = SidedTypeRegistry.getTextureNamer(type);
                    resourceLocation = new ResourceLocation(textures.get(f.getName()));
                    if (namer != null) {
                        String c = namer.nameFromCfg(f, cfg);
                        if (c != null) {
                            resourceLocation = new ResourceLocation(textures.get(f.getName()) + "_" + c);
                        }
                    }
                } else if (textures.containsKey("name")) {
                    ITextureNamer namer = SidedTypeRegistry.getTextureNamer(type);
                    newName = textures.get("name");
                    if (namer != null) {
                        resourceLocation = new ResourceLocation(newName + "_" + namer.getTextureName(f, cfg));
                    }
                }
                builder.put(key, resourceLocation);
            }
        }
        return new ModelSidedBlock(newName, type, builder.build());
    }

}
