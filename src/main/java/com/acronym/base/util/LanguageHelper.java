package com.acronym.base.util;

import net.minecraft.client.resources.I18n;

public class LanguageHelper {
    private String modid;

    public LanguageHelper(String modid) {
        this.modid = modid;
    }

    public String translateMessage(String label, String message) {
        if(label=="") return I18n.format(message);
        return I18n.format(String.format("%s.%s.%s", label, modid, message));
    }

    public String none(String message) { return translateMessage("",message); }

    public String message(String message) { return translateMessage("message",message); }

    public String label(String message) { return translateMessage("label",message); }

    public String block(String message) { return translateMessage("block",message); }

    public String item(String message) { return translateMessage("item",message); }

    public String itemgroup(String message) { return translateMessage("itemGroup",message); }

    public String description(String message) { return translateMessage("description",message); }

    public String jei(String message) { return translateMessage("jei",message); }

    public String material(String message) { return translateMessage("material",message); }

    public String error(String message) { return translateMessage("error",message); }

}