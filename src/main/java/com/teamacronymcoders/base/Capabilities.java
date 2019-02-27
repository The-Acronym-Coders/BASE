package com.teamacronymcoders.base;

import com.teamacronymcoders.base.api.ITool;
import com.teamacronymcoders.base.api.ToolImpl;
import net.minecraft.nbt.INBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class Capabilities {
    @CapabilityInject(ITool.class)
    public static Capability<ITool> TOOL;

    public static void register() {
        CapabilityManager.INSTANCE.register(ITool.class, new Capability.IStorage<ITool>() {
            @Override
            public NBTTagCompound writeNBT(Capability<ITool> capability, ITool instance, EnumFacing side) {
                return new NBTTagCompound();
            }

            @Override
            public void readNBT(Capability<ITool> capability, ITool instance, EnumFacing side, INBTBase nbt) {

            }
        }, ToolImpl::new);
    }
}
