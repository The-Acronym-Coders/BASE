package com.teamacronymcoders.base.modularguisystem.gui;

import com.teamacronymcoders.base.modularguisystem.container.ContainerModular;
import net.minecraft.client.gui.inventory.GuiContainer;

public class GuiModular extends GuiContainer {
    public GuiModular(ModularGuiBuilder modularGuiBuilder) {
        super(containerModular);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

    }
}
