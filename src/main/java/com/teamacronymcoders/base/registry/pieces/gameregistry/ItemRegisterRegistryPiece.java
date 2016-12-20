package com.teamacronymcoders.base.registry.pieces.gameregistry;

import com.teamacronymcoders.base.registry.Registry;
import com.teamacronymcoders.base.registry.pieces.RegistryPiece;
import com.teamacronymcoders.base.registry.pieces.RegistryPieceBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
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
        ResourceLocation itemRegistryName = new ResourceLocation(this.getMod().getPrefix() + name);
        entry.setCreativeTab(this.getMod().getCreativeTab());
        GameRegistry.register(entry, itemRegistryName);
    }
}
