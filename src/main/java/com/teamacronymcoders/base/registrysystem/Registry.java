package com.teamacronymcoders.base.registrysystem;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.exceptions.TooLateException;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Registry<T> {
    protected String name;
    protected IBaseMod mod;
    protected Registry<T> instance;
    protected Map<ResourceLocation, T> entries = new LinkedHashMap<>();
    private LoadingStage loadingStage = LoadingStage.PREINIT;

    public Registry(String name, IBaseMod mod) {
        this.name = name;
        this.mod = mod;
    }

    public void preInit() {
        setLoadingStage(LoadingStage.REGISTER);
    }

    public void registryEvent() {
        setLoadingStage(LoadingStage.INIT);
    }

    public void init() {
        setLoadingStage(LoadingStage.POSTINIT);
    }

    public void postInit() {
        setLoadingStage(LoadingStage.DONE);
    }

    public T get(ResourceLocation name) {
        return this.entries.get(name);
    }

    public void register(ResourceLocation name, T entry) {
        if (requiresBeforeRegister() && getLoadingStage().ordinal() > LoadingStage.REGISTER.ordinal()) {
            throw new TooLateException("ALL REGISTERING MUST HAPPEN BEFORE REGISTRY EVENT");
        }
        if (!this.entries.containsKey(name)) {
            this.entries.put(name, entry);
        } else {
            throw new UnsupportedOperationException("All Entries must be unique. Key: " + name.toString() + " is not.");
        }
    }

    public void onModelRun() {

    }

    public boolean requiresBeforeRegister() {
        return true;
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

    public Map<ResourceLocation, T> getEntries() {
        return this.entries;
    }

    public String getName() {
        return this.name;
    }
}
