package com.teamacronymcoders.base.modularguisystem;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.Reference;
import com.teamacronymcoders.base.modularguisystem.components.IModularGuiComponent;
import com.teamacronymcoders.base.modularguisystem.container.ContainerModular;
import com.teamacronymcoders.base.modularguisystem.gui.GuiModular;
import com.teamacronymcoders.base.modularguisystem.guihost.IModularGuiHost;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class GuiBuilder {
    private List<IModularGuiComponent> components;
    private ResourceLocation backgroundLocation = new ResourceLocation(Reference.MODID, "textures/gui/blank_gui.png");

    public GuiBuilder() {
        components = Lists.newArrayList();
    }

    public void setBackgroundLocation(ResourceLocation backgroundLocation) {
        this.backgroundLocation = backgroundLocation;
    }

    public void addComponent(IModularGuiComponent component) {
        components.add(component);
    }

    public ContainerModular createContainer(IModularGuiHost guiHost) {
        return new ContainerModular(guiHost, new ArrayList<>(this.components));
    }

    @SideOnly(Side.CLIENT)
    public GuiModular createGui(IModularGuiHost guiHost) {
        return new GuiModular(this.createContainer(guiHost), this.backgroundLocation, new ArrayList<>(this.components));
    }
}
