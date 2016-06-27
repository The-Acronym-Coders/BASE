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

    public void none(String message) { translateMessage("",message); }

    public void message(String message) { translateMessage("message",message); }

    public void label(String message) { translateMessage("label",message); }

    public void block(String message) { translateMessage("block",message); }

    public void item(String message) { translateMessage("item",message); }

    public void itemgroup(String message) { translateMessage("itemGroup",message); }

    public void description(String message) { translateMessage("description",message); }

    public void jei(String message) { translateMessage("jei",message); }

}