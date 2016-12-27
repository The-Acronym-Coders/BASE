package com.teamacronymcoders.base.registry.pieces.gameregistry;

import com.teamacronymcoders.base.items.IHasOreDict;
import com.teamacronymcoders.base.registry.pieces.RegistryPiece;
import com.teamacronymcoders.base.registry.pieces.RegistryPieceBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;
import java.util.Map;

@RegistryPiece
public class OreDictRegistryPiece extends RegistryPieceBase<IHasOreDict> {
    public OreDictRegistryPiece() {
        super(IHasOreDict.class);
    }

    @Override
    public void init(ResourceLocation name, IHasOreDict entry) {
        Map<ItemStack, String> oreDict = entry.getOreDictNames(new HashMap<>());
        oreDict.forEach((itemStack, string) -> OreDictionary.registerOre(string, itemStack));
    }
}
