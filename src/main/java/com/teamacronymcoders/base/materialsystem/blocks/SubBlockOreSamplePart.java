package com.teamacronymcoders.base.materialsystem.blocks;

import com.google.common.collect.Maps;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.GeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.ModelType;
import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.partdata.MaterialPartData;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.subblocksystem.blocks.SubBlockBase;
import com.teamacronymcoders.base.util.ItemStackUtils;
import com.teamacronymcoders.base.util.OreDictUtils;
import com.teamacronymcoders.base.util.files.templates.TemplateFile;
import com.teamacronymcoders.base.util.files.templates.TemplateManager;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

import static com.teamacronymcoders.base.materialsystem.parttype.OreSamplePartType.ACTIVATED_TEXT_DATA_NAME;
import static com.teamacronymcoders.base.materialsystem.parttype.OreSamplePartType.DROP_DATA_NAME;

public class SubBlockOreSamplePart extends SubBlockBase {
    private MaterialPart materialPart;
    private CreativeTabs creativeTabs;

    private ItemStack itemStackToDrop = null;
    private String itemDrop;
    private String activatedText;
    private IBaseMod mod;

    private int hardness = 0;
    private int resistance = 2;
    private int harvestLevel = -1;
    private String harvestTool = null;

    public SubBlockOreSamplePart(MaterialPart materialPart, MaterialUser materialUser, CreativeTabs creativeTab) {
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

        this.mod = materialUser.getMod();
        if (data.containsDataPiece(DROP_DATA_NAME)) {
            itemDrop = data.getDataPiece(DROP_DATA_NAME);
        }
        if (data.containsDataPiece(ACTIVATED_TEXT_DATA_NAME)) {
            activatedText = data.getDataPiece(ACTIVATED_TEXT_DATA_NAME);
        }
    }

    private int setField(MaterialPartData data, String fieldName, int currentLevel) {
        if (data.containsDataPiece(fieldName)) {
            currentLevel = Integer.parseInt(data.getDataPiece(fieldName));
        }

        return currentLevel;
    }

    @Override
    public void getDrops(int fortune, List<ItemStack> itemStacks) {
        ItemStack itemStack = this.getItemStack().copy();
        if (ItemStackUtils.isValid(itemStack)) {
            itemStacks.add(itemStack);
        } else {
            mod.getLogger().fatal("Couldn't drop null ItemStack for " + getMaterialPart().getLocalizedName());
        }
    }

    @Override
    public ItemStack getItemStack() {
        if (itemStackToDrop == null) {
            if (itemDrop != null && !itemDrop.isEmpty()) {
                String[] itemDropArray = itemDrop.split(":");
                String itemString = itemDropArray[0];

                if (itemString.equalsIgnoreCase("oredict")) {
                    itemStackToDrop = OreDictUtils.getPreferredItemStack(itemDropArray[1]);
                } else {
                    int meta = 0;
                    if (itemDropArray.length > 1) {
                        itemString += ":" + itemDropArray[1];
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
                }
            } else {
                this.itemStackToDrop = super.getItemStack();
            }
        }
        return this.itemStackToDrop;
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
    public int getHardness() {
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
        TemplateFile templateFile = TemplateManager.getTemplateFile("ore_sample_block");
        Map<String, String> replacements = Maps.newHashMap();

        Part part = this.getMaterialPart().getPart();
        replacements.put("texture", String.format("%s:blocks/%s", "minecraft", "gravel"));
        replacements.put("particle", String.format("%s:blocks/%s", "minecraft", "stone"));
        replacements.put("ore", String.format("%s:blocks/%s", part.getOwnerId(), part.getShortUnlocalizedName()));
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
        return false;
    }

    @Override
    public boolean isTopSolid() {
        return false;
    }

    @Override
    public BlockFaceShape getBlockFaceShape() {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public AxisAlignedBB getBoundingBox() {
        return new AxisAlignedBB(0.2F, 0.0F, 0.2F, 0.8F, 0.25F, 0.8F);
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    @Override
    public boolean isFullBlock() {
        return false;
    }

    @Override
    public int getLightOpacity() {
        return 0;
    }

    @Override
    public boolean canSilkHarvest() {
        return false;
    }

    @Override
    public void onNeighborChange(World world, BlockPos pos, Block block, BlockPos fromPos) {
        if (!world.getBlockState(pos.down()).isSideSolid(world, pos, EnumFacing.UP)) {
            spawnItemStackEntity(world, this.getItemStack().copy(), pos);
            world.setBlockToAir(pos);
        }
    }

    @Override
    public boolean canPlaceBlockAt(World world, @Nonnull BlockPos pos) {
        return world.getBlockState(pos.down()).isSideSolid(world, pos, EnumFacing.UP);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, EntityPlayer player) {
        if (activatedText != null && !activatedText.isEmpty()) {
            player.sendStatusMessage(new TextComponentString(activatedText), true);
        } else {
            spawnItemStackEntity(world, this.getItemStack().copy(), pos);
        }
        world.setBlockToAir(pos);
        player.swingArm(EnumHand.MAIN_HAND);
        return true;
    }
}

