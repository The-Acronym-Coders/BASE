package com.teamacronymcoders.base.items.tools;

import com.teamacronymcoders.base.client.models.IHasModel;
import net.minecraft.item.ItemSword;

import java.util.List;

public class ItemSwordBase extends ItemSword implements IHasModel {
    protected String texturePath;
    protected String name;

    public ItemSwordBase(ToolMaterial material, String name) {
        this(material, "", name);
    }

    public ItemSwordBase(ToolMaterial material, String texturePath, String name) {
        super(material);
        this.name = name;
        this.texturePath = texturePath;
        if(!texturePath.isEmpty() && !texturePath.endsWith("/"))
            this.texturePath += "/";
        this.setUnlocalizedName(name);
    }

    @Override
    public List<String> getModelNames(List<String> modelNames) {
        modelNames.add(texturePath + name);
        return modelNames;
    }

}
