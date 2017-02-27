package com.teamacronymcoders.base.blocks.properties;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;
import java.util.Locale;

public enum SideType implements IStringSerializable {
    INPUT, OUTPUT, NONE;

    @Nonnull
    public String getName() {
        return this.name().toLowerCase(Locale.US);
    }

    public SideType next() {
        int next = ordinal() + 1;
        if(next > SideType.values().length) {
            next = 0;
        }
        return SideType.values()[next];
    }
}
