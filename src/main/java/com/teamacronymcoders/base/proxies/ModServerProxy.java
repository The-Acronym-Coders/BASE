package com.teamacronymcoders.base.proxies;

import com.teamacronymcoders.base.Reference;
import com.teamacronymcoders.base.event.BaseRegistryEvent;
import com.teamacronymcoders.base.recipesystem.loader.ILoader;
import com.teamacronymcoders.base.recipesystem.loader.ServerConfigJsonRecipeLoader;
import net.minecraft.util.ResourceLocation;

public class ModServerProxy extends ModCommonProxy {
    @Override
    public void registerServerLoader(BaseRegistryEvent<ILoader> loaderRegistryEvent) {
        loaderRegistryEvent.register(new ResourceLocation(Reference.MODID, "server"),
                ServerConfigJsonRecipeLoader.getInstance());
    }
}
