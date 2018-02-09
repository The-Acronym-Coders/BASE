package com.teamacronymcoders.base.modularguisystem.components;

import com.teamacronymcoders.base.modularguisystem.container.ContainerModular;
import com.teamacronymcoders.base.modularguisystem.gui.GuiModular;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IModularGuiComponent {
    @SideOnly(Side.CLIENT)
    void addToGui(GuiModular guiModular);

    void addToContainer(ContainerModular containerModular);
}
