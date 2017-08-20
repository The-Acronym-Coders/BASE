package com.teamacronymcoders.base.materialsystem.compat.base;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.materialsystem.compat.IMaterialCompat;
import com.teamacronymcoders.base.materialsystem.compat.MaterialCompat;
import com.teamacronymcoders.base.materialsystem.parts.PartBuilder;
import com.teamacronymcoders.base.materialsystem.parttype.*;

@MaterialCompat("base")
public class BaseCompat implements IMaterialCompat {
    @Override
    public void doCompat() throws MaterialException {
        MaterialUser user = Base.instance.getMaterialUser();
        
        PartType item = new ItemPartType();
        PartType block = new BlockPartType();
        PartType ore = new OrePartType();
        PartType fluid = new FluidPartType();

        MaterialSystem.registerPartType(item);
        MaterialSystem.registerPartType(block);
        MaterialSystem.registerPartType(ore);
        MaterialSystem.registerPartType(fluid);

        registerPart(new PartBuilder(user).setName("Ingot").setPartType(item));
        registerPart(new PartBuilder(user).setName("Beam").setPartType(item));
        registerPart(new PartBuilder(user).setName("Gear").setPartType(item));
        registerPart(new PartBuilder(user).setName("Bolt").setPartType(item));
        registerPart(new PartBuilder(user).setName("Dust").setPartType(item));
        registerPart(new PartBuilder(user).setName("Nugget").setPartType(item));
        registerPart(new PartBuilder(user).setName("Rod").setPartType(item));
        registerPart(new PartBuilder(user).setName("Plate").setPartType(item));
        registerPart(new PartBuilder(user).setName("Dense Plate").setPartType(item));

        registerPart(new PartBuilder(user).setName("Block").setPartType(block));

        registerPart(new PartBuilder(user).setName("Ore").setPartType(ore));
        registerPart(new PartBuilder(user).setName("Poor Ore").setPartType(ore));
        registerPart(new PartBuilder(user).setName("Dense Ore").setPartType(ore));

        registerPart(new PartBuilder(user).setName("Fluid").setPartType(fluid));
    }

    private void registerPart(PartBuilder partBuilder) {
        try {
            partBuilder.build();
        } catch (MaterialException e) {
            Base.instance.getLogger().getLogger().error(e);
        }
    }
}
