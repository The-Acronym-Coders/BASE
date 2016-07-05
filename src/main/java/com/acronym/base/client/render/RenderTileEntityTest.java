package com.acronym.base.client.render;

import com.acronym.base.Base;
import com.acronym.base.blocks.BlockTest;
import com.acronym.base.util.RenderingUtils;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.text.TextComponentString;
import org.lwjgl.opengl.GL11;

/** Created by Jared on 7/1/2016 */
public class RenderTileEntityTest extends TileEntitySpecialRenderer<BlockTest.TileEntityTest> {

    @Override
    public void renderTileEntityAt(BlockTest.TileEntityTest te, double x, double y, double z, float partialTicks, int destroyStage) {
        super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);
        this.func_190053_a(true);
        this.func_190052_a(te, new TextComponentString("test").getText(), x, y, z, 12);
        this.func_190053_a(false);
        GL11.glTranslated(x-0.04, y + 1, z+0.04);
        RenderingUtils.renderBeamAt(Base.proxy.getClientPlayer(), 0.08, 0, 0.08, 0.08, 5, 0.08, 0, 0, 0);
        GL11.glTranslated(-x, -y - 1, -z);
    }
}
