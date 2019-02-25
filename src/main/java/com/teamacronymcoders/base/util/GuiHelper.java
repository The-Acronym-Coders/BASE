package com.teamacronymcoders.base.util;

import java.awt.Color;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.teamacronymcoders.base.client.ClientHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.*;

public class GuiHelper extends GuiScreen {
    private Minecraft mc = Minecraft.getMinecraft();
    private FontRenderer fontRenderer = mc.fontRenderer;
	public static final ResourceLocation BLOCK_TEX = TextureMap.LOCATION_BLOCKS_TEXTURE;

    /**
     * Draws a window
     *
     * @param x        startX
     * @param y        startY
     * @param w        width
     * @param h        height
     * @param bgColour background colour
     */
    private void drawWindow(int x, int y, int w, int h, int bgColour) {
        drawRect(x - 3, y - 4, x + w + 3, y - 3, bgColour);
        drawRect(x - 3, y + h + 3, x + w + 3, y + h + 4, bgColour);
        drawRect(x - 3, y - 3, x + w + 3, y + h + 3, bgColour);
        drawRect(x - 4, y - 3, x - 3, y + h + 3, bgColour);
        drawRect(x + w + 3, y - 3, x + w + 4, y + h + 3, bgColour);
    }

    /**
     * Draws a window without a border
     *
     * @param x           startX
     * @param y           startY
     * @param w           width
     * @param h           height
     * @param bgColour    background colour
     * @param frameColour frame colour
     */
    public void drawWindowWithBorder(int x, int y, int w, int h, int bgColour, int frameColour) {
        drawWindow(x, y, w, h, bgColour);
        int frameFade;
        frameFade = (frameColour & 0xFEFEFE) >> 1 | frameColour & 0xFF000000;

        drawGradientRect(x - 3, y - 3 + 1, x - 3 + 1, y + h + 3 - 1, frameColour, frameFade);
        drawGradientRect(x + w + 2, y - 3 + 1, x + w + 3, y + h + 3 - 1, frameColour, frameFade);
        drawGradientRect(x - 3, y - 3, x + w + 3, y - 3 + 1, frameColour, frameFade);
        drawGradientRect(x - 3, y + h + 2, x + w + 3, y + h + 3, frameColour, frameFade);
    }

    /**
     * Draws a verticle line on a progress bar
     *
     * @param x         startX
     * @param y         startY
     * @param w         width
     * @param h         height
     * @param p         progress
     * @param pMax      progressMax
     * @param lineColor lineColour
     */
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

    /**
     * Draws a verticle progress bar
     *
     * @param x              startX
     * @param y              startY
     * @param w              width
     * @param h              height
     * @param p              progress
     * @param bgColour       background colour
     * @param frameColour    frame colour
     * @param progressColour progress colour
     */
    public void drawVerticalProgressBar(int x, int y, int w, int h, int p, int bgColour, int frameColour, int progressColour) {
        drawProgressBar(x, y, w, h, p, bgColour, frameColour, progressColour, 1);
    }

    /**
     * Draws a horizontal progress bar
     *
     * @param x              startX
     * @param y              startY
     * @param w              width
     * @param h              height
     * @param p              progress
     * @param bgColour       background colour
     * @param frameColour    frame colour
     * @param progressColour progress colour
     */
    public void drawHorizontalProgressBar(int x, int y, int w, int h, int p, int bgColour, int frameColour, int progressColour) {
        drawProgressBar(x, y, w, h, p, bgColour, frameColour, progressColour, 0);
    }

