package com.teamacronymcoders.base.recipesystem.event;

import com.google.common.collect.Maps;
import com.teamacronymcoders.base.json.factory.DefaultObjectFactory;
import com.teamacronymcoders.base.json.factory.IObjectFactory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.GenericEvent;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

@ParametersAreNonnullByDefault
public class RegisterRecipeFactoriesEvent<T> extends GenericEvent<T> {
    private final Map<String, IObjectFactory<? extends T>> factories;

    public RegisterRecipeFactoriesEvent(Class<T> tClass) {
        super(tClass);
        factories = Maps.newHashMap();
    }

    public void register(ResourceLocation resourceLocation, IObjectFactory<? extends T> factory) {
        factories.put(resourceLocation.toString(), factory);
    }

    public void register(ResourceLocation resourceLocation, Class<? extends T> tClass) {
        register(resourceLocation, new DefaultObjectFactory<>(tClass));
    }

    public Map<String, IObjectFactory<? extends T>> getFactories() {
        return factories;
    }
}
