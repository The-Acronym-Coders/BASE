package com.teamacronymcoders.base.materialsystem.compat.chicken;

import com.setycz.chickens.registry.ChickensRegistry;
import com.setycz.chickens.registry.ChickensRegistryItem;
import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.partdata.MaterialPartData;
import com.teamacronymcoders.base.materialsystem.parttype.PartDataPiece;
import com.teamacronymcoders.base.materialsystem.parttype.PartType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.List;

public class ChickenPartType extends PartType {
    public ChickenPartType() {
        super("chicken", createPartData());
    }

    private static List<PartDataPiece> createPartData() {
        return null;
    }

    public void setup(@Nonnull MaterialPart materialPart, @Nonnull MaterialUser materialUser) {
        ResourceLocation registryName = new ResourceLocation(materialUser.getId(), materialPart.getUnlocalizedName());
        ChickensRegistry.register(new ChickensRegistryItem(registryName,
                registryName.toString(),
                registryName,
                ItemStack.EMPTY,
                materialPart.getColor(),
                materialPart.getColor(),
                getParent(materialPart.getData(), "parentOne"),
                getParent(materialPart.getData(), "parentTwo")
        ));
    }

    private ChickensRegistryItem getParent(MaterialPartData data, String parentNumber) {
        return data.containsDataPiece(parentNumber) ?
                ChickensRegistry.getByRegistryName(data.getDataPiece(parentNumber)) : null;
    }
}
