package com.teamacronymcoders.base.recipesystem.source;

public class JsonRecipeSource implements IRecipeSource {
    @Override
    public String getName() {
        return "Json";
    }

    @Override
    public boolean doReload() {
        return true;
    }
}
