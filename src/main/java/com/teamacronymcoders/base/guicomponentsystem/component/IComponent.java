package com.teamacronymcoders.base.guicomponentsystem.component;

import com.teamacronymcoders.base.guicomponentsystem.container.ContainerComponentBase;
import net.minecraft.entity.player.EntityPlayer;

public interface IComponent {
    int getLeft();

    int getTop();

    int getWidth();

    int getHeight();

    void onAddedToContainer(EntityPlayer entityPlayer, ContainerComponentBase containerComponentBase);
}
