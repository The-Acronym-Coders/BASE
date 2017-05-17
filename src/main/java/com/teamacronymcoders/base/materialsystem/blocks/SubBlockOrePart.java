package com.teamacronymcoders.base.materialsystem.blocks;

import com.google.common.collect.Maps;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.client.models.wrapped.WrappedBlockEntry;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPartData;
import com.teamacronymcoders.base.subblocksystem.blocks.BlockSubBlockHolder;
import com.teamacronymcoders.base.util.OreDictUtils;
import com.teamacronymcoders.base.util.TextUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.List;
import java.util.Map;

public class SubBlockOrePart extends SubBlockPart {
    private ItemStack itemStackToDrop = null;
    private String itemDrop;
    private IBaseMod mod;

    public SubBlockOrePart(MaterialPart materialPart, ResourceLocation variantLocation, MaterialSystem materialSystem) {
        super(materialPart, materialSystem.materialCreativeTab);
        MaterialPartData data = materialPart.getData();
        this.mod = materialSystem.getMod();
        if (data.containsDataPiece("drop")) {
            itemDrop = data.getDataPiece("drop");
        }

        Map<ResourceLocation, Boolean> layers = Maps.newHashMap();
        layers.put(new ResourceLocation("base", "blocks/" + materialPart.getPart().getUnlocalizedName() + "_shadow"), true);
        layers.put(new ResourceLocation("base", "blocks/" + materialPart.getPart().getUnlocalizedName()), true);
        this.mod.getModelLoader().registerWrappedModel(this.getTextureLocation(), new WrappedBlockEntry(variantLocation, layers, materialPart.getColor()));
    }

    @Override
    public void getDrops(int fortune, List<ItemStack> itemStacks) {
        if (itemStackToDrop == null) {
            if (itemDrop != null && !itemDrop.isEmpty()) {
                String[] itemDropArray = itemDrop.split(":");
                String itemString = itemDropArray[0];
                int meta = 0;
                if (itemDropArray.length > 1) {
                    itemString += itemDropArray[1];
                    if (itemDropArray.length > 2) {
                        meta = Integer.parseInt(itemDropArray[2]);
                    }
                }
                Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemString));
                if (item != null) {
                    itemStackToDrop = new ItemStack(item, 1, meta);
                } else {
                    this.mod.getLogger().error("Could not find Item for name: " + itemString);
                }
            } else {
                this.itemStackToDrop = this.itemStack;
            }
        }
        if (itemStackToDrop != null) {
            itemStacks.add(itemStackToDrop.copy());
        } else {
            mod.getLogger().fatal("Couldn't drop null ItemStack for " + getMaterialPart().getLocalizedName());
        }
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return new ResourceLocation(this.mod.getID(), this.getUnLocalizedName());
    }
}
