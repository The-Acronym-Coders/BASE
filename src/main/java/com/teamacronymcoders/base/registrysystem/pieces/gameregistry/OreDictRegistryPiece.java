package com.teamacronymcoders.base.registrysystem.pieces.gameregistry;

import com.teamacronymcoders.base.items.IHasOreDict;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPiece;
import com.teamacronymcoders.base.registrysystem.pieces.RegistryPieceBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;
import java.util.Map;

import static net.minecraftforge.fml.common.eventhandler.EventPriority.LOW;

@RegistryPiece(priority = LOW)
public class OreDictRegistryPiece extends RegistryPieceBase<IHasOreDict> {
    public OreDictRegistryPiece() {
        super(IHasOreDict.class);
    }

    @Override
    public void preInit(ResourceLocation name, IHasOreDict entry) {
        Map<ItemStack, String> oreDict = entry.getOreDictNames(new HashMap<>());
        oreDict.forEach((itemStack, string) -> OreDictionary.registerOre(string, itemStack));
    }
}
