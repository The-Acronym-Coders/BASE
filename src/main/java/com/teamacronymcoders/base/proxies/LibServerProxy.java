package com.teamacronymcoders.base.proxies;

import com.teamacronymcoders.base.registrysystem.pieces.RegistrySide;

public class LibServerProxy extends LibCommonProxy {

    @Override
    public RegistrySide getRegistrySide() {
        return RegistrySide.SERVER;
    }
}
