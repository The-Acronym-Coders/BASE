package com.acronym.base.api.registries;

import com.acronym.base.api.registries.helpers.Wrenchable;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class WrenchRegistry {
	private static List<Wrenchable> wrenchableList = new ArrayList<>();

	public static boolean addWrenchable(Block block) {
		if(!wrenchableList.contains(block)) {
			wrenchableList.add(new Wrenchable(block));
			return true;
		}
		return false;
	}

	public static boolean isWrenchable(Block block) {
		return wrenchableList.contains(block);
	}

	public static List<Wrenchable> getWrenchableList() {
		return wrenchableList;
	}
}
