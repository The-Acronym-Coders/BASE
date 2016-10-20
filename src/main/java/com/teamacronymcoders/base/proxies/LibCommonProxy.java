package com.teamacronymcoders.base.proxies;

import com.teamacronymcoders.base.IBaseMod;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class LibCommonProxy {
    private IBaseMod mod;

    public void addOBJDomain() {
        //Only add Client Side
    }

    public void setItemModel(Item item, int metadata, ResourceLocation location) {
        //Only set Client Side
    }

    public void setAllItemModels(Item item, List<ResourceLocation> locations) {
        //Only set Client Side
    }

    public IBaseMod getMod() {
        return this.mod;
    }

    public void setMod(IBaseMod mod) {
        this.mod = mod;
    }
}
