package com.teamacronymcoders.base.registry;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.registry.pieces.IRegistryPiece;
import net.minecraft.block.Block;

import java.util.List;

public class BlockRegistry extends ModularRegistry<Block> {
    public BlockRegistry(IBaseMod mod, List<IRegistryPiece> registryPieces) {
        super("BLOCK", mod, registryPieces);
    }

    public void register(Block block) {
        String name = block.getUnlocalizedName();
        if(name.startsWith("tile.")) {
            name = name.substring(5);
        }
        register(name, block);
    }
}
