package com.teamacronymcoders.base.registry;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.registry.pieces.IRegistryPiece;
import net.minecraft.item.Item;

import java.util.List;

public class ItemRegistry extends ModularRegistry<Item> {
    public ItemRegistry(IBaseMod mod, List<IRegistryPiece> registryPieces) {
        super("ITEM", mod, registryPieces);
    }

    public void register(Item item) {
        String name = item.getUnlocalizedName();
        if(name.startsWith("item.")) {
            name = name.substring(5);
        }
        this.register(name, item);
    }
}
