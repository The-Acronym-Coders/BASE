package com.teamacronymcoders.base.registry;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.IModAware;
import com.teamacronymcoders.base.items.IHasOreDict;
import com.teamacronymcoders.base.items.IHasRecipe;
import com.teamacronymcoders.base.registry.config.ConfigRegistry;
import com.teamacronymcoders.base.registry.config.IConfigListener;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Registry<T> {
    protected String name;
    protected IBaseMod mod;
    protected Registry<T> instance;
    protected Map<String, T> entries = new HashMap<>();
    private LoadingStage loadingStage = LoadingStage.PREINIT;

    public Registry(String name, IBaseMod mod) {
        this.name = name;
        this.mod = mod;
    }

    public void preInit() {
        this.entries.forEach((name, entry) -> {
            initiateEntry(name, entry);
            initiateModel(name, entry);
        });

        setLoadingStage(LoadingStage.INIT);
    }

    public void init() {
        this.entries.forEach((name, entry) -> {
            initiateRecipes(entry);
            initiateOreDict(entry);
            initiateColor(entry);
        });

        setLoadingStage(LoadingStage.POSTINIT);
    }

    public void postInit() {
        setLoadingStage(LoadingStage.DONE);
    }

    protected void initiateEntry(String name, T entry) {
        if (entry instanceof IConfigListener) {
            mod.getRegistryHolder().getRegistry(ConfigRegistry.class, "CONFIG").addListener((IConfigListener) entry);
        }
        if (entry instanceof IModAware) {
            ((IModAware) entry).setMod(mod);
        }
    }

    protected void initiateModel(String name, T entry) {

    }

    protected void initiateColor(T entry) {

    }

    protected void initiateOreDict(T entry) {
        if(entry instanceof IHasOreDict) {
            Map<ItemStack, String> oreDict = ((IHasOreDict) entry).getOreDictNames(new HashMap<>());
            oreDict.forEach((itemStack, s) -> OreDictionary.registerOre(s, itemStack));
        }
    }

    public void initiateRecipes(T entry) {
        if (entry instanceof IHasRecipe) {
            ((IHasRecipe) entry).getRecipes(new ArrayList<>()).forEach(GameRegistry::addRecipe);
        }
    }

    public T get(String name) {
        return this.entries.get(name);
    }

    public void register(String name, T entry) {
        if (getLoadingStage() != LoadingStage.PREINIT) {
            throw new UnsupportedOperationException("ALL REGISTERING MUST HAPPEN IN PREINIT");
        }
        if(!this.entries.containsKey(name)) {
            this.entries.put(name, entry);
        } else {
            throw new UnsupportedOperationException("All Entries must be unique");
        }
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

    public Map<String, T> getEntries() {
        return this.entries;
    }

    public String getName() {
        return this.name;
    }
}
