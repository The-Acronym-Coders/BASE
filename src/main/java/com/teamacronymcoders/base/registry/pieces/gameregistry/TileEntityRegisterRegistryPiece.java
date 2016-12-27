package com.teamacronymcoders.base.registry.pieces.gameregistry;

import com.teamacronymcoders.base.blocks.IHasTileEntity;
import com.teamacronymcoders.base.registry.Registry;
import com.teamacronymcoders.base.registry.pieces.RegistryPiece;
import com.teamacronymcoders.base.registry.pieces.RegistryPieceBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

@RegistryPiece
public class TileEntityRegisterRegistryPiece extends RegistryPieceBase<IHasTileEntity> {
    public TileEntityRegisterRegistryPiece() {
        super(IHasTileEntity.class);
    }

    @Override
    public boolean acceptsRegistry(Registry registry) {
        return "BLOCK".equalsIgnoreCase(registry.getName());
    }

    @Override
    public void preInit(ResourceLocation name, IHasTileEntity entry) {
        GameRegistry.registerTileEntity(entry.getTileEntityClass(), name.toString());
    }
}
