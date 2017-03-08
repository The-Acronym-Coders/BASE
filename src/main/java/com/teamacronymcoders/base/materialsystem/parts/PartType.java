package com.teamacronymcoders.base.materialsystem.parts;

import com.teamacronymcoders.base.materialsystem.MaterialPart;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class PartType {
    private String name;
    private Consumer<MaterialPart> registerMethod;

    public PartType(@Nonnull String name) {
        this(name, materialPart -> {
        });
    }

    public PartType(@Nonnull String name, @Nonnull Consumer<MaterialPart> registerMethod) {
        this.name = name;
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
