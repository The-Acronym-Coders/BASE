package com.teamacronymcoders.base.proxies;

import net.minecraftforge.client.model.obj.OBJLoader;

public class LibClientProxy extends LibCommonProxy {
    public void addOBJDomain() {
        OBJLoader.INSTANCE.addDomain(getMod().getID());
    }
}
