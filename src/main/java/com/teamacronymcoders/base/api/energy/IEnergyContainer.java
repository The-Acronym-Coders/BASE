package com.teamacronymcoders.base.api.energy;

import net.minecraft.util.EnumFacing;

public interface IEnergyContainer {
	BaseEnergyContainer getEnergyContainer(EnumFacing side);
}
