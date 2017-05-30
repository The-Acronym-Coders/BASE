package com.teamacronymcoders.base.client.models.blocksided;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.teamacronymcoders.base.blocks.properties.SideType;
import com.teamacronymcoders.base.client.ClientHelper;
import com.teamacronymcoders.base.client.models.ModelUtils;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.IRetexturableModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collection;

import static com.teamacronymcoders.base.client.models.blocksided.BakedModelBlockSided.TYPES;

@SideOnly(Side.CLIENT)
public class ModelBlockSided implements IRetexturableModel {
    final String name;
    final String modid;
    final String type;
    ImmutableMap<String, ResourceLocation> textures;

    public ModelBlockSided(String modid, String name, String type, ImmutableMap<String, ResourceLocation> textures) {
        this.name = name;
        this.modid = modid;
        this.type = type;
        this.textures = textures;
    }

    @Override
    public Collection<ResourceLocation> getDependencies() {
        return ImmutableList.of();
    }

    @Override
    public Collection<ResourceLocation> getTextures() {
        return textures.values();
    }

    @Override
    public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        TextureAtlasSprite[][] tex = new TextureAtlasSprite[6][3];
        for (EnumFacing f : EnumFacing.VALUES) {
            for (SideType cfg : SideType.values()) {
                ResourceLocation rl = textures.get(f.getName() + "_" + cfg.getName());
                if (rl != null) {
                    tex[f.ordinal()][cfg.ordinal()] = ModelUtils.getBlockSprite(rl);
                }
            }
        }
        return new BakedModelBlockSided(modid, name, tex);
    }

    @Override
    public IModelState getDefaultState() {
        return null;
    }

    @Override
    public IModel retexture(ImmutableMap<String, String> textures) {
        String newName = this.name;
        ImmutableMap.Builder<String, ResourceLocation> builder = ImmutableMap.builder();
        for (EnumFacing f : EnumFacing.VALUES)
            for (SideType cfg : SideType.values()) {
                String key = f.getName() + "_" + cfg.getName();
                ResourceLocation rl = this.textures.get(key);
                if (textures.containsKey(key)) {
                    rl = new ResourceLocation(textures.get(key));
                } else if (textures.containsKey(f.getName())) {
                    ITextureNamer namer = TYPES.get(type);
                    rl = new ResourceLocation(textures.get(f.getName()));
                    if (namer != null) {
                        String c = namer.nameFromCfg(f, cfg);
                        if (c != null) {
                            rl = new ResourceLocation(textures.get(f.getName()) + "_" + c);
                        }
                    }
                } else if (textures.containsKey("name")) {
                    ITextureNamer namer = TYPES.get(type);
                    newName = textures.get("name");
                    if (namer != null) {
                        rl = new ResourceLocation(newName + "_" + namer.getTextureName(f, cfg));
                    }
                }
                builder.put(key, rl);
            }
        return new ModelBlockSided(modid, newName, type, builder.build());
    }
}

