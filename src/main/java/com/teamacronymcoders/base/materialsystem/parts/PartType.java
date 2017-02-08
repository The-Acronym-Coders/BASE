package com.teamacronymcoders.base.materialsystem.parts;

import com.teamacronymcoders.base.materialsystem.MaterialPart;
import com.teamacronymcoders.base.materialsystem.blocks.SubBlockPart;
import com.teamacronymcoders.base.subblocksystem.SubBlockSystem;
import com.teamacronymcoders.base.subblocksystem.blocks.ISubBlock;

public enum PartType {
    ITEM, ORE,
    BLOCK {
        public void setup(MaterialPart materialPart) {
            SubBlockSystem.registerSubBlock(new SubBlockPart(materialPart));
        }
    };

    public void setup(MaterialPart materialPart) {

    }
}
