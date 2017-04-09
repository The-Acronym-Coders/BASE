package com.teamacronymcoders.base.blocks.properties;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraftforge.common.property.IUnlistedProperty;

public class PropertySideType implements IUnlistedProperty<SideType> {
    final String name;

    public PropertySideType(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isValid(SideType value) {
        return true;
    }

    @Override
    public Class<SideType> getType() {
        return SideType.class;
    }

    @Override
    public String valueToString(SideType value) {
        return value.toString();
    }

    public static final PropertySideType[] SIDE_TYPE = {
            new PropertySideType("sidetype_down"),
            new PropertySideType("sidetype_up"),
            new PropertySideType("sidetype_north"),
            new PropertySideType("sidetype_south"),
            new PropertySideType("sidetype_west"),
            new PropertySideType("sidetype_east")
    };
}
