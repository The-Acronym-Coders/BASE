package com.teamacronymcoders.base.registry.pieces.gameregistry;

import com.teamacronymcoders.base.registry.pieces.RegistryPieceBase;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockRegisterRegistryPiece extends RegistryPieceBase<Block> {
    @Override
    public boolean acceptsRegistry(String name) {
        return "BLOCK".equalsIgnoreCase(name);
    }

    @Override
    public void preInit(String name, Block entry) {
        GameRegistry.register(entry);
    }
}
