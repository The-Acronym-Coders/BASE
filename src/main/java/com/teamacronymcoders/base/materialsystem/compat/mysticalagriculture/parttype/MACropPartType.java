package com.teamacronymcoders.base.materialsystem.compat.mysticalagriculture.parttype;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.materialsystem.compat.mysticalagriculture.block.BlockMaterialMystericalCrop;
import com.teamacronymcoders.base.materialsystem.compat.mysticalagriculture.item.ItemMaterialMysticalSeed;
import com.teamacronymcoders.base.materialsystem.items.ItemSingularMaterialPart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.partdata.DataPartParsers;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.materialsystem.parttype.PartDataPiece;
import com.teamacronymcoders.base.materialsystem.parttype.PartType;
import com.teamacronymcoders.base.registrysystem.BlockRegistry;
import com.teamacronymcoders.base.registrysystem.IRegistryHolder;
import com.teamacronymcoders.base.registrysystem.ItemRegistry;

import javax.annotation.Nonnull;

public class MACropPartType extends PartType {
    public MACropPartType() {
        super("MA Crop", Lists.newArrayList(new PartDataPiece("tiers", true)));
    }

    public void setup(@Nonnull MaterialPart materialPart, @Nonnull MaterialUser materialUser) {
        BlockMaterialMystericalCrop blockMaterialMystericalCrop = new BlockMaterialMystericalCrop(materialPart);
        IRegistryHolder registryHolder = materialUser.getMod().getRegistryHolder();
        registryHolder.getRegistry(BlockRegistry.class, "BLOCK").register(blockMaterialMystericalCrop);

        ItemRegistry itemRegistry = registryHolder.getRegistry(ItemRegistry.class, "ITEM");
        ItemMaterialMysticalSeed materialMysticalSeed = new ItemMaterialMysticalSeed(materialPart, blockMaterialMystericalCrop,
                materialPart.getData().getValue("tier", 5, DataPartParsers::getInt));
        itemRegistry.register(materialMysticalSeed);

        Part essencePart = MaterialSystem.getPart("MA Essence");
        MaterialPart materialPartEssence = new MaterialPart(materialUser, materialPart.getMaterial(), essencePart);
        ItemSingularMaterialPart itemSingularMaterialPart = new ItemSingularMaterialPart(materialPartEssence);
        itemRegistry.register(itemSingularMaterialPart);

        blockMaterialMystericalCrop.setSeed(materialMysticalSeed);
        blockMaterialMystericalCrop.setCrop(itemSingularMaterialPart);
    }
}
