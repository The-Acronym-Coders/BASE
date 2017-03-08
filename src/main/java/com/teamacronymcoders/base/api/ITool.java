package com.teamacronymcoders.base.api;

import net.minecraft.nbt.NBTTagCompound;

public interface ITool {

    void deserializeNBT(NBTTagCompound nbt);

    NBTTagCompound serializeNBT();

}
