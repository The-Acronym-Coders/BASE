package com.acronym.base.util;

import net.minecraft.client.resources.I18n;

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
     * Translates a message
     *
     * @param label   prefix
     * @param message message
     * @return Translated String
     */
    public String translateMessage(String label, String message) {
        if (label == "") return I18n.format(message);
        return I18n.format(String.format("%s.%s.%s", label, modid, message));
    }

    /**
     * Returns an emptry translated string
     *
     * @param message message
     * @return Translated String
     */
    public String none(String message) {
        return translateMessage("", message);
    }

    /**
     * Translates a message
     *
     * @param message message
     * @returnTranslated String
     */
    public String message(String message) {
        return translateMessage("message", message);
    }

    /**
     * Translates a message
     *
     * @param message message
     * @returnTranslated String
     */
    public String label(String message) {
        return translateMessage("label", message);
    }

    /**
     * Translates a message
     *
     * @param message message
     * @returnTranslated String
     */
    public String block(String message) {
        return translateMessage("block", message);
    }

    /**
     * Translates a message
     *
     * @param message message
     * @returnTranslated String
     */
    public String item(String message) {
        return translateMessage("item", message);
    }

    /**
     * Translates a message
     *
     * @param message message
     * @returnTranslated String
     */
    public String itemgroup(String message) {
        return translateMessage("itemGroup", message);
    }

    /**
     * Translates a message
     *
     * @param message message
     * @returnTranslated String
     */
    public String description(String message) {
        return translateMessage("description", message);
    }

    /**
     * Translates a message
     *
     * @param message message
     * @returnTranslated String
     */
    public String jei(String message) {
        return translateMessage("jei", message);
    }

    /**
     * Translates a message
     *
     * @param message message
     * @returnTranslated String
     */
    public String material(String message) {
        return translateMessage("material", message);
    }

    /**
     * Translates a message
     *
     * @param message message
     * @returnTranslated String
     */
    public String error(String message) {
        return translateMessage("error", message);
    }

}
