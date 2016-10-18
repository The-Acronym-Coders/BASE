package com.teamacronymcoders.base.proxies;

import com.teamacronymcoders.base.IBaseMod;

public class LibCommonProxy {
    private IBaseMod mod;

    public void addOBJDomain() {
        //Only add Client Side
    }

    public IBaseMod getMod() {
        return this.mod;
    }

    public void setMod(IBaseMod mod) {
        this.mod = mod;
    }
}
