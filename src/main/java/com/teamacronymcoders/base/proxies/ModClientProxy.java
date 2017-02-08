package com.teamacronymcoders.base.proxies;

import com.teamacronymcoders.base.Reference;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

@SideOnly(Side.CLIENT)
public class ModClientProxy extends ModCommonProxy {

    public World getClientWorld() {
        return FMLClientHandler.instance().getClient().theWorld;
    }

    public boolean isClient() {
        return true;
    }

    public boolean isServer() {
        return false;
    }

    public EntityPlayer getClientPlayer() {
        return FMLClientHandler.instance().getClient().thePlayer;
    }

    /**
     * Translates a message
     *
     * @param label   prefix
     * @param message message
     * @return Translated String
     */
    public String translateMessage(String label, String message) {
        if (Objects.equals(label, "")) return I18n.format(message);
        return I18n.format(String.format("%s.%s.%s", label, Reference.MODID, message));
    }
}
