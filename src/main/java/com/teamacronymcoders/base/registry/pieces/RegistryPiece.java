package com.teamacronymcoders.base.registry.pieces;

public @interface RegistryPiece {
    RegistrySide value() default RegistrySide.BOTH;
}
