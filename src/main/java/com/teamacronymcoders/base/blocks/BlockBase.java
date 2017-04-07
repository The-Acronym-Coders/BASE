package com.teamacronymcoders.base.blocks;

import com.teamacronymcoders.base.client.models.IHasModel;
import net.minecraft.block.material.Material;

import java.util.List;

public class BlockBase extends BlockBaseNoModel implements IHasModel {
    public BlockBase(Material mat) {
        super(mat);
    }

    public BlockBase(Material mat, String name) {
        super(mat, name);
    }

    @Override
    public List<String> getModelNames(List<String> modelNames) {
        String name = this.getUnlocalizedName();
        if (name.startsWith("tile.")) {
            name = name.substring(5);
        }
        modelNames.add(name);
        return modelNames;
    }
}
