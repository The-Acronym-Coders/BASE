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
        ResourceLocation name = item.getRegistryName();
        if (name == null) {
            String unlocalizedName = item.getUnlocalizedName();
            if (unlocalizedName.startsWith("item.")) {
                unlocalizedName = unlocalizedName.substring(5);
            }
            name = new ResourceLocation(this.mod.getID(), unlocalizedName);
        }

        this.register(name, item);
    }
}
