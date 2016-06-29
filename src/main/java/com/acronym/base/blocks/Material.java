package com.acronym.base.blocks;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Material {

	private String name;
	private Color color;
	private EnumOreType[] types;
	private List<EnumOreType> typeList = new ArrayList<>();

	public Material(String name, Color color,EnumOreType... types) {
		this.name=name;
		this.color=color;
		this.types=types;
		if(types!=null)
			for (EnumOreType type : types)
				this.typeList.add(type);
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public EnumOreType[] getTypes() {
		return types;
	}

	public boolean isTypeSet(EnumOreType type) {
		return this.typeList.contains(type);
	}

	public enum EnumOreType {
		NUGGET,
		DUST,
		INGOT,
		BLOCK,
		ORE,
		GEAR,
		FLUID,
		PLATE,
	}
}
