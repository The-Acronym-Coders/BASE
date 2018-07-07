package com.teamacronymcoders.base.materialsystem.compat.base;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.compat.IMaterialCompat;
import com.teamacronymcoders.base.materialsystem.compat.MaterialCompat;
import com.teamacronymcoders.base.materialsystem.parts.PartBuilder;
import com.teamacronymcoders.base.materialsystem.parttype.*;

@MaterialCompat("base")
public class BaseCompat implements IMaterialCompat {
    @Override
    public void doCompat() {
        PartType item = new ItemPartType();
        PartType block = new BlockPartType();
        PartType ore = new OrePartType();
        PartType oreSample = new OreSamplePartType();
        PartType fluid = new FluidPartType();
        PartType armor = new ArmorPartType();
        PartType minecart = new MinecartPartType();

        MaterialSystem.registerPartType(item);
        MaterialSystem.registerPartType(block);
        MaterialSystem.registerPartType(ore);
        MaterialSystem.registerPartType(oreSample);
        MaterialSystem.registerPartType(fluid);
        MaterialSystem.registerPartType(armor);
        MaterialSystem.registerPartType(minecart);

        //Specifically for Backwards Compat
        MaterialSystem.registerPartType(new StoragePartType());

        registerPart("Ingot", item);
        registerPart("Beam", item);
        registerPart("Gear", item);
        registerPart("Bolt", item);
        registerPart("Dust", item);
        registerPart("Nugget", item);
        registerPart(new PartBuilder().setName("Rod").setPartType(item)
                .setAdditionalOreDictNames("stick", "partToolRod"));
        registerPart("Plate", item);
        registerPart(new PartBuilder().setName("Dense Plate").setPartType(item)
                .setAdditionalOreDictNames("plateDense"));
        registerPart("Crystal", item);
        registerPart("Crushed Ore", item);
        registerPart("Casing", item);

        registerPart("Clump", item);
        registerPart("Shard", item);
        registerPart("Dirty Dust", item);

        registerPart(new PartBuilder().setName("Cluster").setPartType(item).setOverlay(true));

        registerPart("Block", block);

        registerPart("Ore", ore);
        registerPart("Poor Ore", ore);
        registerPart("Dense Ore", ore);

        registerPart("Ore Sample", oreSample);

        registerPart(new PartBuilder().setName("Molten").setPartType(fluid).setOreDictName(""));

        registerPart("Armor", armor);

        registerPart("Minecart", minecart);
    }

    private void registerPart(String name, PartType partType) {
        registerPart(new PartBuilder().setName(name).setPartType(partType));
    }

    private void registerPart(PartBuilder partBuilder) {
        try {
            partBuilder.build();
        } catch (MaterialException e) {
            Base.instance.getLogger().getLogger().error(e);
        }
    }
}
