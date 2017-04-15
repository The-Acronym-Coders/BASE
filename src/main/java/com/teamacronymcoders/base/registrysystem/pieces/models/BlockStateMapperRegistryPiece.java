package com.teamacronymcoders.base.registrysystem.pieces.models;

import com.teamacronymcoders.base.blocks.IHasBlockStateMapper;
import com.teamacronymcoders.base.client.BlockStateMappers;
import com.teamacronymcoders.base.registrysystem.Registry;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPiece;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPieceBase;
import com.teamacronymcoders.base.registrysystem.pieces.RegistrySide;
import net.minecraft.util.ResourceLocation;

import static net.minecraftforge.fml.common.eventhandler.EventPriority.LOW;

@RegistryPiece(value = RegistrySide.CLIENT, priority = LOW)
public class BlockStateMapperRegistryPiece extends RegistryPieceBase<IHasBlockStateMapper> {
    public BlockStateMapperRegistryPiece() {
        super(IHasBlockStateMapper.class);
    }

    @Override
    public boolean acceptsRegistry(Registry registry) {
        return "BLOCK".equalsIgnoreCase(registry.getName());
    }

    @Override
    public void preInit(ResourceLocation name, IHasBlockStateMapper entry) {
        BlockStateMappers.registerStateMapper(entry);
    }
}
