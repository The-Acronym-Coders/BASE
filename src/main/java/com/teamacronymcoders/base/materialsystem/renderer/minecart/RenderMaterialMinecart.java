package com.teamacronymcoders.base.materialsystem.renderer.minecart;

import com.teamacronymcoders.base.materialsystem.entity.minecart.EntityMaterialMinecart;
import com.teamacronymcoders.base.renderer.entity.loader.IEntityRenderer;
import com.teamacronymcoders.base.renderer.entity.minecart.RenderMinecartBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.Optional;

@SideOnly(Side.CLIENT)
public class RenderMaterialMinecart extends RenderMinecartBase<EntityMaterialMinecart> implements IEntityRenderer {
    public RenderMaterialMinecart() {
        super(null);
    }

    public RenderMaterialMinecart(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    protected void renderCartModel(EntityMaterialMinecart entity) {
        Optional<Color> color = Optional.ofNullable(entity.getMaterialPart().getFullColor());
        GlStateManager.pushMatrix();
        color.ifPresent(value -> GlStateManager.color(value.getRed() / 255f, value.getGreen() / 255f, value.getBlue() / 255f));
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        modelMinecart.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GlStateManager.popMatrix();
    }

    @Override
    public Class getEntityClass() {
        return EntityMaterialMinecart.class;
    }

    @Override
    public IRenderFactory getRenderFactory() {
        return RenderMaterialMinecart::new;
    }
}
