package com.teamacronymcoders.base.registry.pieces.gameregistry;

import com.teamacronymcoders.base.blocks.IHasItemBlock;
import com.teamacronymcoders.base.registry.Registry;
import com.teamacronymcoders.base.registry.pieces.RegistryPiece;
import com.teamacronymcoders.base.registry.pieces.RegistryPieceBase;
import net.minecraft.item.Item;

@RegistryPiece
public class ItemBlockRegisterRegistryPiece extends RegistryPieceBase {
    private Registry<Item> itemRegistry;

    @Override
    @SuppressWarnings("unchecked")
    public boolean acceptsRegistry(Registry registry) {
        if("ITEM".equalsIgnoreCase(registry.getName())) {
            itemRegistry = registry;
        }
        return "BLOCK".equalsIgnoreCase(registry.getName());
    }

    @Override
    public void addEntry(String name, Object block) {
        if(block instanceof IHasItemBlock) {
            itemRegistry.register(name, ((IHasItemBlock) block).getItemBlock());
        }
    }
}
