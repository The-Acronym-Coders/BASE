package com.acronym.base.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.awt.*;
import java.util.List;

public class GuiHelper extends GuiScreen {
    private Minecraft mc = Minecraft.getMinecraft();
    private FontRenderer fontRenderer = mc.fontRendererObj;

    private void drawWindow(int x, int y, int w, int h, int bgColor) {
        drawRect(x - 3, y - 4, x + w + 3, y - 3, bgColor);
        drawRect(x - 3, y + h + 3, x + w + 3, y + h + 4, bgColor);
        drawRect(x - 3, y - 3, x + w + 3, y + h + 3, bgColor);
        drawRect(x - 4, y - 3, x - 3, y + h + 3, bgColor);
        drawRect(x + w + 3, y - 3, x + w + 4, y + h + 3, bgColor);
    }

    public void drawWindowWithBorder(int x, int y, int w, int h, int bgColor, int frameColor) {
        drawWindow(x, y, w, h, bgColor);
        int frameFade;
        frameFade = (frameColor & 0xFEFEFE) >> 1 | frameColor & 0xFF000000;

        drawGradientRect(x - 3, y - 3 + 1, x - 3 + 1, y + h + 3 - 1, frameColor, frameFade);
        drawGradientRect(x + w + 2, y - 3 + 1, x + w + 3, y + h + 3 - 1, frameColor, frameFade);
        drawGradientRect(x - 3, y - 3, x + w + 3, y - 3 + 1, frameColor, frameFade);
        drawGradientRect(x - 3, y + h + 2, x + w + 3, y + h + 3, frameColor, frameFade);
    }

    public void drawLineOnVerticalProgressBar(int x, int y, int w, int h, int p, int pMax, int lineColor) {
        x -= 2;
        y -= 2;
        w += 4;
        h += 4;

        //int lineY = Math.round(((float) h / 100) * p);

        //int lineY = Math.round(((((float)p) / ((float)(pMax - 20)))) * h);
        int lineY = Math.round((float) p / (float) pMax * h);

        drawRect(x, y + h - lineY, w + x, y + h - lineY + 1, lineColor);
    }

    public void drawVerticalProgressBar(int x, int y, int w, int h, int p, int bgColor, int frameColor, int progressColor) {
        drawProgressBar(x, y, w, h, p, bgColor, frameColor, progressColor, 1);
    }

    public void drawHorizontalProgressBar(int x, int y, int w, int h, int p, int bgColor, int frameColor, int progressColor) {
        drawProgressBar(x, y, w, h, p, bgColor, frameColor, progressColor, 0);
    }

    private void drawProgressBar(int x, int y, int w, int h, int p, int bgColor, int frameColor, int progressColor, int hv) {
        drawWindowWithBorder(x, y, w, h, bgColor, frameColor);

        // Adjust x, y, w, h to fit progress bar inside window...
        x -= 2;
        y -= 2;
        w += 4;
        h += 4;

        switch (hv) {
            case 0:
                float pWf = ((float) w / 100) * p;
                int pW = Math.round(pWf);
                drawRect(x, y, x + pW, y + h, progressColor);
                break;
            case 1:
                float pHf = ((float) h / 100) * p;
                int pH = Math.round(pHf);
                drawRect(x, y + h - pH, w + x, h + y, progressColor);
                break;
            default:
                break;
        }
    }

    public void drawItemStack(ItemStack itemStack, int x, int y) {
        int[][] savedGLState = OpenGLHelper.saveGLState(new int[]{GL11.GL_ALPHA_TEST, GL11.GL_LIGHTING});
        RenderItem renderItem = mc.getRenderItem();

        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        renderItem.renderItemIntoGUI(itemStack, x, y);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();

        OpenGLHelper.restoreGLState(savedGLState);
    }

