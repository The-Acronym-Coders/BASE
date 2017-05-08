package com.teamacronymcoders.base.materialsystem.blocks;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.client.models.wrapped.WrappedBlockEntry;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPartData;
import com.teamacronymcoders.base.materialsystem.materials.Material;
import com.teamacronymcoders.base.util.OreDictUtils;
import com.teamacronymcoders.base.util.TextUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

import static net.minecraftforge.fml.common.registry.ForgeRegistries.BLOCKS;

public class SubBlockOrePart extends SubBlockPart {
    private ItemStack itemStackToDrop = null;
    private String oreDictDrop = "ore";
    private IBaseMod mod;

    public SubBlockOrePart(MaterialPart materialPart, IBlockState variant, MaterialSystem materialSystem) {
        super(materialPart, materialSystem.materialCreativeTab);
        MaterialPartData data = materialPart.getData();
        this.mod = materialSystem.getMod();
        if (data.containsDataPiece("dropType")) {
            oreDictDrop = data.getDataPiece("dropType");
        }

        List<ResourceLocation> layers = Lists.newArrayList();
        layers.add(new ResourceLocation("base", materialPart.getPart().getName() + "_shadow"));
        layers.add(new ResourceLocation("base", materialPart.getPart().getName()));
        this.mod.getModelLoader().registerWrappedModel(this.getTextureLocation(), new WrappedBlockEntry(variant, layers));
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
            mod.getLogger().fatal("Couldn't drop null ItemStack for " + getMaterialPart().getLocalizedName());
        }
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return new ResourceLocation(this.mod.getID(), this.getUnLocalizedName());
    }
}
