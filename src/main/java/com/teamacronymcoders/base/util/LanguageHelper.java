package com.teamacronymcoders.base.util;

import com.teamacronymcoders.base.Base;

public class LanguageHelper {

    private String modid;

    /**
     * Initializes a new instance of LanguageHelper
     *
     * @param modid
     */
    public LanguageHelper(String modid) {
        this.modid = modid;
    }



    /**
     * Returns an empty translated string
     *
     * @param message message
     * @return Translated String
     */
    public String none(String message) {
        return Base.proxy.translateMessage("", message);
    }

    /**
     * Translates a message
     *
     * @param message message
     * @returnTranslated String
     */
    public String message(String message) {
        return Base.proxy.translateMessage("message", message);
    }

    /**
     * Translates a message
     *
     * @param message message
     * @returnTranslated String
     */
    public String label(String message) {
        return Base.proxy.translateMessage("label", message);
    }

    /**
     * Translates a message
     *
     * @param message message
     * @returnTranslated String
     */
    public String block(String message) {
        return Base.proxy.translateMessage("block", message);
    }

    /**
     * Translates a message
     *
     * @param message message
     * @returnTranslated String
     */
    public String item(String message) {
        return Base.proxy.translateMessage("item", message);
    }

    /**
     * Translates a message
     *
     * @param message message
     * @returnTranslated String
     */
    public String itemgroup(String message) {
        return Base.proxy.translateMessage("itemGroup", message);
    }

    /**
     * Translates a message
     *
     * @param message message
     * @returnTranslated String
     */
    public String description(String message) {
        return Base.proxy.translateMessage("description", message);
    }

    /**
     * Translates a message
     *
     * @param message message
     * @returnTranslated String
     */
    public String jei(String message) {
        return Base.proxy.translateMessage("jei", message);
    }

    /**
     * Translates a message
     *
     * @param message message
     * @returnTranslated String
     */
    public String material(String message) {
        return Base.proxy.translateMessage("material", message);
    }

    /**
     * Translates a message
     *
     * @param message message
     * @returnTranslated String
     */
    public String error(String message) {
        return Base.proxy.translateMessage("error", message);
    }

}
