package com.teamacronymcoders.base.registrysystem.pieces.gameregistry;

import com.google.common.collect.Maps;
import com.teamacronymcoders.base.blocks.IHasTileEntity;
import com.teamacronymcoders.base.registrysystem.Registry;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPiece;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPieceBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Map;

@RegistryPiece
public class TileEntityRegisterRegistryPiece extends RegistryPieceBase<IHasTileEntity> {
    private Map<String, Class<? extends TileEntity>> tileEntitiesToRegister;

    public TileEntityRegisterRegistryPiece() {
        super(IHasTileEntity.class);
        tileEntitiesToRegister = Maps.newHashMap();
    }

    @Override
    public boolean acceptsRegistry(Registry registry) {
        return "BLOCK".equalsIgnoreCase(registry.getName());
    }

    @Override
    public void onRegistryEvent(ResourceLocation name, IHasTileEntity entry) {
        tileEntitiesToRegister.put(entry.getTileName(name), entry.getTileEntityClass());
    }

    @Override
    public void afterRegistryEvent() {
        tileEntitiesToRegister.forEach((key, value) -> GameRegistry.registerTileEntity(value, key));
        tileEntitiesToRegister.clear();
    }
}
