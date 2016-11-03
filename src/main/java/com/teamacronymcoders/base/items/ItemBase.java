package com.teamacronymcoders.base.items;

import com.teamacronymcoders.base.client.models.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemBase extends Item implements IHasModel {
    protected String texturePath;
    protected String name;

    boolean creativeTabSet = false;

    public ItemBase(String name) {
        this("", name);
    }

    public ItemBase(String texturePath, String name) {
        super();
        this.name = name;
        this.texturePath = texturePath;
        if (!texturePath.isEmpty() && !texturePath.endsWith("/"))
            this.texturePath += "/";
        this.setUnlocalizedName(name);
    }

    @Override
    @Nonnull
    public Item setCreativeTab(@Nonnull CreativeTabs tab) {
        if (!creativeTabSet) {
            super.setCreativeTab(tab);
            this.creativeTabSet = true;
        }
        return this;
    }

    @Override
    public List<String> getModelNames(List<String> modelNames) {
        modelNames.add(texturePath + name);
        return modelNames;
    }
}
