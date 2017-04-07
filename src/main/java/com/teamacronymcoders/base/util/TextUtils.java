package com.teamacronymcoders.base.util;

public class TextUtils {
    private TextUtils() {

    }

    public static String removeSpecialCharacters(String string) {
        return string.replace(" ", "").replace("'", "");
    }
}
