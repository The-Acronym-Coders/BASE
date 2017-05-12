package com.teamacronymcoders.base.client.models;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.model.obj.OBJModel.Normal;
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.util.vector.Vector3f;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

//Borrowed from IE with Permission
@SideOnly(Side.CLIENT)
public class ModelUtils {
    private static float[] alphaNoFading = {1, 1, 1, 1};

    public static BakedQuad createBakedQuad(VertexFormat format, Vector3f[] vertices, EnumFacing facing, TextureAtlasSprite sprite, double[] uvs, float[] colour, boolean invert) {
        return createBakedQuad(format, vertices, facing, sprite, uvs, colour, invert, alphaNoFading);
    }

    public static BakedQuad createBakedQuad(VertexFormat format, Vector3f[] vertices, EnumFacing facing, TextureAtlasSprite sprite, double[] uvs, float[] colour, boolean invert, float[] alpha) {
        return createBakedQuad(format, vertices, facing, sprite, uvs, colour, invert, alpha, false, null);
    }

    public static BakedQuad createBakedQuad(VertexFormat format, Vector3f[] vertices, EnumFacing facing, TextureAtlasSprite sprite, double[] uvs, float[] colour, boolean invert, float[] alpha, boolean smartLighting, BlockPos basePos) {
        UnpackedBakedQuad.Builder builder = new UnpackedBakedQuad.Builder(format);
        builder.setQuadOrientation(facing);
        builder.setTexture(sprite);
        Normal faceNormal = new Normal(facing.getDirectionVec().getX(), facing.getDirectionVec().getY(), facing.getDirectionVec().getZ());
        int vId = invert ? 3 : 0;
        int u = vId > 1 ? 2 : 0;
        putVertexData(format, builder, vertices[vId], faceNormal, uvs[u], uvs[1], sprite, colour, alpha[invert ? 3 : 0]);
        vId = invert ? 2 : 1;
        u = vId > 1 ? 2 : 0;
        putVertexData(format, builder, vertices[invert ? 2 : 1], faceNormal, uvs[u], uvs[3], sprite, colour, alpha[invert ? 2 : 1]);
        vId = invert ? 1 : 2;
        u = vId > 1 ? 2 : 0;
        putVertexData(format, builder, vertices[invert ? 1 : 2], faceNormal, uvs[u], uvs[3], sprite, colour, alpha[invert ? 1 : 2]);
        vId = invert ? 1 : 3;
        u = vId > 1 ? 2 : 0;
        putVertexData(format, builder, vertices[invert ? 0 : 3], faceNormal, uvs[u], uvs[1], sprite, colour, alpha[invert ? 0 : 3]);
        BakedQuad tmp = builder.build();
        return smartLighting ? new SmartLightingQuad(tmp.getVertexData(), -1, facing, sprite, format, basePos) : tmp;
    }

    protected static void putVertexData(VertexFormat format, UnpackedBakedQuad.Builder builder, Vector3f pos, Normal faceNormal, double u, double v, TextureAtlasSprite sprite, float[] colour, float alpha) {
        for (int e = 0; e < format.getElementCount(); e++) {
            switch (format.getElement(e).getUsage()) {
                case POSITION:
                    builder.put(e, pos.getX(), pos.getY(), pos.getZ(), 0);
                    break;
                case COLOR:
                    float d = 1;//LightUtil.diffuseLight(faceNormal.x, faceNormal.y, faceNormal.z);
                    builder.put(e, d * colour[0], d * colour[1], d * colour[2], 1 * colour[3] * alpha);
                    break;
                case UV:
                    if (sprite == null)//Double Safety. I have no idea how it even happens, but it somehow did .-.
                        sprite = Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
                    builder.put(e, sprite.getInterpolatedU(u), sprite.getInterpolatedV((v)), 0, 1);
                    break;
                case NORMAL:
                    builder.put(e, faceNormal.x, faceNormal.y, faceNormal.z, 0);
                    break;
                default:
                    builder.put(e);
            }
        }
    }

    public static TextureAtlasSprite getBlockSprite(String path) {
        return Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry(path);
    }

    public static TextureAtlasSprite getBlockSprite(ResourceLocation path) {
        if (!path.getResourcePath().contains("blocks/")) {
            path = new ResourceLocation(path.getResourceDomain(), "blocks/" + path.getResourcePath());
        }
        return getBlockSprite(path.toString());
    }

