package com.teamacronymcoders.base.guisystem;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.guisystem.network.PacketHandlerOpenGui;
import com.teamacronymcoders.base.guisystem.network.PacketOpenGui;
import com.teamacronymcoders.base.guisystem.target.GuiBlockTarget;
import com.teamacronymcoders.base.guisystem.target.GuiEntityTarget;
import com.teamacronymcoders.base.guisystem.target.GuiItemTarget;
import com.teamacronymcoders.base.guisystem.target.GuiTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class GuiHandler {
    private IBaseMod mod;
    private Map<ResourceLocation, GuiTarget> guiTargetRegistry;

    public GuiHandler(IBaseMod mod) {
        this.mod = mod;
        this.guiTargetRegistry = new HashMap<>();
        this.registerGuiTargetType(new GuiBlockTarget());
        this.registerGuiTargetType(new GuiEntityTarget());
        this.registerGuiTargetType(new GuiItemTarget());
        mod.getPacketHandler().registerPacket(PacketHandlerOpenGui.class, PacketOpenGui.class, Side.CLIENT);
    }

    /*
     * Called similar to EntityPlayer#openGui but allows for passing a context NBTTag in
     * @param guiTarget the target for which to open the GUI;
     * @param context Any data items for which you want to know when opening the guisystem
     */
    public void openGui(@Nonnull GuiTarget guiTarget, @Nonnull NBTTagCompound context, boolean openGuiFromServer,
                        EntityPlayer entityPlayer, World world) {
        //TODO: Network stuff
        this.mod.getLibProxy().openGui(guiTarget, context, openGuiFromServer, entityPlayer, world);
    }


    /*
     *
     * @param guiTarget An empty instance of a GuiTarget that can be used to deserialize GuiTargets
     */
    public void registerGuiTargetType(GuiTarget guiTarget) {
        if(!guiTargetRegistry.containsKey(guiTarget.getTargetType())) {
            guiTargetRegistry.put(guiTarget.getTargetType(), guiTarget);
        } else {
            mod.getLogger().error("GUI Target Type: " + guiTarget.getTargetType() + " has a non-unique name");
        }
    }
}
