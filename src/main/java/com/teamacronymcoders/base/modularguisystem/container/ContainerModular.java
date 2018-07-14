package com.teamacronymcoders.base.modularguisystem.container;

import com.teamacronymcoders.base.modularguisystem.guihost.IModularGuiHost;
import com.teamacronymcoders.base.modularguisystem.components.IModularGuiComponent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

import java.util.List;

public class ContainerModular extends Container {
    private IModularGuiHost guiHost;
    private List<IModularGuiComponent> components;

    public ContainerModular(IModularGuiHost guiHost, List<IModularGuiComponent> components) {
        this.guiHost = guiHost;
        this.components = components;
        this.components.forEach(component -> component.addToContainer(this));
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return guiHost.canInteractWith(player) && components.stream().allMatch(component -> component.canInteractWith(player));
    }

    public void addSlot(Slot slot) {
        super.addSlotToContainer(slot);
    }
}
