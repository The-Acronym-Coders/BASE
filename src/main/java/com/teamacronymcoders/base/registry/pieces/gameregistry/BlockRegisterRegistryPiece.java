package com.teamacronymcoders.base.registry.pieces.gameregistry;

import com.teamacronymcoders.base.blocks.IHasItemBlock;
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
    public boolean acceptsEntry(String name, Object entry) {
        return entry instanceof Block;
    }

    @Override
    public void preInit(String name, Block entry) {
        ResourceLocation blockName = new ResourceLocation(this.getMod().getID(), name);
        entry.setCreativeTab(this.getMod().getCreativeTab());
        GameRegistry.register(entry, blockName);

        if (entry instanceof IHasItemBlock) {
            GameRegistry.register(((IHasItemBlock) entry).getItemBlock(), blockName);
        }

        if (entry instanceof IHasTileEntity) {
            Class<? extends TileEntity> tileEntityClass = ((IHasTileEntity) entry).getTileEntityClass();
            GameRegistry.registerTileEntity(tileEntityClass, this.getMod().getPrefix() + name);
        }
    }
}
