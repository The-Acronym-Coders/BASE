package com.acronym.base.api.registries.helpers;

import net.minecraft.block.Block;

public class Wrenchable {
	private Block wrenchable;
	public Wrenchable(Block wrenchable) {
		this.wrenchable=wrenchable;
	}

	public Block getWrenchable() {
		return wrenchable;
	}
}
