package com.teamacronymcoders.base.modules.materials.blocks;

import com.mojang.realmsclient.gui.ChatFormatting;
import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.api.materials.MaterialType;
import com.teamacronymcoders.base.api.materials.MaterialType.EnumPartType;
import com.teamacronymcoders.base.blocks.BlockBase;
import com.teamacronymcoders.base.blocks.IHasBlockColor;
import com.teamacronymcoders.base.blocks.IHasBlockStateMapper;
import com.teamacronymcoders.base.items.IHasItemColor;
import com.teamacronymcoders.base.modules.materials.items.ItemBlockMaterial;
import com.teamacronymcoders.base.reference.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@SuppressWarnings("deprecation")
public class BlockMaterial extends BlockBase implements IHasBlockColor, IHasItemColor, IHasBlockStateMapper {
    private MaterialType materialType;
    private MaterialType.EnumPartType partType;

    public BlockMaterial(MaterialType materialType, EnumPartType partType, BlockProperties blockProperties) {
        super(Material.IRON);
        this.materialType = materialType;
        this.partType = partType;
        setUnlocalizedName(materialType.getName().toLowerCase() + "." + partType.getLowerCaseName());
        blockProperties.setPropertiesToBlock(this);
        setItemBlock(new ItemBlockMaterial(this));
    }

    @Override
    @Nonnull
    public String getLocalizedName() {
        if (materialType != null) {
            return String.format("%s %s", materialType.getLocalizedName(), Base.languageHelper.none("base.part." + partType.getName().toLowerCase()));
        }

        return ChatFormatting.RED + Base.languageHelper.error("null_part");
    }

    @Override
    @Nonnull
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public List<String> getModelNames(List<String> names) {
        names.add(this.partType.getName().toLowerCase());
        return names;
    }


    @Override
    public int colorMultiplier(IBlockState state, @Nullable IBlockAccess world, @Nullable BlockPos pos, int tintIndex) {
        return materialType.getColour().getRGB();
    }

    @Override
    public ResourceLocation getResourceLocation(IBlockState blockState) {
        return new ResourceLocation(Reference.MODID, partType.getLowerCaseName());
    }

    @Override
    public int getColorFromItemstack(@Nonnull ItemStack stack, int tintIndex) {
        if (materialType != null && tintIndex == 0) {
            return materialType.getColour().getRGB();
        }
        return 0xFFFFFF;
    }
}
