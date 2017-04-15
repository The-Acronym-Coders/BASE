package com.teamacronymcoders.base.registrysystem;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.registrysystem.pieces.IRegistryPiece;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class BlockRegistry extends ModularRegistry<Block> {
    public BlockRegistry(IBaseMod mod, List<IRegistryPiece> registryPieces) {
        super("BLOCK", mod, registryPieces);
    }

    public void register(Block block) {
        ResourceLocation name = block.getRegistryName();
        if (name == null) {
            String unlocalizedName = block.getUnlocalizedName();
            if (unlocalizedName.startsWith("tile.")) {
                unlocalizedName = unlocalizedName.substring(5);
            }
            name = new ResourceLocation(this.mod.getID(), unlocalizedName);
        }

        this.register(name, block);
    }
}
