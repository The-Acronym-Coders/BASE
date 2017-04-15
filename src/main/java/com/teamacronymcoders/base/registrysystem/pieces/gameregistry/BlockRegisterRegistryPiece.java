package com.teamacronymcoders.base.registrysystem.pieces.gameregistry;

import com.teamacronymcoders.base.registrysystem.Registry;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPiece;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPieceBase;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static net.minecraftforge.fml.common.eventhandler.EventPriority.HIGH;

@RegistryPiece(priority = HIGH)
public class BlockRegisterRegistryPiece extends RegistryPieceBase<Block> {
    public BlockRegisterRegistryPiece() {
        super(Block.class);
    }

    @Override
    public boolean acceptsRegistry(Registry registry) {
        return "BLOCK".equalsIgnoreCase(registry.getName());
    }

    @Override
    public void preInit(ResourceLocation name, Block entry) {
        GameRegistry.register(entry, name);
    }
}
