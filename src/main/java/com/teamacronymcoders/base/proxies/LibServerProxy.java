package com.teamacronymcoders.base.proxies;

import com.teamacronymcoders.base.modulesystem.IModule;
import com.teamacronymcoders.base.modulesystem.proxies.IModuleProxy;
import com.teamacronymcoders.base.registry.pieces.RegistrySide;

public class LibServerProxy extends LibCommonProxy {

    @Override
    public IModuleProxy getModuleProxy(IModule module) {
        return getModuleProxy(module.getServerProxyPath());
    }

    @Override
    public RegistrySide getRegistrySide() {
        return RegistrySide.SERVER;
    }
}