    /**
     * Draws a progress bar
     *
     * @param x              startX
     * @param y              startY
     * @param w              width
     * @param h              height
     * @param p              progress
     * @param bgColour       background colour
     * @param frameColour    frame colour
     * @param progressColour
     * @param hv             Horizontal(0) or Verticle(1)
     */
    private void drawProgressBar(int x, int y, int w, int h, int p, int bgColour, int frameColour, int progressColour, int hv) {
        drawWindowWithBorder(x, y, w, h, bgColour, frameColour);

        // Adjust x, y, w, h to fit progress bar inside window...
        x -= 2;
        y -= 2;
        w += 4;
        h += 4;

        switch (hv) {
            case 0:
                float pWf = ((float) w / 100) * p;
                int pW = Math.round(pWf);
                drawRect(x, y, x + pW, y + h, progressColour);
                break;
            case 1:
                float pHf = ((float) h / 100) * p;
                int pH = Math.round(pHf);
                drawRect(x, y + h - pH, w + x, h + y, progressColour);
                break;
            default:
                break;
        }
    }

    /**
     * Draws an ItemStack
     *
     * @param itemStack itemstack to draw
     * @param x         startX
     * @param y         startY
     */
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

    /**
     * Draws a mini ItemStack
     *
     * @param itemStack ItemStack to draw
     * @param x         startX
     * @param y         startY
     */
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

    /**
     * Draws a centered String with a shadow
     *
     * @param x       startX
     * @param y       startY
     * @param w       width
     * @param message message
     * @param Colour  Colour
     */
    public void drawCenteredStringWithShadow(int x, int y, int w, String message, int Colour) {
        int messageWidth = fontRenderer.getStringWidth(message);
        int messageX = x + ((w >> 1) - (messageWidth >> 1));

        fontRenderer.drawStringWithShadow(message, messageX, y, Colour);
    }

    /**
     * Draws a String with a shadow
     *
     * @param x       startX
     * @param y       startY
     * @param message message
     * @param colour  colour
     */
    public void drawStringWithShadow(int x, int y, String message, int colour) {
        fontRenderer.drawStringWithShadow(message, x, y, colour);
    }

    /**
     * Draws a centered String
     *
     * @param x       startX
     * @param y       startY
     * @param w       width
     * @param message message
     * @param colour  colour
     */
    public void drawCenteredString(int x, int y, int w, String message, int colour) {
        int messageWidth = fontRenderer.getStringWidth(message);
        int messageX = x + ((w >> 1) - (messageWidth >> 1));

        fontRenderer.drawString(message, messageX, y, colour);
    }

    /**
     * Renders a String with line wrap
     *
     * @param str        String to render
     * @param x          startX
     * @param y          startY
     * @param wrapWidth  wrap width
     * @param textColour textColour
     */
    public void renderSplitString(String str, int x, int y, int wrapWidth, int textColour) {
        int posY = y;

        for (String s : fontRenderer.listFormattedStringToWidth(str, wrapWidth)) {
            drawStringWithShadow(x, posY, s, textColour);
            posY += fontRenderer.FONT_HEIGHT;
        }
    }

    /**
     * Gets a list of a string split;
     *
     * @param str       Stirng to split
     * @param wrapWidth Wrap length
     * @return List of the split String
     */
    public List<String> getSplitString(String str, int wrapWidth) {
        return fontRenderer.listFormattedStringToWidth(str, wrapWidth);
    }

    /**
     * Draws a player head
     *
     * @param x startX
     * @param y startY
     */
    public void drawPlayerHead(int x, int y) {
        ResourceLocation playerSkin = ClientHelper.player().getLocationSkin();
        mc.getTextureManager().bindTexture(playerSkin);

        int[][] savedGLState = OpenGLHelper.saveGLState(new int[]{GL11.GL_ALPHA_TEST, GL11.GL_LIGHTING});
        GL11.glPushMatrix();
        GL11.glScalef(1.0F, 0.5F, 1.0F);

        this.drawTexturedModalRect(x, y, 32, 64, 32, 64);
        this.drawTexturedModalRect(x, y, 160, 64, 32, 64);

        GL11.glPopMatrix();
        OpenGLHelper.restoreGLState(savedGLState);
    }

