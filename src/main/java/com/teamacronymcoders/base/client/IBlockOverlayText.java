package com.teamacronymcoders.base.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author IE Team
 */
public interface IBlockOverlayText {
    @SideOnly(Side.CLIENT)
    String[] getOverlayText(EntityPlayer player, RayTraceResult rayTraceResult, boolean tool);
}
