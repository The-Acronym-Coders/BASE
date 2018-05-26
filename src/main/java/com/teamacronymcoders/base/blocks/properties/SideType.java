package com.teamacronymcoders.base.blocks.properties;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;
import java.util.Locale;

public enum SideType implements IStringSerializable {
    INPUT, OUTPUT, NONE;

    public static final SideType[] VALUES = values();
    @Nonnull
    public String getName() {
        return this.name().toLowerCase(Locale.US);
    }

    public SideType next() {
        int next = ordinal() + 1;
        if(next >= VALUES.length) {
            next = 0;
        }
        return VALUES[next];
    }
}
