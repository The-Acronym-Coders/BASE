package com.teamacronymcoders.base.registrysystem.pieces.models;

import com.teamacronymcoders.base.client.Models;
import com.teamacronymcoders.base.client.models.IHasModel;
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
public class ModelRegistryPiece extends RegistryPieceBase<IHasModel> {
    public ModelRegistryPiece() {
        super(IHasModel.class);
    }

    @Override
    public boolean acceptsRegistry(Registry registry) {
        return "ITEM".equalsIgnoreCase(registry.getName());
    }

    @Override
    public void onRegistryEvent(ResourceLocation name, IHasModel entry) {
        Models.registerModels(entry);
    }
}
