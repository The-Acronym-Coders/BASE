package com.teamacronymcoders.base.modularguisystem.gui;

import com.teamacronymcoders.base.modularguisystem.components.IModularGuiComponent;
import com.teamacronymcoders.base.modularguisystem.container.ContainerModular;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class GuiModular extends GuiContainer {
    private ResourceLocation backgroundLocation;

    public GuiModular(ContainerModular modularContainer, ResourceLocation backgroundLocation, List<IModularGuiComponent> components) {
        super(modularContainer);
        this.backgroundLocation = backgroundLocation;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(this.backgroundLocation);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
    }
}
