package com.teamacronymcoders.base.registrysystem;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.registrysystem.pieces.IRegistryPiece;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class ItemRegistry extends ModularRegistry<Item> {
    public ItemRegistry(IBaseMod mod, List<IRegistryPiece> registryPieces) {
        super("ITEM", mod, registryPieces);
    }

    public void register(Item item) {
        String unlocalizedName = item.getUnlocalizedName();
        boolean removedItem = false;
        if (unlocalizedName.startsWith("item.")) {
            unlocalizedName = unlocalizedName.substring(5);
            removedItem = true;
        }
        if (!unlocalizedName.contains(mod.getID())) {
            String tilePart = removedItem ? "item." : "";
            item.setUnlocalizedName(tilePart + mod.getID() + "." + unlocalizedName);
        }
        ResourceLocation name = item.getRegistryName();
        if (name == null) {
            name = new ResourceLocation(this.mod.getID(), unlocalizedName);
        }

        this.register(name, item);
    }
}
