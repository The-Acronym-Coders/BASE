package com.teamacronymcoders.base.registry.pieces.gameregistry;

import com.teamacronymcoders.base.items.IIsHidden;
import com.teamacronymcoders.base.registry.Registry;
import com.teamacronymcoders.base.registry.pieces.RegistryPieceBase;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class CreativeTabRegistryPiece extends RegistryPieceBase {
    @Override
    public boolean acceptsRegistry(Registry registry) {
        return "ITEM".equalsIgnoreCase(registry.getName()) || "BLOCK".equalsIgnoreCase(registry.getName());
    }

    @Override
    public boolean acceptsEntry(ResourceLocation location, Object object) {
        return !(object instanceof IIsHidden);
    }

    @Override
    public void addEntry(ResourceLocation location, Object object) {
        if(object instanceof Item) {
            ((Item) object).setCreativeTab(this.getMod().getCreativeTab());
        } else if(object instanceof Block) {
            ((Block) object).setCreativeTab(this.getMod().getCreativeTab());
        }
    }
}
