package com.teamacronymcoders.base.subblocksystem.blocks;

import com.teamacronymcoders.base.blocks.BlockBase;
import net.minecraft.block.material.Material;

public class BlockSubBlockHolder extends BlockBase {
    public BlockSubBlockHolder(int number) {
        super(Material.IRON, "sub_block_holder_" + number);
    }


}
