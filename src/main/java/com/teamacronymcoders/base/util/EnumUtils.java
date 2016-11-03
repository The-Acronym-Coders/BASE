package com.teamacronymcoders.base.util;

import java.util.Arrays;

public class EnumUtils {
    private EnumUtils() { }

    public static String[] getNames(Class<? extends Enum<?>> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
