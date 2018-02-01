package com.teamacronymcoders.base.client.models.generator;

import com.teamacronymcoders.base.registrysystem.pieces.RegistryPiece;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPieceBase;
import com.teamacronymcoders.base.registrysystem.pieces.RegistrySide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@RegistryPiece(value = RegistrySide.CLIENT, priority = EventPriority.LOWEST)
public class ModelGeneratorPiece extends RegistryPieceBase<IHasGeneratedModel> {

    public ModelGeneratorPiece() {
        super(IHasGeneratedModel.class);
    }

    @Override
    public void onModelEvent(ResourceLocation name, IHasGeneratedModel entry) {
        ModelGenerator.generate(entry);
    }
}
