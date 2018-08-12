package com.teamacronymcoders.base.recipesystem.event;

import com.google.common.collect.Maps;
import com.teamacronymcoders.base.json.factory.DefaultObjectFactory;
import com.teamacronymcoders.base.json.factory.IObjectFactory;
import com.teamacronymcoders.base.recipesystem.input.IRecipeInput;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Map;

public class RegisterRecipeSystemTypesEvent extends Event {
    private final Map<String, IObjectFactory<IRecipeInput>> recipeInputFactories;

    public RegisterRecipeSystemTypesEvent() {
        recipeInputFactories = Maps.newHashMap();
    }

    public void registerRecipeInput(ResourceLocation resourceLocation, IObjectFactory<IRecipeInput> recipeInputFactory) {
        recipeInputFactories.put(resourceLocation.toString(), recipeInputFactory);
    }

    public void registerRecipeInput(ResourceLocation resourceLocation, Class<IRecipeInput> recipeInputClass) {
        registerRecipeInput(resourceLocation, new DefaultObjectFactory<>(recipeInputClass));
    }

    public Map<String, IObjectFactory<IRecipeInput>> getRecipeInputFactories() {
        return recipeInputFactories;
    }
}
