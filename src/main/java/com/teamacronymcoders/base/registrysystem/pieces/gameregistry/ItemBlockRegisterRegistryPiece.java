package com.teamacronymcoders.base.registrysystem.pieces.gameregistry;

import com.teamacronymcoders.base.blocks.IHasItemBlock;
import com.teamacronymcoders.base.registrysystem.ItemRegistry;
import com.teamacronymcoders.base.registrysystem.Registry;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPiece;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPieceBase;
import net.minecraft.util.ResourceLocation;

@RegistryPiece
public class ItemBlockRegisterRegistryPiece extends RegistryPieceBase<IHasItemBlock> {
    private ItemRegistry itemRegistry;

    public ItemBlockRegisterRegistryPiece() {
        super(IHasItemBlock.class);
    }

    @Override
    public boolean acceptsRegistry(Registry registry) {
        return "BLOCK".equalsIgnoreCase(registry.getName());
    }

    @Override
    public void addEntry(ResourceLocation name, IHasItemBlock block) {
        if (itemRegistry == null) {
            this.itemRegistry = this.getMod().getRegistryHolder().getRegistry(ItemRegistry.class, "ITEM");
        }
        itemRegistry.register(name, block.getItemBlock());
    }
}
