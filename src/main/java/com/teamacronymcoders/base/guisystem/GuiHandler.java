package com.teamacronymcoders.base.guisystem;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.guisystem.network.PacketHandlerOpenGui;
import com.teamacronymcoders.base.guisystem.network.PacketOpenGui;
import com.teamacronymcoders.base.guisystem.target.GuiTarget;
import com.teamacronymcoders.base.guisystem.target.GuiTargetBase;
import com.teamacronymcoders.base.util.ClassLoading;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class GuiHandler {
    private IBaseMod mod;
    private Map<String, GuiTargetBase> guiTargetRegistry;

    public GuiHandler(IBaseMod mod, ASMDataTable asmDataTable) {
        this.mod = mod;
        this.guiTargetRegistry = new HashMap<>();
        this.getGuiTargets(asmDataTable);
        mod.getPacketHandler().registerPacket(PacketHandlerOpenGui.class, PacketOpenGui.class, Side.CLIENT);
    }

    /*
     * Called similar to EntityPlayer#openGui but allows for passing a context NBTTag in
     * @param guiTarget the target for which to open the GUI;
     * @param context Any data items for which you want to know when opening the guisystem
     */
    public void openGui(@Nonnull GuiTargetBase guiTarget, @Nonnull NBTTagCompound context, boolean openGuiFromServer,
                        EntityPlayer entityPlayer, World world) {
        this.mod.getLibProxy().openGui(guiTarget, context, openGuiFromServer, entityPlayer, world);
    }

    private void getGuiTargets(ASMDataTable asmDataTable) {
        ClassLoading.getInstances(asmDataTable, GuiTarget.class, GuiTargetBase.class).forEach(guiTarget ->
                this.guiTargetRegistry.put(guiTarget.getName(), guiTarget));
    }

    public GuiTargetBase getGuiTarget(String name) {
        return guiTargetRegistry.get(name);
    }
}