    /**
     * Draws a transparent item in the slot
     *
     * @param itemStack  item to draw
     * @param x          slot x
     * @param y          slot y
     * @param renderItem Item Render
     */
    public void drawItemStack(ItemStack itemStack, int x, int y, RenderItem renderItem, boolean transparent) {
        this.zLevel = 50.0f;
        renderItem.zLevel = 50.0f;

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        int colorOverlay = new Color(139, 139, 139, 160).hashCode();

        RenderHelper.enableGUIStandardItemLighting();
        renderItem.renderItemAndEffectIntoGUI(itemStack, x, y);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GlStateManager.disableDepth();
        GlStateManager.colorMask(true, true, true, false);
        if (transparent) {
            this.zLevel = 100.0f;
            renderItem.zLevel = 100.0f;
            this.drawGradientRect(x, y, x + 16, y + 16, colorOverlay, colorOverlay);
        }
        GlStateManager.colorMask(true, true, true, true);
        GlStateManager.enableDepth();

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        this.zLevel = 0.0f;
        renderItem.zLevel = 0.0f;
    }

    public void drawMiniItemStack(ItemStack itemStack, int x, int y) {
        int[][] savedGLState = OpenGLHelper.saveGLState(new int[]{GL11.GL_ALPHA_TEST, GL11.GL_LIGHTING});
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        drawItemStack(itemStack, x, y);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        GL11.glPopMatrix();
        OpenGLHelper.restoreGLState(savedGLState);
    }

    /**
     * Draws a slot that is disabled...
     *
     * @param x          slot x
     * @param y          slot y
     * @param renderItem Item Render
     */
    public void drawDisabledSlot(int x, int y, RenderItem renderItem) {
        this.zLevel = 100.f;
        renderItem.zLevel = 100.0f;

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int colorOverlay = new Color(139, 139, 139, 200).hashCode();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        GlStateManager.colorMask(true, true, true, false);
        renderItem.renderItemAndEffectIntoGUI(new ItemStack(Blocks.BARRIER), x, y);
        this.drawGradientRect(x, y, x + 16, y + 16, colorOverlay, colorOverlay);
        GlStateManager.colorMask(true, true, true, true);
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.zLevel = 0.0f;
        renderItem.zLevel = 0.0f;
    }

    public void drawCenteredStringWithShadow(int x, int y, int w, String message, int color) {
        int messageWidth = fontRenderer.getStringWidth(message);
        int messageX = x + ((w >> 1) - (messageWidth >> 1));

        fontRenderer.drawStringWithShadow(message, messageX, y, color);
    }

    public void drawStringWithShadow(int x, int y, String message, int color) {
        fontRenderer.drawStringWithShadow(message, x, y, color);
    }

    public void drawCenteredString(int x, int y, int w, String message, int color) {
        int messageWidth = fontRenderer.getStringWidth(message);
        int messageX = x + ((w >> 1) - (messageWidth >> 1));

        fontRenderer.drawString(message, messageX, y, color);
    }

    public void renderSplitString(String str, int x, int y, int wrapWidth, int textColor) {
        int posY = y;
        for (String s : fontRenderer.listFormattedStringToWidth(str, wrapWidth)) {
            drawStringWithShadow(x, posY, s, textColor);
            posY += fontRenderer.FONT_HEIGHT;
        }
    }

    public List<String> getSplitString(String str, int wrapWidth) {
        return fontRenderer.listFormattedStringToWidth(str, wrapWidth);
    }

    public void drawPlayerHead(int x, int y) {
        ResourceLocation playerSkin = Minecraft.getMinecraft().thePlayer.getLocationSkin();
        mc.getTextureManager().bindTexture(playerSkin);

        int[][] savedGLState = OpenGLHelper.saveGLState(new int[]{GL11.GL_ALPHA_TEST, GL11.GL_LIGHTING});
        GL11.glPushMatrix();
        GL11.glScalef(1.0F, 0.5F, 1.0F);

        this.drawTexturedModalRect(x, y, 32, 64, 32, 64);
        this.drawTexturedModalRect(x, y, 160, 64, 32, 64);

        GL11.glPopMatrix();
        OpenGLHelper.restoreGLState(savedGLState);
    }

    private void drawPixels(int x, int y, int u, int v, int width, int height, int times) {
        for (int cu = u; cu < u + width; cu++) {
            for (int cv = v; cv < v + height; cv++) {
                for (int factor = 0; factor < times; factor++) {
                    drawTexturedModalRect(x + (cu * 3) + factor, y + (cv * 3) + factor, cu + u, cv + v, 1, 1);
                }
            }
        }
    }

    public void drawResource(ResourceLocation resource, int x, int y, int x1, int y1, int w, int h) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(resource);
        drawTexturedModalRect(0, 0, 0, 0, 128, 128);
    }
}
