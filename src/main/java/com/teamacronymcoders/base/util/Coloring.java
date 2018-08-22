package com.teamacronymcoders.base.util;

public class Coloring {
        private int color;

        private Coloring(int color) {
            this.color = color;
        }

        public static Coloring fromHex(String text) {
            if (text.length() == 6) {
                text = "FF" + text;
            }
            return new Coloring((int)Long.parseLong(text, 16));
        }

        public static Coloring fromEpeen(String text) {
            int thickShaft = (int) (text.chars().filter(ch -> ch == '=').count()) * 32;
            int thinShaft = (int) (text.chars().filter(ch -> ch == '-').count()) * 32;
            int juice = (int) (text.chars().filter(ch -> ch == '~').count()) * 32;

            String hex = "";
            hex += thickShaft == 0 ? "00" : Integer.toHexString(thickShaft);
            hex += thinShaft == 0 ? "00" : Integer.toHexString(thinShaft);
            hex += juice == 0 ? "00" : Integer.toHexString(juice);
            return fromHex(hex);
        }

        public static Coloring fromInt(int number) {
            return fromHex(Integer.toHexString(number));
        }


        public int getIntColor() {
            return this.color;
        }
}
