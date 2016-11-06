package com.teamacronymcoders.base.util;

import java.util.Arrays;

public class EnumUtils {
    private EnumUtils() { }

    public static String[] getNames(Class<? extends Enum<?>> enumClass) {
        String[] enumNames = Arrays.stream(enumClass.getEnumConstants()).map(Enum::name).toArray(String[]::new);
        for(int i = 0; i < enumNames.length; i++) {
            enumNames[i] = enumNames[i].toLowerCase();
        }
        return enumNames;
    }
}
