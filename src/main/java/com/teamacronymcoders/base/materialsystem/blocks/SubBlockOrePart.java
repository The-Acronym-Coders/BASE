package com.teamacronymcoders.base.materialsystem.blocks;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPartData;
import com.teamacronymcoders.base.util.OreDictUtils;
import com.teamacronymcoders.base.util.TextUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.List;

public class SubBlockOrePart extends SubBlockPart {
    ItemStack itemStackToDrop = null;
    String oreDictDrop = "ore";
    IBlockState variant = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("stone")).getDefaultState();
    public SubBlockOrePart(MaterialPart materialPart) {
        super(materialPart);
        MaterialPartData data = materialPart.getData();
        if (data.getDataPiece("dropType") instanceof String) {
            oreDictDrop = ((String) data.getDataPiece("dropType"));
        }
        if (data.getDataPiece("variant") instanceof String) {
            String variantString = ((String) data.getDataPiece("variant"));
            String[] variantArray = variantString.split(":");
            String blockString;
            int meta = 0;
            if (variantArray.length < 2) {
                blockString = variantArray[0];
            } else {
                blockString = variantArray[0] + ":" + variantArray[1];
            }
            if (variantArray.length == 3) {
                meta = Integer.parseInt(variantArray[2]);
            }
            Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(blockString));
            if (block != null) {
                //noinspection deprecation
                variant = block.getStateFromMeta(meta);
            } else {
                Base.instance.getLogger().fatal("Couldn't find block for variant string: " + blockString);
            }

        }
    }

    @Override
    public void getDrops(IBlockState blockState, int fortune, List<ItemStack> itemStacks) {
        if (itemStackToDrop == null) {
            String oreDictName = oreDictDrop + TextUtils.toSnakeCase(this.getMaterialPart().getMaterial().getName());
            itemStackToDrop = OreDictUtils.getPreferredItemStack(oreDictName);
        }
        if (itemStackToDrop != null) {
            itemStacks.add(itemStackToDrop.copy());
        } else {
            Base.instance.getLogger().fatal("Couldn't drop null ItemStack for " + getMaterialPart().getLocalizedName());
        }
    }

    public IBlockState getBlockStateForOreStone() {
        return variant;
    }
}
