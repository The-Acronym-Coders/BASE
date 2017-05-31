package com.teamacronymcoders.base.registrysystem.pieces.gameregistry;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.items.IIsHidden;
import com.teamacronymcoders.base.registrysystem.Registry;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPiece;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPieceBase;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

@RegistryPiece
public class CreativeTabRegistryPiece extends RegistryPieceBase {
    private CreativeTabs creativeTabs = null;

    public CreativeTabRegistryPiece() {
        super();
    }

    @Override
    public boolean acceptsRegistry(Registry registry) {
        return "ITEM".equalsIgnoreCase(registry.getName()) || "BLOCK".equalsIgnoreCase(registry.getName());
    }

    @Override
    public boolean acceptsEntry(ResourceLocation location, Object object) {
        return creativeTabs != null && !(object instanceof IIsHidden);
    }

    @Override
    public void addEntry(ResourceLocation location, Object object) {
        if (creativeTabs != null) {
            if (object instanceof Item) {
                ((Item) object).setCreativeTab(creativeTabs);
            } else if (object instanceof Block) {
                ((Block) object).setCreativeTab(creativeTabs);
            }
        }
    }

    @Override
    public void setMod(IBaseMod mod) {
        super.setMod(mod);
        this.creativeTabs = mod.getCreativeTab();
    }
}
