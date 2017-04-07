package com.teamacronymcoders.base.modules.debug;

import com.teamacronymcoders.base.modules.debug.items.ItemDebuggerStick;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import com.teamacronymcoders.base.registry.ItemRegistry;
import com.teamacronymcoders.base.registry.config.ConfigRegistry;
import com.teamacronymcoders.base.util.Platform;

public class DebugModule extends ModuleBase {
    @Override
    public String getName() {
        return "Debug";
    }

    @Override
    public boolean getActiveDefault() {
        return Platform.isDevEnv();
    }

    @Override
    public void registerItems(ConfigRegistry configRegistry, ItemRegistry itemRegistry) {
        itemRegistry.register(new ItemDebuggerStick());
    }
}
