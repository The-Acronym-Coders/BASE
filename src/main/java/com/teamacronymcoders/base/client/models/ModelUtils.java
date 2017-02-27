package com.teamacronymcoders.base.client.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.model.obj.OBJModel.Normal;
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.util.vector.Vector3f;

import javax.annotation.Nullable;

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

    @SideOnly(Side.CLIENT)
    public static TextureAtlasSprite getRegisterSprite(TextureMap map, String path) {
        TextureAtlasSprite sprite = map.getTextureExtry(path);
        if (sprite == null) {
            map.registerSprite(new ResourceLocation(path));
            sprite = map.getTextureExtry(path);
        }
        return sprite;
    }

    @SideOnly(Side.CLIENT)
    public static TextureAtlasSprite getRegisterSprite(TextureMap map, ResourceLocation path) {
        TextureAtlasSprite sprite = map.getTextureExtry(path.toString());
        if (sprite == null) {
            map.registerSprite(path);
            sprite = map.getTextureExtry(path.toString());
        }
        return sprite;
    }
}
