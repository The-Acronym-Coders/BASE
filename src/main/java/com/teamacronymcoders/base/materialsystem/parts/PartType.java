package com.teamacronymcoders.base.materialsystem.parts;

import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.materials.Material;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Consumer;

public class PartType {
    private String name;
    private Consumer<MaterialPart> registerMethod;

    public PartType(@Nonnull String name, @Nullable Consumer<MaterialPart> registerMethod) {
        this.name = name;
        if (registerMethod == null) {
            registerMethod = materialPart -> {};
        }
        this.registerMethod = registerMethod;
    }

    @Nonnull
    public String getName() {
        return this.name;
    }

    public void setup(@Nonnull MaterialPart materialPart) {
        registerMethod.accept(materialPart);
    }
}
