package com.teamacronymcoders.base.client;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;

/**
 * @author IE Team
 */
public interface IBlockOverlayText {
    String[] getOverlayText(PlayerEntity player, RayTraceResult rayTraceResult, boolean tool);
}
