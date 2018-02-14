package com.teamacronymcoders.base.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class CapUtils {
    @Nullable
    public static <T> T get(ICapabilityProvider provider, Capability<T> capability) {
        return get(provider, capability, null);
    }

    @Nullable
    public static <T> T get(ICapabilityProvider provider, Capability<T> capability, @Nullable EnumFacing facing) {
        return provider.hasCapability(capability, facing) ? provider.getCapability(capability, facing) : null;
    }
}
