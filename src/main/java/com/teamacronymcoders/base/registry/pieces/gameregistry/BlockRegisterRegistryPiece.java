package com.teamacronymcoders.base.registry.pieces.gameregistry;

import com.teamacronymcoders.base.blocks.IHasTileEntity;
import com.teamacronymcoders.base.registry.Registry;
import com.teamacronymcoders.base.registry.pieces.RegistryPiece;
import com.teamacronymcoders.base.registry.pieces.RegistryPieceBase;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

@RegistryPiece
public class BlockRegisterRegistryPiece extends RegistryPieceBase<Block> {
    @Override
    public boolean acceptsRegistry(Registry registry) {
        return "BLOCK".equalsIgnoreCase(registry.getName());
    }

    @Override
    public boolean acceptsEntry(ResourceLocation name, Object entry) {
        return entry instanceof Block;
    }

    @Override
    public void preInit(ResourceLocation name, Block entry) {
        entry.setCreativeTab(this.getMod().getCreativeTab());
        GameRegistry.register(entry, name);

        if (entry instanceof IHasTileEntity) {
            Class<? extends TileEntity> tileEntityClass = ((IHasTileEntity) entry).getTileEntityClass();
            GameRegistry.registerTileEntity(tileEntityClass, name.toString());
        }
    }
}
