package com.teamacronymcoders.base.guicomponentsystem.gui;

import com.teamacronymcoders.base.Reference;
import com.teamacronymcoders.base.guicomponentsystem.container.ContainerComponentBase;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiComponentBase extends GuiContainer {
    private final ResourceLocation DEFAULT_BACKGROUND = new ResourceLocation(Reference.MODID, "textures/gui/empty_background.png");
    private final ContainerComponentBase containerComponentBase;

    public GuiComponentBase(ContainerComponentBase container) {
        super(container);

        this.containerComponentBase = container;
        this.containerComponentBase.init();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(INVENTORY_BACKGROUND);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
    }
}
