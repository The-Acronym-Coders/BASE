package com.teamacronymcoders.base.registrysystem.pieces.models;

import com.teamacronymcoders.base.blocks.IHasBlockColor;
import com.teamacronymcoders.base.client.Colors;
import com.teamacronymcoders.base.items.IHasItemColor;
import com.teamacronymcoders.base.registrysystem.Registry;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPiece;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPieceBase;
import com.teamacronymcoders.base.registrysystem.pieces.RegistrySide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static net.minecraftforge.eventbus.api.EventPriority.LOW;

@OnlyIn(Dist.CLIENT)
@RegistryPiece(value = RegistrySide.CLIENT, priority = LOW)
public class ColorRegistryPiece extends RegistryPieceBase {
    @Override
    public boolean acceptsRegistry(Registry registry) {
        return "ITEM".equalsIgnoreCase(registry.getName()) || "BLOCK".equalsIgnoreCase(registry.getName());
    }

    public void init(ResourceLocation name, Object entry) {
        if (entry instanceof IHasItemColor) {
            Colors.registerItemColor(entry, (IHasItemColor) entry);
        }
        if (entry instanceof IHasBlockColor) {
            Colors.registerBlockColor((IHasBlockColor) entry);
        }
    }
}
