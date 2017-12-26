package com.teamacronymcoders.base.util;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class CapabilityUtils {
    public static <T> Optional<T> getCapability(@Nonnull ICapabilityProvider provider, @Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        T instance = null;
        if (provider.hasCapability(capability, facing)) {
            instance = provider.getCapability(capability, facing);
        }
        return Optional.ofNullable(instance);
    }

    public static <T> Optional<T> getCapability(@Nonnull ICapabilityProvider provider,@Nonnull Capability<T> capability) {
        return getCapability(provider, capability, null);
    }
}
