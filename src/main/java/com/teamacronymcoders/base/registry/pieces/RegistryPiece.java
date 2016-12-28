package com.teamacronymcoders.base.registry.pieces;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface RegistryPiece {
    RegistrySide value() default RegistrySide.BOTH;

    String modid() default "";
}
