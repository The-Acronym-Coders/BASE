package com.teamacronymcoders.base.materialsystem.partdata;

public class DataPartParsers {
    public static float getFloat(String value) {
        return Float.parseFloat(value);
    }

    public static Integer getInt(String value) {
        return Integer.parseInt(value);
    }

    public static String getString(String value) {
        return value;
    }
}
