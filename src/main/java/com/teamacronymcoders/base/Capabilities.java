package com.teamacronymcoders.base;

import com.teamacronymcoders.base.api.capabilities.ITool;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class Capabilities {
    @CapabilityInject(ITool.class)
    public static Capability<ITool> TOOL;

    public static void register() {
        CapabilityManager.INSTANCE.register(ITool.class, new Capability.IStorage<ITool>() {
            @Override
            public CompoundNBT writeNBT(Capability<ITool> capability, ITool instance, Direction side) {
                return new CompoundNBT();
            }

            @Override
            public void readNBT(Capability<ITool> capability, ITool instance, Direction side, INBT nbt) {

            }
        }, () -> new ITool() {});
    }
}
