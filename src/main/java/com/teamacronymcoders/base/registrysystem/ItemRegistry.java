package com.teamacronymcoders.base.registrysystem;

import java.util.List;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.registrysystem.pieces.IRegistryPiece;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemRegistry extends ModularRegistry<Item> {
    public ItemRegistry(IBaseMod mod, List<IRegistryPiece> registryPieces) {
        super("ITEM", mod, registryPieces);
    }

    public void register(Item item) {
        String unlocalizedName = item.getUnlocalizedName();
        if (unlocalizedName.startsWith("item.")) {
            unlocalizedName = unlocalizedName.substring(5);
        }
        if (!unlocalizedName.contains(mod.getID())) {
            item.setUnlocalizedName(mod.getID() + "." + unlocalizedName);
        }
        ResourceLocation name = item.getRegistryName();
        if (name == null) {
            name = new ResourceLocation(this.mod.getID(), unlocalizedName);
        }

        this.register(name, item);
    }
}
