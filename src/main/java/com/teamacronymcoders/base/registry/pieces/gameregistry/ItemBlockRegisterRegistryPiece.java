package com.teamacronymcoders.base.registry.pieces.gameregistry;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.blocks.IHasItemBlock;
import com.teamacronymcoders.base.registry.ItemRegistry;
import com.teamacronymcoders.base.registry.Registry;
import com.teamacronymcoders.base.registry.pieces.RegistryPiece;
import com.teamacronymcoders.base.registry.pieces.RegistryPieceBase;
import net.minecraft.util.ResourceLocation;

@RegistryPiece
public class ItemBlockRegisterRegistryPiece extends RegistryPieceBase<IHasItemBlock> {
    private ItemRegistry itemRegistry;

    @Override
    @SuppressWarnings("unchecked")
    public boolean acceptsRegistry(Registry registry) {
        return "BLOCK".equalsIgnoreCase(registry.getName());
    }

    @Override
    public boolean acceptsEntry(ResourceLocation name, Object entry) {
        return entry instanceof IHasItemBlock;
    }

    @Override
    public void addEntry(ResourceLocation name, IHasItemBlock block) {
        itemRegistry.register(name, block.getItemBlock());
    }

    @Override
    public void setMod(IBaseMod mod) {
        super.setMod(mod);
        this.itemRegistry = mod.getRegistryHolder().getRegistry(ItemRegistry.class, "ITEM");
    }
}
