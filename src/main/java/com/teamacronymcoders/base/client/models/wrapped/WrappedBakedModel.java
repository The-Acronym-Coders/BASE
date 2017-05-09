package com.teamacronymcoders.base.client.models.wrapped;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.client.models.ModelUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.util.vector.Vector3f;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class WrappedBakedModel implements IBakedModel {
    private WrappedBlockEntry blockEntry;
    private List<BakedQuad> bakedQuads = null;

    public WrappedBakedModel(WrappedBlockEntry wrappedBlockEntry) {
        this.blockEntry = wrappedBlockEntry;
    }

    @Override
    @Nonnull
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
        if (bakedQuads == null) {
            bakedQuads = Lists.newArrayList();
            bakedQuads.addAll(ModelUtils.bakeQuads(this.blockEntry.getBaseResource(), null));
            for (Map.Entry<ResourceLocation, Boolean> location : this.blockEntry.getLayers().entrySet()) {
                Color color = location.getValue() ? this.blockEntry.getColor() : null;
                bakedQuads.addAll(ModelUtils.bakeQuads(location.getKey(), color));
            }
        }
        return bakedQuads;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    @Nonnull
    public TextureAtlasSprite getParticleTexture() {
        return ModelUtils.getBlockSprite(this.blockEntry.getBaseResource());
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public ItemCameraTransforms getItemCameraTransforms() {
        return ModelUtils.getDefaultTransforms();
    }

    @Override
    @Nonnull
    public ItemOverrideList getOverrides() {
        return ItemOverrideList.NONE;
    }
}