    @SuppressWarnings("deprecation")
    private static final ItemCameraTransforms defaultTransforms = new ItemCameraTransforms(
            new ItemTransformVec3f(new Vector3f(75, 45, 0), new Vector3f(0, .25f, 0), new Vector3f(0.375f, 0.375f, 0.375f)), //thirdperson left
            new ItemTransformVec3f(new Vector3f(75, 45, 0), new Vector3f(0, .15625f, 0), new Vector3f(0.375f, 0.375f, 0.375f)), //thirdperson left

            new ItemTransformVec3f(new Vector3f(0, 45, 0), new Vector3f(0, 0, 0), new Vector3f(.4f, .4f, .4f)), //firstperson left
            new ItemTransformVec3f(new Vector3f(0, 225, 0), new Vector3f(0, 0, 0), new Vector3f(.4f, .4f, .4f)), //firstperson right

            new ItemTransformVec3f(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)), //head
            new ItemTransformVec3f(new Vector3f(30, 225, 0), new Vector3f(0, 0, 0), new Vector3f(.625f, .625f, .625f)), //gui
            new ItemTransformVec3f(new Vector3f(0, 0, 0), new Vector3f(0, .1875f, 0), new Vector3f(.25f, .25f, .25f)), //ground
            new ItemTransformVec3f(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(.5f, .5f, .5f))); //fixed

    public static ItemCameraTransforms getDefaultTransforms() {
        return defaultTransforms;
    }

    public static List<BakedQuad> bakeQuads(ResourceLocation sprite, Color color) {
        return bakeQuads(getBlockSprite(sprite), color);
    }

    public static List<BakedQuad> bakeQuads(TextureAtlasSprite sprite, Color color) {
        TextureAtlasSprite[] sprites = new TextureAtlasSprite[6];
        Arrays.fill(sprites, sprite);
        return bakeQuads(sprites, color);
    }

    public static List<BakedQuad> bakeQuads(TextureAtlasSprite[] sprites, Color color) {
        List<BakedQuad> quads = Lists.newArrayListWithExpectedSize(6);
        float[] colorArray;
        if (color != null) {
            colorArray = color.getRGBComponents(new float[4]);
        } else {
            colorArray = new float[]{1, 1, 1, 1};
        }
        Vector3f[] vertices = {new Vector3f(0, 0, 0), new Vector3f(0, 0, 1), new Vector3f(1, 0, 1), new Vector3f(1, 0, 0)};
        quads.add(ModelUtils.createBakedQuad(DefaultVertexFormats.ITEM, vertices, EnumFacing.DOWN, sprites[0], new double[]{0, 16, 16, 0}, colorArray, true));
        vertices = new Vector3f[]{new Vector3f(0, 1, 0), new Vector3f(0, 1, 1), new Vector3f(1, 1, 1), new Vector3f(1, 1, 0)};
        quads.add(ModelUtils.createBakedQuad(DefaultVertexFormats.ITEM, vertices, EnumFacing.UP, sprites[1], new double[]{0, 0, 16, 16}, colorArray, false));

        vertices = new Vector3f[]{new Vector3f(1, 0, 0), new Vector3f(1, 1, 0), new Vector3f(0, 1, 0), new Vector3f(0, 0, 0)};
        quads.add(ModelUtils.createBakedQuad(DefaultVertexFormats.ITEM, vertices, EnumFacing.NORTH, sprites[2], new double[]{0, 16, 16, 0}, colorArray, true));
        vertices = new Vector3f[]{new Vector3f(1, 0, 1), new Vector3f(1, 1, 1), new Vector3f(0, 1, 1), new Vector3f(0, 0, 1)};
        quads.add(ModelUtils.createBakedQuad(DefaultVertexFormats.ITEM, vertices, EnumFacing.SOUTH, sprites[3], new double[]{16, 16, 0, 0}, colorArray, false));

        vertices = new Vector3f[]{new Vector3f(0, 0, 0), new Vector3f(0, 1, 0), new Vector3f(0, 1, 1), new Vector3f(0, 0, 1)};
        quads.add(ModelUtils.createBakedQuad(DefaultVertexFormats.ITEM, vertices, EnumFacing.WEST, sprites[4], new double[]{0, 16, 16, 0}, colorArray, true));
        vertices = new Vector3f[]{new Vector3f(1, 0, 0), new Vector3f(1, 1, 0), new Vector3f(1, 1, 1), new Vector3f(1, 0, 1)};
        quads.add(ModelUtils.createBakedQuad(DefaultVertexFormats.ITEM, vertices, EnumFacing.EAST, sprites[5], new double[]{16, 16, 0, 0}, colorArray, false));
        return quads;
    }
}
