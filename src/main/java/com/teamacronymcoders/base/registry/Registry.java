package com.teamacronymcoders.base.registry;

import com.teamacronymcoders.base.IBaseMod;

import java.util.HashMap;

public abstract class Registry<T> {
    protected IBaseMod mod;
    protected Registry<T> instance;
    protected HashMap<String, T> entries = new HashMap<>();
    private LoadingStage loadingStage = LoadingStage.PREINIT;

    public Registry(IBaseMod mod) {
        this.mod = mod;
    }

    public void preInit() {
        this.entries.forEach((name, entry) -> {
            initiateEntry(name, entry);
            initiateModel(name, entry);
        });

        setLoadingStage(LoadingStage.INIT);
    }

    protected void initiateEntry(String name, T entry) {
        /*if (entry instanceof IConfigListener) {
            registryHolder.getConfigRegistry().addListener((IConfigListener) entry);
        }
        if (entry instanceof IModAware) {
            ((IModAware) entry).setMod(this.mod);
        }*/
    }

    protected void initiateModel(String name, T entry) {
    }

    public void init() {
        this.entries.forEach(this::initiateRecipes);

        setLoadingStage(LoadingStage.POSTINIT);
    }

    public void postInit() {
        setLoadingStage(LoadingStage.DONE);
    }

    public void initiateRecipes(String name, T entry) {
        /*if (entry instanceof IHasRecipe) {
            for (IRecipe recipe : ((IHasRecipe) entry).getRecipes()) {
                GameRegistry.addRecipe(recipe);
            }
        }*/
    }

    public LoadingStage getLoadingStage() {
        return loadingStage;
    }

    public void setLoadingStage(LoadingStage stage) {
        if (stage.ordinal() < loadingStage.ordinal()) {
            throw new IllegalArgumentException("LOADING STAGE SHOULD NEVER MOVE BACKWARDS");
        } else {
            this.loadingStage = stage;
        }
    }
}
