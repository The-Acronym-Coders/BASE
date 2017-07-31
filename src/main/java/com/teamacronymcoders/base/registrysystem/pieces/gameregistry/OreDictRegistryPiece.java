package com.teamacronymcoders.base.registrysystem.pieces.gameregistry;

import static net.minecraftforge.fml.common.eventhandler.EventPriority.LOW;

import java.util.HashMap;
import java.util.Map;

import com.teamacronymcoders.base.items.IHasOreDict;
import com.teamacronymcoders.base.registrysystem.Registry;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPiece;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPieceBase;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

@RegistryPiece(priority = LOW)
public class OreDictRegistryPiece extends RegistryPieceBase<IHasOreDict> {
    public OreDictRegistryPiece() {
        super(IHasOreDict.class);
    }

    @Override
    public boolean acceptsRegistry(Registry registry) {
        return "ITEM".equalsIgnoreCase(registry.getName());
    }

    @Override
    public void onRegistryEvent(ResourceLocation name, IHasOreDict entry) {
        Map<ItemStack, String> oreDict = entry.getOreDictNames(new HashMap<>());
        oreDict.forEach((itemStack, string) -> OreDictionary.registerOre(string, itemStack));
    }
}
