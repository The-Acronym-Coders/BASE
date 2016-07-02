package com.acronym.base.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
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

    private static void setupGlTranslucent() {
        glDisable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glShadeModel(GL_SMOOTH);
        glDisable(GL_ALPHA_TEST);
        glDisable(GL_CULL_FACE);

        RenderHelper.disableStandardItemLighting();

        OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);
    }

    private static void renderAllTranslucent(VertexBuffer tessellator, double startX, double startY, double startZ, double endX, double endY, double endZ, double rotationX, double rotationY, double rotationZ) {
        glPushMatrix();
        glRotated(rotationX, 1, 0, 0);
        glRotated(rotationY, 0, 1, 0);
        glRotated(rotationZ, 0, 0, 1);

        glPushMatrix();
        for (int j = 0; j < 4; j++) {
            drawPowerTranslucent(tessellator, startX, startY, startZ, endX, endY, endZ);
            glRotatef(-90, 0, 1, 0);
        }
        glPopMatrix();

        glPopMatrix();
    }

    private static void drawPowerTranslucent(VertexBuffer vertex, double startX, double startY, double startZ, double endX, double endY, double endZ) {
        vertex.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        vertex.pos(-startX, startY, -startZ).color(100, 0, 0, 255).endVertex();
        vertex.pos(startX, startY, -startZ).color(0, 100, 0, 255).endVertex();
        vertex.pos(endX, endY, -endZ).color(0, 0, 100, 255).endVertex();
        vertex.pos(-endX, endY, -endZ).color(40, 20, 255, 255).endVertex();
        Tessellator.getInstance().draw();
    }

    public static void renderBeamAt(Entity entity, double startX, double startY, double startZ, double endX, double endY, double endZ, double rotationX, double rotationY, double rotationZ) {
        glPushMatrix();
        glTranslated(startX, startY, startZ);
        glPushAttrib(GL_ALL_ATTRIB_BITS);
        setupGlTranslucent();
        glTranslatef(0.5f, 0, 0.5f);
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertex = tessellator.getBuffer();
        renderAllTranslucent(vertex, startX, startY, startZ, endX, endY, endZ, rotationX, rotationY, rotationZ);
        glPopAttrib();
        glPopMatrix();
    }

}
