package com.acronym.base.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class RenderingUtils {
    public static Minecraft mc = Minecraft.getMinecraft();

    /**
     * Renders an Item (Requires the block to be BlockRenderLayer.CUTOUT
     *
     * @param item  The EntityItem to render
     * @param angle The render angle
     */
    public static void render3DItem(EntityItem item, float angle) {
        GL11.glPushMatrix();
        item.hoverStart = 0F;
        GL11.glRotatef(angle, 0, 1, 0);
        mc.getRenderManager().doRenderEntity(item, 0, 0, 0, 0, 0, false);
        GL11.glPopMatrix();
    }

    /**
     * Sets the GL environment
     */
    private static void setupGlTranslucent() {
        glDisable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glShadeModel(GL_SMOOTH);
        glDisable(GL_ALPHA_TEST);
        glDisable(GL_CULL_FACE);

        RenderHelper.disableStandardItemLighting();

        OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);
    }

    /**
     * Renders a Translucent
     *
     * @param vertex    vertexBuffer
     * @param startX    startX
     * @param startY    startY
     * @param startZ    startZ
     * @param endX      endX
     * @param endY      endY
     * @param endZ      endZ
     * @param rotationX rotationx
     * @param rotationY rotationY
     * @param rotationZ rotationZ
     */
    private static void renderTranslucent(VertexBuffer vertex, double startX, double startY, double startZ, double endX, double endY, double endZ, double rotationX, double rotationY, double rotationZ) {
        glPushMatrix();
        glRotated(rotationX, 1, 0, 0);
        glRotated(rotationY, 0, 1, 0);
        glRotated(rotationZ, 0, 0, 1);

        glPushMatrix();
        for (int j = 0; j < 4; j++) {
            drawTranslucent(vertex, startX, startY, startZ, endX, endY, endZ);
            glRotatef(-90, 0, 1, 0);
        }
        glPopMatrix();

        glPopMatrix();
    }

    /**
     * draws a translucent
     *
     * @param vertex vertexBuffer
     * @param startX startX
     * @param startY startY
     * @param startZ startZ
     * @param endX   endX
     * @param endY   endY
     * @param endZ   endZ
     */
    private static void drawTranslucent(VertexBuffer vertex, double startX, double startY, double startZ, double endX, double endY, double endZ) {
        vertex.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        vertex.pos(-startX, startY, -startZ).color(100, 0, 0, 255).endVertex();
        vertex.pos(startX, startY, -startZ).color(0, 100, 0, 255).endVertex();
        vertex.pos(endX, endY, -endZ).color(0, 0, 100, 255).endVertex();
        vertex.pos(-endX, endY, -endZ).color(40, 20, 255, 255).endVertex();
        Tessellator.getInstance().draw();
    }

    /**
     * Renders a beam
     *
     * @param entity    entity
     * @param startX    startX
     * @param startY    startY
     * @param startZ    startZ
     * @param endX      endX
     * @param endY      endY
     * @param endZ      endZ
     * @param rotationX rotationX
     * @param rotationY rotationY
     * @param rotationZ rotationZ
     */
    public static void renderBeamAt(Entity entity, double startX, double startY, double startZ, double endX, double endY, double endZ, double rotationX, double rotationY, double rotationZ) {
        glPushMatrix();
        glTranslated(startX, startY, startZ);
        glPushAttrib(GL_ALL_ATTRIB_BITS);
        setupGlTranslucent();
        glTranslatef(0.5f, 0, 0.5f);
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertex = tessellator.getBuffer();
        renderTranslucent(vertex, startX, startY, startZ, endX, endY, endZ, rotationX, rotationY, rotationZ);
        glPopAttrib();
        glPopMatrix();
    }

    /**
     * Draws a 2D line
     *
     * @param x         startX
     * @param y         startY
     * @param x2        endX
     * @param y2        endY
     * @param red       redColour
     * @param green     greenColour
     * @param blue      blueColour
     * @param lineWidth lineWidth;
     */
    public static void drawLine(double x, double y, double x2, double y2, float red, float green, float blue, float lineWidth) {
        int existed = FMLClientHandler.instance().getClient().thePlayer.ticksExisted;
        float alpha = 0.3F + MathHelper.sin((float) (existed + x)) * 0.3F + 0.3F;
        Tessellator tess = Tessellator.getInstance();
        VertexBuffer buff = tess.getBuffer();
        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS | GL11.GL_LIGHTING_BIT);
        GL11.glLineWidth(lineWidth);
        GL11.glDisable(GL_TEXTURE_2D);
        GL11.glBlendFunc(770, 1);
        buff.begin(3, DefaultVertexFormats.POSITION_COLOR);
        buff.pos(x, y, 0).color(red, green, blue, alpha).endVertex();
        buff.pos(x2, y2, 0).color(red, green, blue, alpha).endVertex();
        tess.draw();
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(32826);
        GL11.glEnable(GL_TEXTURE_2D);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopAttrib();
        GL11.glPopMatrix();

    }

    /**
     * draws a 3D line
     *
     * @param x         startX
     * @param y         startY
     * @param z         startZ
     * @param x2        endX
     * @param y2        endY
     * @param z2        endZ
     * @param red       redColour
     * @param green     greenColour
     * @param blue      blueColour
     * @param lineWidth lineWidth
     */
    public static void drawLine(double x, double y, double z, double x2, double y2, double z2, float red, float green, float blue, float lineWidth) {

        int count = FMLClientHandler.instance().getClient().thePlayer.ticksExisted;
        float alpha = 0.3F + MathHelper.sin((float) (count + x)) * 0.3F + 0.3F;

        Tessellator tess = Tessellator.getInstance();
        VertexBuffer buff = tess.getBuffer();

        GL11.glPushMatrix();
        GL11.glLineWidth(lineWidth);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 1);
        buff.begin(3, DefaultVertexFormats.POSITION_COLOR);
        buff.pos(x, y, z).color(red, green, blue, alpha).endVertex();
        buff.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
        tess.draw();

        GL11.glBlendFunc(770, 771);
        GL11.glDisable(32826);
        GL11.glEnable(3553);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }

    /**
     * draws a non-fading 2D line
     *
     * @param x         startX
     * @param y         startY
     * @param x2        endX
     * @param y2        endY
     * @param red       redColour
     * @param green     greenColour
     * @param blue      blueColour
     * @param lineWidth lineWidth
     * @param alpha     alpha
     */
    public static void drawLineNoFade(double x, double y, double x2, double y2, float red, float green, float blue, float lineWidth, float alpha) {
        Tessellator tess = Tessellator.getInstance();
        VertexBuffer buff = tess.getBuffer();
        GL11.glPushMatrix();
        GL11.glLineWidth(lineWidth);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        buff.begin(3, DefaultVertexFormats.POSITION_COLOR);
        buff.pos(x, y, 0).color(red, green, blue, alpha).endVertex();
        buff.pos(x2, y2, 0).color(red, green, blue, alpha).endVertex();
        tess.draw();
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(32826);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glPopMatrix();
    }

    /**
     * Draws a non-fading 3D line
     *
     * @param x         startX
     * @param y         startY
     * @param z         startZ
     * @param x2        endX
     * @param y2        endY
     * @param z2        endZ
     * @param red       redColour
     * @param green     greenColour
     * @param blue      blueColour
     * @param lineWidth lineWidth
     * @param alpha     alpha
     */
    public static void drawLineNoFade(double x, double y, double z, double x2, double y2, double z2, float red, float green, float blue, float lineWidth, float alpha) {
        Tessellator tess = Tessellator.getInstance();
        VertexBuffer buff = tess.getBuffer();
        GL11.glPushMatrix();
        GL11.glLineWidth(lineWidth);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        buff.begin(3, DefaultVertexFormats.POSITION_COLOR);
        buff.pos(x, y, z).color(red, green, blue, alpha).endVertex();
        buff.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
        tess.draw();
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(32826);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glPopMatrix();
    }

    /**
     * Draws a fading 2D line
     *
     * @param x         startX
     * @param y         startY
     * @param x2        endX
     * @param y2        endY
     * @param red       redColour
     * @param green     greenColour
     * @param blue      blueColour
     * @param lineWidth lineWidth
     * @param fadeSpeed fadeSpeed
     */
    public static void drawLine(double x, double y, double x2, double y2, float red, float green, float blue, float lineWidth, float fadeSpeed) {
        int count = FMLClientHandler.instance().getClient().thePlayer.ticksExisted;
        float alpha = fadeSpeed + MathHelper.sin((float) (count + x)) * 0.3F + 0.3F;
        Tessellator tess = Tessellator.getInstance();
        VertexBuffer buff = tess.getBuffer();
        GL11.glPushMatrix();
        GL11.glLineWidth(lineWidth);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        buff.begin(3, DefaultVertexFormats.POSITION_COLOR);
        buff.pos(x, y, 0).color(red, green, blue, alpha).endVertex();
        buff.pos(x2, y2, 0).color(red, green, blue, alpha).endVertex();
        tess.draw();
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(32826);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glPopMatrix();
    }

    /**
     * Draws a fading 3D line
     *
     * @param x         startX
     * @param y         startY
     * @param z         startZ
     * @param x2        endX
     * @param y2        endY
     * @param z2        endZ
     * @param red       redColour
     * @param green     greenColour
     * @param blue      blueColour
     * @param lineWidth lineWidth
     * @param fadeSpeed fadeSpeed
     */
    public static void drawLine(double x, double y, double z, double x2, double y2, double z2, float red, float green, float blue, float lineWidth, float fadeSpeed) {
        int existed = FMLClientHandler.instance().getClient().thePlayer.ticksExisted;
        float alpha = 0.3F + MathHelper.sin((float) (existed + x)) * 0.3F + 0.3F;
        Tessellator tess = Tessellator.getInstance();
        VertexBuffer buff = tess.getBuffer();
        GL11.glPushMatrix();
        GL11.glLineWidth(lineWidth);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 1);
        buff.begin(3, DefaultVertexFormats.POSITION_COLOR);
        buff.pos(x, y, z).color(red, green, blue, alpha).endVertex();
        buff.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
        tess.draw();
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(32826);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glPopMatrix();
    }

    /**
     * Draws a 2D rectangle
     *
     * @param x         startX
     * @param y         startY
     * @param width     width
     * @param height    height
     * @param red       redColour
     * @param green     greenColour
     * @param blue      blueColour
     * @param lineWidth lineWidth
     */
    public static void drawRect(double x, double y, double width, double height, float red, float green, float blue, float lineWidth) {
        drawLine(x, y, x + width, y, red, green, blue, lineWidth, 0);
        drawLine(x + width, y, x + width, y + width, red, green, blue, lineWidth, 0);
        drawLine(x + width, y + width, x, y + width, red, green, blue, lineWidth, 0);
        drawLine(x, y + width, x, y, red, green, blue, lineWidth, 0);
    }

    /**
     * Drawsa non-fading 2D rectangle
     *
     * @param x         startX
     * @param y         startY
     * @param width     width
     * @param height    height
     * @param red       redcolour
     * @param green     greenColour
     * @param blue      blueColour
     * @param lineWidth lineWidth
     * @param alpha     alpha
     */
    public static void drawRectFadeless(double x, double y, double width, double height, float red, float green, float blue, float lineWidth, float alpha) {
        drawLineNoFade(x, y, x + width, y, red, green, blue, lineWidth, alpha);
        drawLineNoFade(x + width, y, x + width, y + width, red, green, blue, lineWidth, alpha);
        drawLineNoFade(x + width, y + width, x, y + width, red, green, blue, lineWidth, alpha);
        drawLineNoFade(x, y + width, x, y, red, green, blue, lineWidth, alpha);
    }

    /**
     * Draws a fading 2D rectangle
     *
     * @param x         startX
     * @param y         startY
     * @param width     width
     * @param height    height
     * @param red       redColour
     * @param green     greenColour
     * @param blue      blueColour
     * @param lineWidth lineWidth
     * @param fadeSpeed fadeSpeed
     */
    public static void drawRect(double x, double y, double width, double height, float red, float green, float blue, float lineWidth, float fadeSpeed) {
        drawLine(x, y, x + width, y, red, green, blue, lineWidth, fadeSpeed);
        drawLine(x + width, y, x + width, y + width, red, green, blue, lineWidth, fadeSpeed);
        drawLine(x + width, y + width, x, y + width, red, green, blue, lineWidth, fadeSpeed);
        drawLine(x, y + width, x, y, red, green, blue, lineWidth, fadeSpeed);
    }

    /**
     * Draws a 3D rectange
     * @param x startX
     * @param y startY
     * @param z startZ
     * @param width width
     * @param height height
     * @param red redColour
     * @param green greenColour
     * @param blue blueColour
     * @param lineWidth lineWidth
     */
    public static void drawRect(double x, double y, double z, double width, double height, float red, float green, float blue, float lineWidth) {
        drawLine(x, y, z, x + width, y, z, red, green, blue, lineWidth);
        drawLine(x + width, y, z, x + width, y + width, z, red, green, blue, lineWidth);
        drawLine(x + width, y + width, z, x, y + width, z, red, green, blue, lineWidth);
        drawLine(x, y + width, z, x, y, z, red, green, blue, lineWidth);
    }

    /**
     * Draws a non-fading 3D rectangle
     * @param x startX
     * @param y startY
     * @param z startZ
     * @param width width
     * @param height height
     * @param red redColour
     * @param green greenColour
     * @param blue blueColour
     * @param lineWidth lineWidth
     * @param alpha alpha
     */
    public static void drawRectNoFade(double x, double y, double z, double width, double height, float red, float green, float blue, float lineWidth, float alpha) {
        drawLineNoFade(x, y, z, x + width, y, z, red, green, blue, lineWidth, alpha);
        drawLineNoFade(x + width, y, z, x + width, y + width, z, red, green, blue, lineWidth, alpha);
        drawLineNoFade(x + width, y + width, z, x, y + width, z, red, green, blue, lineWidth, alpha);
        drawLineNoFade(x, y + width, z, x, y, z, red, green, blue, lineWidth, alpha);
    }

}
