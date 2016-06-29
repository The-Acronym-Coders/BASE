package com.acronym.base.integration;

import com.acronym.base.integration.base.Base;
import com.acronym.base.integration.chisel.Chisel;
import com.acronym.base.integration.tesla.Tesla;
import net.minecraftforge.fml.common.Loader;

public enum IntegrationIDs {
	TESLA("Tesla",Tesla.class,EnumModType.INTEGRATION),
	BASE("base",Base.class,EnumModType.REQUIRED),
	CHISEL("chisel", Chisel.class,EnumModType.INTEGRATION),
	;

	private final String modid;
	private final Class<? extends IIntegration> clazz;
	private final EnumModType modType;

	IntegrationIDs(String modid, Class<? extends IIntegration> clazz, EnumModType type) {
		this.modid = modid;
		this.clazz = clazz;
		this.modType=type;
	}

	public boolean isLoaded() {
		return Loader.isModLoaded(this.modid);
	}

	public Class<? extends IIntegration> getIIntegration() {
		return clazz;
	}

	public String getModid() {
		return modid;
	}

	public boolean isRequired() {
		return this.modType==EnumModType.REQUIRED;
	}

	public EnumModType getModType() {
		return modType;
	}
}
