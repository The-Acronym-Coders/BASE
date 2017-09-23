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
    public void doCompat() throws MaterialException {
        PartType item = new ItemPartType();
        PartType block = new BlockPartType();
        PartType ore = new OrePartType();
        PartType fluid = new FluidPartType();
        PartType armor = new ArmorPartType();

        MaterialSystem.registerPartType(item);
        MaterialSystem.registerPartType(block);
        MaterialSystem.registerPartType(ore);
        MaterialSystem.registerPartType(fluid);
        MaterialSystem.registerPartType(armor);

        registerPart(new PartBuilder().setName("Ingot").setPartType(item));
        registerPart(new PartBuilder().setName("Beam").setPartType(item));
        registerPart(new PartBuilder().setName("Gear").setPartType(item));
        registerPart(new PartBuilder().setName("Bolt").setPartType(item));
        registerPart(new PartBuilder().setName("Dust").setPartType(item));
        registerPart(new PartBuilder().setName("Nugget").setPartType(item));
        registerPart(new PartBuilder().setName("Rod").setPartType(item));
        registerPart(new PartBuilder().setName("Plate").setPartType(item));
        registerPart(new PartBuilder().setName("Dense Plate").setPartType(item));

        registerPart(new PartBuilder().setName("Block").setPartType(block));

        registerPart(new PartBuilder().setName("Ore").setPartType(ore));
        registerPart(new PartBuilder().setName("Poor Ore").setPartType(ore));
        registerPart(new PartBuilder().setName("Dense Ore").setPartType(ore));

        registerPart(new PartBuilder().setName("Molten").setPartType(fluid));

        registerPart(new PartBuilder().setName("Armor").setPartType(armor));
    }

    private void registerPart(PartBuilder partBuilder) {
        try {
            partBuilder.build();
        } catch (MaterialException e) {
            Base.instance.getLogger().getLogger().error(e);
        }
    }
}
