package com.teamacronymcoders.base.api;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class BaseAPI {
	@CapabilityInject(ITool.class)
	public static final Capability<ITool> TOOL_CAPABILITY = null;
}
