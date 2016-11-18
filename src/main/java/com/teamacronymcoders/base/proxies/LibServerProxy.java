package com.teamacronymcoders.base.proxies;

import com.teamacronymcoders.base.guisystem.IHasGui;
import com.teamacronymcoders.base.guisystem.target.GuiTarget;
import com.teamacronymcoders.base.modulesystem.IModule;
import com.teamacronymcoders.base.modulesystem.proxies.IModuleProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;

import javax.annotation.Nonnull;

public class LibServerProxy extends LibCommonProxy {
    @Override
    public void openGui(@Nonnull GuiTarget guiTarget, @Nonnull NBTTagCompound context, boolean openGuiFromServer,
                        EntityPlayer entityPlayer, World world) {
        if(entityPlayer instanceof EntityPlayerMP && guiTarget.getTarget() instanceof IHasGui) {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP)entityPlayer;
            entityPlayerMP.getNextWindowId();
            entityPlayerMP.closeContainer();
            int windowId = entityPlayerMP.currentWindowId;
            entityPlayerMP.openContainer = ((IHasGui) guiTarget.getTarget()).getContainer(entityPlayer, world, context);
            entityPlayerMP.openContainer.windowId = windowId;
            entityPlayerMP.openContainer.addListener(entityPlayerMP);
            MinecraftForge.EVENT_BUS.post(new PlayerContainerEvent.Open(entityPlayer, entityPlayer.openContainer));
        }
    }

    @Override
    public IModuleProxy getModuleProxy(IModule module) {
        return getModuleProxy(module.getServerProxyPath());
    }
}
