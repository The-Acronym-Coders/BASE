package com.teamacronymcoders.base.registry.pieces.gameregistry;

import com.teamacronymcoders.base.registry.Registry;
import com.teamacronymcoders.base.registry.pieces.RegistryPiece;
import com.teamacronymcoders.base.registry.pieces.RegistryPieceBase;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

@RegistryPiece
public class ItemRegisterRegistryPiece extends RegistryPieceBase<Item> {
    @Override
    public boolean acceptsRegistry(Registry registry) {
        return "ITEM".equalsIgnoreCase(registry.getName());
    }

    @Override
    public boolean acceptsEntry(String name, Object entry) {
        return entry instanceof Item;
    }

    @Override
    public void preInit(String name, Item entry) {
        GameRegistry.register(entry);
    }
}
