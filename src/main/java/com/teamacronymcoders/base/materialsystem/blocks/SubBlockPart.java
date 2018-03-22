package com.teamacronymcoders.base.materialsystem.blocks;

import com.google.common.collect.Maps;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.GeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.ModelType;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.partdata.MaterialPartData;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.subblocksystem.blocks.SubBlockBase;
import com.teamacronymcoders.base.util.files.templates.TemplateFile;
import com.teamacronymcoders.base.util.files.templates.TemplateManager;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public class SubBlockPart extends SubBlockBase {
    private MaterialPart materialPart;
    private CreativeTabs creativeTabs;

    private float hardness = 5;
    private int resistance = 30;
    private int harvestLevel = 1;
    private String harvestTool = "pickaxe";

    public SubBlockPart(MaterialPart materialPart, CreativeTabs creativeTab) {
        super(materialPart.getUnlocalizedName());
        this.materialPart = materialPart;
        MaterialPartData data = this.materialPart.getData();
        hardness = setField(data, "hardness", hardness);
        resistance = setField(data, "resistance", resistance);
        harvestLevel = setField(data, "harvestLevel", harvestLevel);

        if (data.containsDataPiece("harvestTool")) {
            harvestTool = data.getDataPiece("harvestTool");
        }
        this.creativeTabs = creativeTab;
    }

    private int setField(MaterialPartData data, String fieldName, int currentLevel) {
        if (data.containsDataPiece(fieldName)) {
            currentLevel = Integer.parseInt(data.getDataPiece(fieldName));
        }

        return currentLevel;
    }

    private float setField(MaterialPartData data, String fieldName, float currentLevel) {
        if (data.containsDataPiece(fieldName)) {
            currentLevel = Float.parseFloat(data.getDataPiece(fieldName));
        }

        return currentLevel;
    }

    public void setHardness(float hardness) {
        this.hardness = hardness;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public void setHarvestLevel(int harvestLevel) {
        this.harvestLevel = harvestLevel;
    }

    public void setHarvestTool(String harvestTool) {
        this.harvestTool = harvestTool;
    }

    @Override
    public String getLocalizedName() {
        return this.materialPart.getLocalizedName();
    }

    @Override
    public String getUnLocalizedName() {
        return this.materialPart.getUnlocalizedName();
    }

    @Override
    public int getColor() {
        return materialPart.getColor();
    }

    @Override
    public float getHardness() {
        return hardness;
    }

    @Override
    public int getResistance() {
        return resistance;
    }

    @Override
    public int getHarvestLevel() {
        return harvestLevel;
    }

    @Override
    public String getHarvestTool() {
        return harvestTool;
    }

    @Override
    public String getOreDict() {
        return this.materialPart.getOreDictString();
    }

    @Nullable
    @Override
    public CreativeTabs getCreativeTab() {
        return creativeTabs;
    }

    @Override
    public ResourceLocation getTextureLocation() {
        ResourceLocation location = materialPart.getTextureLocation();
        return new ResourceLocation(location.getResourceDomain(), this.getModelPrefix() + this.getUnLocalizedName());
    }

    @Override
    public IGeneratedModel getGeneratedModel() {
        TemplateFile templateFile = TemplateManager.getTemplateFile("colored_block");
        Map<String, String> replacements = Maps.newHashMap();

        Part part = materialPart.getPart();
        replacements.put("texture", String.format("%s:blocks/%s", part.getOwnerId(), part.getShortUnlocalizedName()));
        templateFile.replaceContents(replacements);

        return new GeneratedModel(this.getModelPrefix() + this.getUnLocalizedName(), ModelType.BLOCKSTATE,
                templateFile.getFileContents());
    }

    @Override
    protected String getModelPrefix() {
        return "materials/";
    }

    public MaterialPart getMaterialPart() {
        return materialPart;
    }

    @Override
    public boolean isSideSolid(EnumFacing side) {
        return true;
    }

    @Override
    public boolean isTopSolid() {
        return true;
    }

    @Override
    public BlockFaceShape getBlockFaceShape() {
        return BlockFaceShape.SOLID;
    }

    @Override
    public AxisAlignedBB getBoundingBox() {
        return Block.FULL_BLOCK_AABB;
    }

    @Override
    public boolean isFullCube() {
        return true;
    }

    @Override
    public boolean isOpaqueCube() {
        return true;
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public boolean isFullBlock() {
        return true;
    }

    @Override
    public int getLightOpacity() {
        return 255;
    }

    @Override
    public boolean canSilkHarvest() {
        return true;
    }

    @Override
    public boolean canPlaceBlockAt(World world, @Nonnull BlockPos pos) {
        return world.getBlockState(pos).getBlock().isReplaceable(world, pos);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, EntityPlayer player) {
        return false;
    }
}
