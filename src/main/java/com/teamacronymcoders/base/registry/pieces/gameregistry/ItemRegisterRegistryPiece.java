package com.teamacronymcoders.base.registry.pieces.gameregistry;

import com.teamacronymcoders.base.registry.Registry;
import com.teamacronymcoders.base.registry.pieces.RegistryPiece;
import com.teamacronymcoders.base.registry.pieces.RegistryPieceBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

@RegistryPiece
public class ItemRegisterRegistryPiece extends RegistryPieceBase<Item> {
    public ItemRegisterRegistryPiece() {
        super(Item.class);
    }

    @Override
    public boolean acceptsRegistry(Registry registry) {
        return "ITEM".equalsIgnoreCase(registry.getName());
    }

    @Override
    public boolean acceptsEntry(ResourceLocation name, Object entry) {
        return entry instanceof Item;
    }

    @Override
    public void preInit(ResourceLocation name, Item entry) {
        GameRegistry.register(entry, name);
    }
}
