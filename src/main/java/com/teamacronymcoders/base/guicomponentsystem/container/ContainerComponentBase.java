package com.teamacronymcoders.base.guicomponentsystem.container;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.guicomponentsystem.IGuiComponentHost;
import com.teamacronymcoders.base.guicomponentsystem.component.IComponent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

import java.util.List;

public class ContainerComponentBase extends Container {
    private IGuiComponentHost host;
    private List<IComponent> components;
    private EntityPlayer entityPlayer;

    public ContainerComponentBase(IGuiComponentHost host, EntityPlayer entityPlayer) {
        super();
        this.host = host;
        this.components = Lists.newArrayList();
        this.entityPlayer = entityPlayer;
    }

    public void addComponent(IComponent component) {
        this.components.add(component);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return player.getDistanceSq(host.getLocation()) <= 64.0D;
    }

    public void addSlot(Slot slot) {
        this.addSlotToContainer(slot);
    }

    public void init() {
        components.forEach(component -> component.onAddedToContainer(entityPlayer, this));
    }
}
