package com.teamacronymcoders.base.util;

import java.util.Locale;

public class TextUtils {
    private TextUtils() {

    }

    public static String removeSpecialCharacters(String input) {
        return input.replace(" ", "").replace("'", "").replace(":", "_");
    }

    public static String toSnakeCase(String input) {
        input = removeSpecialCharacters(input);
        input = input.substring(0, 1).toLowerCase(Locale.US) + input.substring(1);
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (Character.isUpperCase(currentChar)) {
                output.append("_");
            }
            output.append(Character.toLowerCase(currentChar));
        }

        return output.toString();
    }
}
