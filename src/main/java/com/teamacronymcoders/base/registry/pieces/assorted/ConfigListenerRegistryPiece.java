package com.teamacronymcoders.base.registry.pieces.assorted;

import com.teamacronymcoders.base.registry.config.ConfigRegistry;
import com.teamacronymcoders.base.registry.config.IConfigListener;
import com.teamacronymcoders.base.registry.pieces.RegistryPiece;
import com.teamacronymcoders.base.registry.pieces.RegistryPieceBase;
import net.minecraft.util.ResourceLocation;

@RegistryPiece
public class ConfigListenerRegistryPiece extends RegistryPieceBase<IConfigListener> {
    private ConfigRegistry configRegistry;

    @Override
    public boolean acceptsEntry(ResourceLocation name, Object entry) {
        return entry instanceof IConfigListener;
    }

    @Override
    public void addEntry(ResourceLocation name, IConfigListener listener) {
        if (configRegistry == null) {
            configRegistry = this.getMod().getRegistryHolder().getRegistry(ConfigRegistry.class, "CONFIG");
        }
        configRegistry.addListener(listener);
    }
}
