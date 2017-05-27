package com.teamacronymcoders.base.registry.pieces.models;

import com.teamacronymcoders.base.blocks.IHasBlockColor;
import com.teamacronymcoders.base.client.Colors;
import com.teamacronymcoders.base.items.IHasItemColor;
import com.teamacronymcoders.base.registry.Registry;
import com.teamacronymcoders.base.registry.pieces.RegistryPiece;
import com.teamacronymcoders.base.registry.pieces.RegistryPieceBase;
import com.teamacronymcoders.base.registry.pieces.RegistrySide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static net.minecraftforge.fml.common.eventhandler.EventPriority.LOW;

@SideOnly(Side.CLIENT)
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
