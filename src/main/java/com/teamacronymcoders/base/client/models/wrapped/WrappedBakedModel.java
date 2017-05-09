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
import java.util.List;

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
            bakedQuads.addAll(bakeQuads(getTexture(this.blockEntry.getResourceLocation())));
            bakedQuads.addAll(bakeQuads(getTexture(this.blockEntry.getLayers().get(0))));
        }
        return bakedQuads;
    }

    private TextureAtlasSprite getTexture(ResourceLocation resourceLocation) {
        if (!resourceLocation.getResourcePath().contains("blocks/")) {
            resourceLocation = new ResourceLocation(resourceLocation.getResourceDomain(), "blocks/" + resourceLocation.getResourcePath());
        }
        return ModelUtils.getRegisterSprite(Minecraft.getMinecraft().getTextureMapBlocks(), resourceLocation);
    }

    private List<BakedQuad> bakeQuads(TextureAtlasSprite sprite) {
        List<BakedQuad> quads = Lists.newArrayListWithExpectedSize(6);
        float[] colour = {1, 1, 1, 1};
        Vector3f[] vertices = {new Vector3f(0, 0, 0), new Vector3f(0, 0, 1), new Vector3f(1, 0, 1), new Vector3f(1, 0, 0)};
        quads.add(ModelUtils.createBakedQuad(DefaultVertexFormats.ITEM, vertices, EnumFacing.DOWN, sprite, new double[]{0, 16, 16, 0}, colour, true));
        vertices = new Vector3f[]{new Vector3f(0, 1, 0), new Vector3f(0, 1, 1), new Vector3f(1, 1, 1), new Vector3f(1, 1, 0)};
        quads.add(ModelUtils.createBakedQuad(DefaultVertexFormats.ITEM, vertices, EnumFacing.UP, sprite, new double[]{0, 0, 16, 16}, colour, false));

        vertices = new Vector3f[]{new Vector3f(1, 0, 0), new Vector3f(1, 1, 0), new Vector3f(0, 1, 0), new Vector3f(0, 0, 0)};
        quads.add(ModelUtils.createBakedQuad(DefaultVertexFormats.ITEM, vertices, EnumFacing.NORTH, sprite, new double[]{0, 16, 16, 0}, colour, true));
        vertices = new Vector3f[]{new Vector3f(1, 0, 1), new Vector3f(1, 1, 1), new Vector3f(0, 1, 1), new Vector3f(0, 0, 1)};
        quads.add(ModelUtils.createBakedQuad(DefaultVertexFormats.ITEM, vertices, EnumFacing.SOUTH, sprite, new double[]{16, 16, 0, 0}, colour, false));

        vertices = new Vector3f[]{new Vector3f(0, 0, 0), new Vector3f(0, 1, 0), new Vector3f(0, 1, 1), new Vector3f(0, 0, 1)};
        quads.add(ModelUtils.createBakedQuad(DefaultVertexFormats.ITEM, vertices, EnumFacing.WEST, sprite, new double[]{0, 16, 16, 0}, colour, true));
        vertices = new Vector3f[]{new Vector3f(1, 0, 0), new Vector3f(1, 1, 0), new Vector3f(1, 1, 1), new Vector3f(1, 0, 1)};
        quads.add(ModelUtils.createBakedQuad(DefaultVertexFormats.ITEM, vertices, EnumFacing.EAST, sprite, new double[]{16, 16, 0, 0}, colour, false));
        return quads;
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
        return ModelUtils.getRegisterSprite(Minecraft.getMinecraft().getTextureMapBlocks(), this.blockEntry.getResourceLocation());
    }

    @SuppressWarnings("deprecation")
    static final ItemCameraTransforms defaultTransforms = new ItemCameraTransforms(
            new ItemTransformVec3f(new Vector3f(75, 45, 0), new Vector3f(0, .25f, 0), new Vector3f(0.375f, 0.375f, 0.375f)), //thirdperson left
            new ItemTransformVec3f(new Vector3f(75, 45, 0), new Vector3f(0, .15625f, 0), new Vector3f(0.375f, 0.375f, 0.375f)), //thirdperson left

            new ItemTransformVec3f(new Vector3f(0, 45, 0), new Vector3f(0, 0, 0), new Vector3f(.4f, .4f, .4f)), //firstperson left
            new ItemTransformVec3f(new Vector3f(0, 225, 0), new Vector3f(0, 0, 0), new Vector3f(.4f, .4f, .4f)), //firstperson right

            new ItemTransformVec3f(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), //head
            new ItemTransformVec3f(new Vector3f(30, 225, 0), new Vector3f(0, 0, 0), new Vector3f(.625f, .625f, .625f)), //gui
            new ItemTransformVec3f(new Vector3f(0, 0, 0), new Vector3f(0, .1875f, 0), new Vector3f(.25f, .25f, .25f)), //ground
            new ItemTransformVec3f(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(.5f, .5f, .5f))); //fixed

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public ItemCameraTransforms getItemCameraTransforms() {
        return defaultTransforms;
    }

    @Override
    @Nonnull
    public ItemOverrideList getOverrides() {
        return ItemOverrideList.NONE;
    }
}
