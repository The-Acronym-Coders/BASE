package com.teamacronymcoders.base.util;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;

import com.teamacronymcoders.base.client.ClientHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.*;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
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
        mc.getRenderManager().renderEntity(item, 0, 0, 0, 0, 0, false);
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
     * @param vertex    BufferBuilder
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
    private static void renderTranslucent(BufferBuilder vertex, double startX, double startY, double startZ, double endX, double endY, double endZ, double rotationX, double rotationY, double rotationZ) {
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
     * @param vertex BufferBuilder
     * @param startX startX
     * @param startY startY
     * @param startZ startZ
     * @param endX   endX
     * @param endY   endY
     * @param endZ   endZ
     */
    private static void drawTranslucent(BufferBuilder vertex, double startX, double startY, double startZ, double endX, double endY, double endZ) {
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
        BufferBuilder vertex = tessellator.getBuffer();
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
        int existed = ClientHelper.player().ticksExisted;
        float alpha = 0.3F + MathHelper.sin((float) (existed + x)) * 0.3F + 0.3F;

        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buff = tess.getBuffer();

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

        int count = ClientHelper.player().ticksExisted;
        float alpha = 0.3F + MathHelper.sin((float) (count + x)) * 0.3F + 0.3F;

        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buff = tess.getBuffer();

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
        BufferBuilder buff = tess.getBuffer();

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
        BufferBuilder buff = tess.getBuffer();

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
        int count = ClientHelper.player().ticksExisted;
        float alpha = fadeSpeed + MathHelper.sin((float) (count + x)) * 0.3F + 0.3F;

        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buff = tess.getBuffer();

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
        int existed = ClientHelper.player().ticksExisted;
        float alpha = 0.3F + MathHelper.sin((float) (existed + x)) * 0.3F + 0.3F;

        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buff = tess.getBuffer();

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
     *
     * @param x         startX
     * @param y         startY
     * @param z         startZ
     * @param width     width
     * @param height    height
     * @param red       redColour
     * @param green     greenColour
     * @param blue      blueColour
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
     *
     * @param x         startX
     * @param y         startY
     * @param z         startZ
     * @param width     width
     * @param height    height
     * @param red       redColour
     * @param green     greenColour
     * @param blue      blueColour
     * @param lineWidth lineWidth
     * @param alpha     alpha
     */
    public static void drawRectNoFade(double x, double y, double z, double width, double height, float red, float green, float blue, float lineWidth, float alpha) {
        drawLineNoFade(x, y, z, x + width, y, z, red, green, blue, lineWidth, alpha);
        drawLineNoFade(x + width, y, z, x + width, y + width, z, red, green, blue, lineWidth, alpha);
        drawLineNoFade(x + width, y + width, z, x, y + width, z, red, green, blue, lineWidth, alpha);
        drawLineNoFade(x, y + width, z, x, y, z, red, green, blue, lineWidth, alpha);
    }

    public static float FLUID_OFFSET = 0.005f;

	/**
	 * Renders a fluid block, call from TESR. x/y/z is the rendering offset.
	 *
	 * @param fluid
	 *            Fluid to render
	 * @param pos
	 *            BlockPos where the Block is rendered. Used for brightness.
	 * @param x
	 *            Rendering offset. TESR x parameter.
	 * @param y
	 *            Rendering offset. TESR x parameter.
	 * @param z
	 *            Rendering offset. TESR x parameter.
	 * @param w
	 *            Width. 1 = full X-Width
	 * @param h
	 *            Height. 1 = full Y-Height
	 * @param d
	 *            Depth. 1 = full Z-Depth
	 */
	public static void renderFluidCuboid(FluidStack fluid, BlockPos pos, double x, double y, double z, double w,
			double h, double d) {
		double wd = (1d - w) / 2d;
		double hd = (1d - h) / 2d;
		double dd = (1d - d) / 2d;

		renderFluidCuboid(fluid, pos, x, y, z, wd, hd, dd, 1d - wd, 1d - hd, 1d - dd);
	}

	public static void renderFluidCuboid(FluidStack fluid, BlockPos pos, double x, double y, double z, double x1,
			double y1, double z1, double x2, double y2, double z2) {
		int color = fluid.getFluid().getColor(fluid);
		renderFluidCuboid(fluid, pos, x, y, z, x1, y1, z1, x2, y2, z2, color);
	}

	/**
	 * Renders block with offset x/y/z from x1/y1/z1 to x2/y2/z2 inside the block
	 * local coordinates, so from 0-1
	 */
	public static void renderFluidCuboid(FluidStack fluid, BlockPos pos, double x, double y, double z, double x1,
			double y1, double z1, double x2, double y2, double z2, int color) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder renderer = tessellator.getBuffer();
		renderer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
		mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		// RenderUtil.setColorRGBA(color);
		int brightness = mc.world.getCombinedLight(pos, fluid.getFluid().getLuminosity());

		pre(x, y, z);

		TextureAtlasSprite still = mc.getTextureMapBlocks()
				.getTextureExtry(fluid.getFluid().getStill(fluid).toString());
		TextureAtlasSprite flowing = mc.getTextureMapBlocks()
				.getTextureExtry(fluid.getFluid().getFlowing(fluid).toString());

		// x/y/z2 - x/y/z1 is because we need the width/height/depth
		putTexturedQuad(renderer, still, x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.DOWN, color, brightness,
				false);
		putTexturedQuad(renderer, flowing, x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.NORTH, color, brightness,
				true);
		putTexturedQuad(renderer, flowing, x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.EAST, color, brightness,
				true);
		putTexturedQuad(renderer, flowing, x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.SOUTH, color, brightness,
				true);
		putTexturedQuad(renderer, flowing, x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.WEST, color, brightness,
				true);
		putTexturedQuad(renderer, still, x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.UP, color, brightness,
				false);

		tessellator.draw();

		post();
	}

	public static void renderStackedFluidCuboid(FluidStack fluid, double px, double py, double pz, BlockPos pos,
			BlockPos from, BlockPos to, double ymin, double ymax) {
		renderStackedFluidCuboid(fluid, px, py, pz, pos, from, to, ymin, ymax, FLUID_OFFSET);
	}

	public static void renderStackedFluidCuboid(FluidStack fluid, double px, double py, double pz, BlockPos pos,
			BlockPos from, BlockPos to, double ymin, double ymax, float offsetToBlockEdge) {
		if(ymin >= ymax) {
			return;
		}
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder renderer = tessellator.getBuffer();
		renderer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
		mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		int color = fluid.getFluid().getColor(fluid);
		int brightness = mc.world.getCombinedLight(pos, fluid.getFluid().getLuminosity());

		pre(px, py, pz);
		GlStateManager.translate(from.getX(), from.getY(), from.getZ());

		TextureAtlasSprite still = mc.getTextureMapBlocks()
				.getTextureExtry(fluid.getFluid().getStill(fluid).toString());
		TextureAtlasSprite flowing = mc.getTextureMapBlocks()
				.getTextureExtry(fluid.getFluid().getFlowing(fluid).toString());

		if(still == null) {
			still = mc.getTextureMapBlocks().getMissingSprite();
		}
		if(flowing == null) {
			flowing = mc.getTextureMapBlocks().getMissingSprite();
		}

		int xd = to.getX() - from.getX();

		// the liquid can stretch over more blocks than the subtracted height is if
		// ymin's decimal is bigger than ymax's
		// decimal (causing UV over 1)
		// ignoring the decimals prevents this, as yd then equals exactly how many ints
		// are between the two
		// for example, if ymax = 5.1 and ymin = 2.3, 2.8 (which rounds to 2), with the
		// face array becoming 2.3, 3, 4,
		// 5.1
		int yminInt = (int) ymin;
		int yd = (int) (ymax - yminInt);

		// prevents a rare case of rendering the top face multiple times if ymax is
		// perfectly aligned with the block
		// for example, if ymax = 3 and ymin = 1, the values of the face array become 1,
		// 2, 3, 3 as we then have middle
		// ints
		if(ymax % 1d == 0) {
			yd--;
		}
		int zd = to.getZ() - from.getZ();

		double xmin = offsetToBlockEdge;
		double xmax = xd + 1d - offsetToBlockEdge;
		// double ymin = y1;
		// double ymax = y2;
		double zmin = offsetToBlockEdge;
		double zmax = zd + 1d - offsetToBlockEdge;

		double[] xs = new double[2 + xd];
		double[] ys = new double[2 + yd];
		double[] zs = new double[2 + zd];

		xs[0] = xmin;
		for(int i = 1; i <= xd; i++) {
			xs[i] = i;
		}
		xs[xd + 1] = xmax;

		// we have to add the whole number for ymin or otherwise things render
		// incorrectly if above the first block
		// example, heights of 2 and 5 would produce array of 2, 1, 2, 5
		ys[0] = ymin;
		for(int i = 1; i <= yd; i++) {
			ys[i] = i + yminInt;
		}
		ys[yd + 1] = ymax;

		zs[0] = zmin;
		for(int i = 1; i <= zd; i++) {
			zs[i] = i;
		}
		zs[zd + 1] = zmax;

		// render each side
		for(int y = 0; y <= yd; y++) {
			for(int z = 0; z <= zd; z++) {
				for(int x = 0; x <= xd; x++) {

					double x1 = xs[x];
					double x2 = xs[x + 1] - x1;
					double y1 = ys[y];
					double y2 = ys[y + 1] - y1;
					double z1 = zs[z];
					double z2 = zs[z + 1] - z1;

					if(x == 0) {
						putTexturedQuad(renderer, flowing, x1, y1, z1, x2, y2, z2, EnumFacing.WEST, color, brightness,
								true);
					}
					if(x == xd) {
						putTexturedQuad(renderer, flowing, x1, y1, z1, x2, y2, z2, EnumFacing.EAST, color, brightness,
								true);
					}
					if(y == 0) {
						putTexturedQuad(renderer, still, x1, y1, z1, x2, y2, z2, EnumFacing.DOWN, color, brightness,
								false);
					}
					if(y == yd) {
						putTexturedQuad(renderer, still, x1, y1, z1, x2, y2, z2, EnumFacing.UP, color, brightness,
								false);
					}
					if(z == 0) {
						putTexturedQuad(renderer, flowing, x1, y1, z1, x2, y2, z2, EnumFacing.NORTH, color, brightness,
								true);
					}
					if(z == zd) {
						putTexturedQuad(renderer, flowing, x1, y1, z1, x2, y2, z2, EnumFacing.SOUTH, color, brightness,
								true);
					}
				}
			}
		}

		// putTexturedQuad(renderer, still, x1, y1, z1, x2-x1, y2-y1, z2-z1,
		// EnumFacing.DOWN, color, brightness);
		// putTexturedQuad(renderer, flowing, x1, y1, z1, x2-x1, y2-y1, z2-z1,
		// EnumFacing.NORTH, color, brightness);
		// putTexturedQuad(renderer, flowing, x1, y1, z1, x2-x1, y2-y1, z2-z1,
		// EnumFacing.EAST, color, brightness);
		// putTexturedQuad(renderer, flowing, x1, y1, z1, x2-x1, y2-y1, z2-z1,
		// EnumFacing.SOUTH, color, brightness);
		// putTexturedQuad(renderer, flowing, x1, y1, z1, x2-x1, y2-y1, z2-z1,
		// EnumFacing.WEST, color, brightness);
		// putTexturedQuad(renderer, still , x1, y1, z1, x2-x1, y2-y1, z2-z1,
		// EnumFacing.UP, color, brightness);

		tessellator.draw();

		post();
	}

	public static void putTexturedCuboid(BufferBuilder renderer, ResourceLocation location, double x1, double y1,
			double z1, double x2, double y2, double z2, int color, int brightness) {
		boolean flowing = false;
		TextureAtlasSprite sprite = mc.getTextureMapBlocks().getTextureExtry(location.toString());
		putTexturedQuad(renderer, sprite, x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.DOWN, color, brightness,
				flowing);
		putTexturedQuad(renderer, sprite, x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.NORTH, color, brightness,
				flowing);
		putTexturedQuad(renderer, sprite, x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.EAST, color, brightness,
				flowing);
		putTexturedQuad(renderer, sprite, x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.SOUTH, color, brightness,
				flowing);
		putTexturedQuad(renderer, sprite, x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.WEST, color, brightness,
				flowing);
		putTexturedQuad(renderer, sprite, x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.UP, color, brightness,
				flowing);
	}

	public static void putTexturedQuad(BufferBuilder renderer, TextureAtlasSprite sprite, double x, double y, double z,
			double w, double h, double d, EnumFacing face, int color, int brightness, boolean flowing) {
		int l1 = brightness >> 0x10 & 0xFFFF;
		int l2 = brightness & 0xFFFF;

		int a = color >> 24 & 0xFF;
		int r = color >> 16 & 0xFF;
		int g = color >> 8 & 0xFF;
		int b = color & 0xFF;

		putTexturedQuad(renderer, sprite, x, y, z, w, h, d, face, r, g, b, a, l1, l2, flowing);
	}

	// x and x+w has to be within [0,1], same for y/h and z/d
	public static void putTexturedQuad(BufferBuilder renderer, TextureAtlasSprite sprite, double x, double y, double z,
			double w, double h, double d, EnumFacing face, int r, int g, int b, int a, int light1, int light2,
			boolean flowing) {
		// safety
		if(sprite == null) {
			return;
		}
		double minU;
		double maxU;
		double minV;
		double maxV;

		double size = 16f;
		if(flowing) {
			size = 8f;
		}

		double x1 = x;
		double x2 = x + w;
		double y1 = y;
		double y2 = y + h;
		double z1 = z;
		double z2 = z + d;

		double xt1 = x1 % 1d;
		double xt2 = xt1 + w;
		while(xt2 > 1f) {
			xt2 -= 1f;
		}
		double yt1 = y1 % 1d;
		double yt2 = yt1 + h;
		while(yt2 > 1f) {
			yt2 -= 1f;
		}
		double zt1 = z1 % 1d;
		double zt2 = zt1 + d;
		while(zt2 > 1f) {
			zt2 -= 1f;
		}

		// flowing stuff should start from the bottom, not from the start
		if(flowing) {
			double tmp = 1d - yt1;
			yt1 = 1d - yt2;
			yt2 = tmp;
		}

		switch(face) {
			case DOWN:
			case UP:
				minU = sprite.getInterpolatedU(xt1 * size);
				maxU = sprite.getInterpolatedU(xt2 * size);
				minV = sprite.getInterpolatedV(zt1 * size);
				maxV = sprite.getInterpolatedV(zt2 * size);
				break;
			case NORTH:
			case SOUTH:
				minU = sprite.getInterpolatedU(xt2 * size);
				maxU = sprite.getInterpolatedU(xt1 * size);
				minV = sprite.getInterpolatedV(yt1 * size);
				maxV = sprite.getInterpolatedV(yt2 * size);
				break;
			case WEST:
			case EAST:
				minU = sprite.getInterpolatedU(zt2 * size);
				maxU = sprite.getInterpolatedU(zt1 * size);
				minV = sprite.getInterpolatedV(yt1 * size);
				maxV = sprite.getInterpolatedV(yt2 * size);
				break;
			default:
				minU = sprite.getMinU();
				maxU = sprite.getMaxU();
				minV = sprite.getMinV();
				maxV = sprite.getMaxV();
		}

		switch(face) {
			case DOWN:
				renderer.pos(x1, y1, z1).color(r, g, b, a).tex(minU, minV).lightmap(light1, light2).endVertex();
				renderer.pos(x2, y1, z1).color(r, g, b, a).tex(maxU, minV).lightmap(light1, light2).endVertex();
				renderer.pos(x2, y1, z2).color(r, g, b, a).tex(maxU, maxV).lightmap(light1, light2).endVertex();
				renderer.pos(x1, y1, z2).color(r, g, b, a).tex(minU, maxV).lightmap(light1, light2).endVertex();
				break;
			case UP:
				renderer.pos(x1, y2, z1).color(r, g, b, a).tex(minU, minV).lightmap(light1, light2).endVertex();
				renderer.pos(x1, y2, z2).color(r, g, b, a).tex(minU, maxV).lightmap(light1, light2).endVertex();
				renderer.pos(x2, y2, z2).color(r, g, b, a).tex(maxU, maxV).lightmap(light1, light2).endVertex();
				renderer.pos(x2, y2, z1).color(r, g, b, a).tex(maxU, minV).lightmap(light1, light2).endVertex();
				break;
			case NORTH:
				renderer.pos(x1, y1, z1).color(r, g, b, a).tex(minU, maxV).lightmap(light1, light2).endVertex();
				renderer.pos(x1, y2, z1).color(r, g, b, a).tex(minU, minV).lightmap(light1, light2).endVertex();
				renderer.pos(x2, y2, z1).color(r, g, b, a).tex(maxU, minV).lightmap(light1, light2).endVertex();
				renderer.pos(x2, y1, z1).color(r, g, b, a).tex(maxU, maxV).lightmap(light1, light2).endVertex();
				break;
			case SOUTH:
				renderer.pos(x1, y1, z2).color(r, g, b, a).tex(maxU, maxV).lightmap(light1, light2).endVertex();
				renderer.pos(x2, y1, z2).color(r, g, b, a).tex(minU, maxV).lightmap(light1, light2).endVertex();
				renderer.pos(x2, y2, z2).color(r, g, b, a).tex(minU, minV).lightmap(light1, light2).endVertex();
				renderer.pos(x1, y2, z2).color(r, g, b, a).tex(maxU, minV).lightmap(light1, light2).endVertex();
				break;
			case WEST:
				renderer.pos(x1, y1, z1).color(r, g, b, a).tex(maxU, maxV).lightmap(light1, light2).endVertex();
				renderer.pos(x1, y1, z2).color(r, g, b, a).tex(minU, maxV).lightmap(light1, light2).endVertex();
				renderer.pos(x1, y2, z2).color(r, g, b, a).tex(minU, minV).lightmap(light1, light2).endVertex();
				renderer.pos(x1, y2, z1).color(r, g, b, a).tex(maxU, minV).lightmap(light1, light2).endVertex();
				break;
			case EAST:
				renderer.pos(x2, y1, z1).color(r, g, b, a).tex(minU, maxV).lightmap(light1, light2).endVertex();
				renderer.pos(x2, y2, z1).color(r, g, b, a).tex(minU, minV).lightmap(light1, light2).endVertex();
				renderer.pos(x2, y2, z2).color(r, g, b, a).tex(maxU, minV).lightmap(light1, light2).endVertex();
				renderer.pos(x2, y1, z2).color(r, g, b, a).tex(maxU, maxV).lightmap(light1, light2).endVertex();
				break;
		}
	}

	public static void pre(double x, double y, double z) {
		GlStateManager.pushMatrix();

		RenderHelper.disableStandardItemLighting();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		if(Minecraft.isAmbientOcclusionEnabled()) {
			GL11.glShadeModel(GL11.GL_SMOOTH);
		}
		else {
			GL11.glShadeModel(GL11.GL_FLAT);
		}

		GlStateManager.translate(x, y, z);
	}

	public static void post() {
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
		RenderHelper.enableStandardItemLighting();
	}

	public static void setColorRGB(int color) {
		setColorRGBA(color | 0xff000000);
	}

	public static void setColorRGBA(int color) {
		float a = alpha(color) / 255.0F;
		float r = red(color) / 255.0F;
		float g = green(color) / 255.0F;
		float b = blue(color) / 255.0F;

		GlStateManager.color(r, g, b, a);
	}

	public static void setBrightness(BufferBuilder renderer, int brightness) {
		renderer.putBrightness4(brightness, brightness, brightness, brightness);
	}

	public static int compose(int r, int g, int b, int a) {
		int rgb = a;
		rgb = (rgb << 8) + r;
		rgb = (rgb << 8) + g;
		rgb = (rgb << 8) + b;
		return rgb;
	}

	public static int alpha(int c) {
		return (c >> 24) & 0xFF;
	}

	public static int red(int c) {
		return (c >> 16) & 0xFF;
	}

	public static int green(int c) {
		return (c >> 8) & 0xFF;
	}

	public static int blue(int c) {
		return (c) & 0xFF;
	}

	// TODO This is a really really awful name. And probably a stupid method anyway.
	public static float[] directionalVelocitiesOfMagnitude(Vec3i vector, float magnitude) {
		float x = 0, y = 0, z = 0;
		if(vector.getX() != 0) {
			x = magnitude;
			if(vector.getX() < 0) {
				x = -x;
			}
		}
		if(vector.getY() != 0) {
			y = magnitude;
			if(vector.getY() < 0) {
				y = -y;
			}
		}
		if(vector.getZ() != 0) {
			z = magnitude;
			if(vector.getZ() < 0) {
				z = -z;
			}
		}
		return new float[] { x, y, z };
	}
}
