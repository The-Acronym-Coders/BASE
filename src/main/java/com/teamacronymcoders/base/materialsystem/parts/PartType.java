package com.teamacronymcoders.base.materialsystem.parts;

import com.teamacronymcoders.base.materialsystem.MaterialPart;
import com.teamacronymcoders.base.materialsystem.blocks.SubBlockPart;
import com.teamacronymcoders.base.materialsystem.materials.Material;
import com.teamacronymcoders.base.subblocksystem.SubBlockSystem;
import com.teamacronymcoders.base.subblocksystem.blocks.ISubBlock;

import java.util.function.Consumer;
import java.util.function.Function;

public class PartType {
    private String name;
    private Consumer<MaterialPart> registerMethod;

    public PartType(String name) {
        this.name = name;
        this.registerMethod = materialPart -> {};
    }

    public PartType(String name, Consumer<MaterialPart> registerMethod) {
        this.name = name;
        this.registerMethod = registerMethod;
    }

    public String getName() {
        return this.name;
    }

    public void setup(MaterialPart materialPart) {
        registerMethod.accept(materialPart);
    }
}