    /**
     * Draws pixels
     *
     * @param x      startX
     * @param y      startY
     * @param u      u
     * @param v      x
     * @param width  width
     * @param height height
     * @param times  how many times should the pixels be drawn?
     */
    private void drawPixels(int x, int y, int u, int v, int width, int height, int times) {
        for (int cu = u; cu < u + width; cu++) {
            for (int cv = v; cv < v + height; cv++) {
                for (int factor = 0; factor < times; factor++) {
                    drawTexturedModalRect(x + (cu * 3) + factor, y + (cv * 3) + factor, cu + u, cv + v, 1, 1);
                }
            }
        }
    }

    /**
     * Draws an image from a ResourceLocation
     *
     * @param resource ResourceLocation to draw
     * @param x        startX
     * @param y        startY
     * @param u        textureY
     * @param v        textureV
     * @param w        width
     * @param h        height
     */
    public void drawResource(ResourceLocation resource, int x, int y, int u, int v, int w, int h) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(resource);
        drawTexturedModalRect(x, y, u, v, w, h);
    }

    //Below this line...you guessed it, stolen from TiCon
	public static void renderGuiTank(FluidStack fluid, int capacity, int amount, double x, double y, double width,
			double height) {
		GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
		if(fluid == null || fluid.getFluid() == null || fluid.amount <= 0) {
			return;
		}
	
		TextureAtlasSprite icon = getStillTexture(fluid);
		if(icon == null) {
			return;
		}
	
		int renderAmount = (int) Math.max(Math.min(height, amount * height / capacity), 1);
		int posY = (int) (y + height - renderAmount);
	
		bindBlockTexture();
		int color = fluid.getFluid().getColor(fluid);
		GL11.glColor3ub((byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF));
		GlStateManager.enableBlend();
		for(int i = 0; i < width; i += 16) {
			for(int j = 0; j < renderAmount; j += 16) {
				int drawWidth = (int) Math.min(width - i, 16);
				int drawHeight = Math.min(renderAmount - j, 16);
	
				int drawX = (int) (x + i);
				int drawY = posY + j;
	
				double minU = icon.getMinU();
				double maxU = icon.getMaxU();
				double minV = icon.getMinV();
				double maxV = icon.getMaxV();
	
				Tessellator tessellator = Tessellator.getInstance();
				BufferBuilder tes = tessellator.getBuffer();
				tes.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
				tes.pos(drawX, drawY + drawHeight, 0).tex(minU, minV + (maxV - minV) * drawHeight / 16F).endVertex();
				tes.pos(drawX + drawWidth, drawY + drawHeight, 0)
						.tex(minU + (maxU - minU) * drawWidth / 16F, minV + (maxV - minV) * drawHeight / 16F)
						.endVertex();
				tes.pos(drawX + drawWidth, drawY, 0).tex(minU + (maxU - minU) * drawWidth / 16F, minV).endVertex();
				tes.pos(drawX, drawY, 0).tex(minU, minV).endVertex();
				tessellator.draw();
			}
		}
		GlStateManager.disableBlend();
		GL11.glPopAttrib();
	}

	public static void renderGuiTank(FluidTank tank, double x, double y, double width, double height) {
		GuiHelper.renderGuiTank(tank.getFluid(), tank.getCapacity(), tank.getFluidAmount(), x, y, width, height);
	}

	public static TextureAtlasSprite getStillTexture(Fluid fluid) {
		ResourceLocation iconKey = fluid.getStill();
		if(iconKey == null) {
			return null;
		}
		return Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry(iconKey.toString());
	}

	public static TextureAtlasSprite getStillTexture(FluidStack fluid) {
		if(fluid == null || fluid.getFluid() == null) {
			return null;
		}
		return GuiHelper.getStillTexture(fluid.getFluid());
	}

	public static void bindBlockTexture() {
		engine().bindTexture(BLOCK_TEX);
	}

	public static TextureManager engine() {
		return Minecraft.getMinecraft().renderEngine;
	}
}
