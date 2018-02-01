package com.teamacronymcoders.base.registrysystem.pieces.models;

import com.teamacronymcoders.base.client.ItemMeshDefinitions;
import com.teamacronymcoders.base.items.IHasItemMeshDefinition;
import com.teamacronymcoders.base.registrysystem.Registry;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPiece;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPieceBase;
import com.teamacronymcoders.base.registrysystem.pieces.RegistrySide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static net.minecraftforge.fml.common.eventhandler.EventPriority.LOW;

@SideOnly(Side.CLIENT)
@RegistryPiece(value = RegistrySide.CLIENT, priority = LOW)
public class ItemMeshDefinitioniRegistryPiece extends RegistryPieceBase<IHasItemMeshDefinition> {
    public ItemMeshDefinitioniRegistryPiece() {
        super(IHasItemMeshDefinition.class);
    }

    @Override
    public boolean acceptsRegistry(Registry registry) {
        return "ITEM".equalsIgnoreCase(registry.getName());
    }

    @Override
    public void onModelEvent(ResourceLocation name, IHasItemMeshDefinition entry) {
        ItemMeshDefinitions.registerItemMeshDefinitions(entry);
    }
}
