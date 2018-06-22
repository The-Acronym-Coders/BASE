package com.teamacronymcoders.base.materialsystem.blocks;

import com.google.common.collect.Maps;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.GeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.ModelType;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.partdata.DataPartParsers;
import com.teamacronymcoders.base.materialsystem.partdata.MaterialPartData;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.util.ItemStackUtils;
import com.teamacronymcoders.base.util.OreDictUtils;
import com.teamacronymcoders.base.util.files.templates.TemplateFile;
import com.teamacronymcoders.base.util.files.templates.TemplateManager;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
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
import java.util.List;
import java.util.Map;

import static com.teamacronymcoders.base.materialsystem.parttype.OreSamplePartType.ACTIVATED_TEXT_DATA_NAME;
import static com.teamacronymcoders.base.materialsystem.parttype.OreSamplePartType.REQUIRE_TOOL_DATA_NAME;
import static com.teamacronymcoders.base.materialsystem.parttype.OreSamplePartType.DROP_DATA_NAME;

public class SubBlockOreSamplePart extends SubBlockPart {
    private ItemStack itemStackToDrop = null;
    private String itemDrop;
    private boolean requireTool;
    private String activatedText;
    private IBaseMod mod;

    public SubBlockOreSamplePart(MaterialPart materialPart, MaterialUser materialUser) {
        super(materialPart, MaterialSystem.materialCreativeTab);
        MaterialPartData data = materialPart.getData();

        setHardness(data.getValue("hardness", 0.125F, DataPartParsers::getFloat));
        setResistance(data.getValue("resistance", 2, DataPartParsers::getInt));
        setHarvestLevel(data.getValue("harvestLevel", -1, DataPartParsers::getInt));
        setHarvestTool(data.getValue("harvestTool", null, DataPartParsers::getString));

        mod = materialUser.getMod();
        itemDrop = data.getValue(DROP_DATA_NAME, itemDrop, DataPartParsers::getString);
        requireTool = data.getValue(REQUIRE_TOOL_DATA_NAME, requireTool, DataPartParsers::getBool);
        activatedText = data.getValue(ACTIVATED_TEXT_DATA_NAME, activatedText, DataPartParsers::getString);
    }

    @Override
    public Material getMaterial() {
        if (requireTool) { return Material.ROCK; }
        else { return Material.GROUND; }
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
            if (!requireTool){ spawnItemStackEntity(world, this.getItemStack().copy(), pos); }
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
            world.setBlockToAir(pos);
        } else if (!requireTool) {
            spawnItemStackEntity(world, this.getItemStack().copy(), pos);
            world.setBlockToAir(pos);
        }
        player.swingArm(EnumHand.MAIN_HAND);
        return true;
    }
}
