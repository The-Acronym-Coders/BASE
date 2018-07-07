package com.teamacronymcoders.base.materialsystem.compat.mysticalagriculture;

import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.compat.IMaterialCompat;
import com.teamacronymcoders.base.materialsystem.compat.MaterialCompat;
import com.teamacronymcoders.base.materialsystem.compat.mysticalagriculture.parttype.MACropPartType;
import com.teamacronymcoders.base.materialsystem.parts.PartBuilder;
import com.teamacronymcoders.base.materialsystem.parttype.PartType;

@MaterialCompat("mysticalagriculture")
public class MysticalAgricultureCompat implements IMaterialCompat {
    @Override
    public void doCompat() throws MaterialException {
        MACropPartType maCropPartType = new MACropPartType();
        MaterialSystem.registerPartType(maCropPartType);
        MaterialSystem.registerPart(new PartBuilder().setPartType(maCropPartType).setName("MA CROP").build());
        PartType item = MaterialSystem.getPartType("ITEM");
        MaterialSystem.registerPart(new PartBuilder().setPartType(item).setName("MA Essence").build());
    }
}
