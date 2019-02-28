package com.teamacronymcoders.base.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;

/**
 * @author IE Team
 */
public interface IBlockOverlayText {
    String[] getOverlayText(EntityPlayer player, RayTraceResult rayTraceResult, boolean tool);
}
