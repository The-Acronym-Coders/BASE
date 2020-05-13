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
    
    public BlockRegistry(IBaseMod mod, List<IRegistryPiece> registryPieces, String altNamespace) {
        super("BLOCK", mod, registryPieces);
    }

    public void register(Block block) {
        String namespace = (altNamespace != null) ? altNamespace : mod.getID();
        String unlocalizedName = block.getTranslationKey();
        if (unlocalizedName.startsWith("tile.")) {
            unlocalizedName = unlocalizedName.substring(5);
        }
        if (!unlocalizedName.contains(namespace)) {
            block.setTranslationKey(namespace + "." + unlocalizedName);
        }
        ResourceLocation name = block.getRegistryName();
        if (name == null) {
            name = new ResourceLocation(namespace, unlocalizedName);
        }

        this.register(name, block);
    }
}
