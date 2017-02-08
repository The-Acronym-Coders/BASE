package com.teamacronymcoders.base.registry.pieces.gameregistry;

import com.teamacronymcoders.base.registry.Registry;
import com.teamacronymcoders.base.registry.pieces.RegistryPiece;
import com.teamacronymcoders.base.registry.pieces.RegistryPieceBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static net.minecraftforge.fml.common.eventhandler.EventPriority.HIGH;

@RegistryPiece(priority = HIGH)
public class ItemRegisterRegistryPiece extends RegistryPieceBase<Item> {
    public ItemRegisterRegistryPiece() {
        super(Item.class);
    }

    @Override
    public boolean acceptsRegistry(Registry registry) {
        return "ITEM".equalsIgnoreCase(registry.getName());
    }

    @Override
    public void preInit(ResourceLocation name, Item entry) {
        GameRegistry.register(entry, name);
    }
}
