package com.teamacronymcoders.base.blocks;

import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.items.itemblocks.ItemBlockModel;
import net.minecraft.block.material.Material;

import java.util.List;

public class BlockBase extends BlockBaseNoModel implements IHasModel {
    public BlockBase(Material mat, String name) {
        super(mat, name);
        this.setItemBlock(new ItemBlockModel<>(this));
    }

    @Override
    public List<String> getModelNames(List<String> modelNames) {
        modelNames.add(this.getName());
        return modelNames;
    }
}
