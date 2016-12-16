package com.teamacronymcoders.base.registry.pieces.gameregistry;

import com.teamacronymcoders.base.registry.pieces.IRegistryPiece;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRegisterRegistryPiece implements IRegistryPiece<Item> {
    @Override
    public boolean acceptsRegistry(String name) {
        return "ITEM".equalsIgnoreCase(name);
    }

    @Override
    public void preInit(String name, Item entry) {
        GameRegistry.register(entry);
    }
}
