package com.teamacronymcoders.base.subblocksystem.blocks;

import com.google.common.collect.Maps;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.GeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.ModelType;
import com.teamacronymcoders.base.util.files.templates.TemplateFile;
import com.teamacronymcoders.base.util.files.templates.TemplateManager;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

import static com.teamacronymcoders.base.Reference.MODID;

public abstract class SubBlockBase implements ISubBlock {
    private String name;
    private ResourceLocation textureLocation;
    private Block block;
    private int meta;
    
    public SubBlockBase(String name) {
        this.name = name;
        this.textureLocation = new ResourceLocation(MODID, this.getModelPrefix() + name);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String getUnLocalizedName() {
        return "base.subblock." + name;
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return this.textureLocation;
    }

    @Override
    public int getColor() {
        return -1;
    }

    @Override
    public void getDrops( int fortune, List<ItemStack> itemStacks) {
        itemStacks.add(this.getItemStack());
    }

    @Override
    public void setRecipes(List<IRecipe> recipes) {

    }

    @Override
    public String getOreDict() {
        return "";
    }

    @Override
    @Nonnull
    public ItemStack getItemStack() {
        return new ItemStack(block, 1, meta);
    }
    
    @Override
    public Material getMaterial() {
        return Material.IRON;
    }
    
    @Nonnull
    @Override
    public IBlockState getBlockState() {
        return this.block.getDefaultState().withProperty(BlockSubBlockHolder.SUB_BLOCK_NUMBER, meta);
    }
    
    public Block getBlock() {
        return block;
    }
    
    @Override
    public void setBlock(Block block) {
        this.block = block;
    }
    
    @Override
    public void setMeta(int meta) {
        this.meta = meta;
    }

    @Override
    public void onNeighborChange(World world, BlockPos pos, Block block, BlockPos fromPos) {

    }

    @Override
    public IGeneratedModel getGeneratedModel() {
        TemplateFile templateFile = TemplateManager.getTemplateFile("sub_block_state");
        Map<String, String> replacements = Maps.newHashMap();

        replacements.put("texture", new ResourceLocation(this.getTextureLocation().getResourceDomain(),
                "blocks/" + this.getTextureLocation().getResourcePath()).toString());
        templateFile.replaceContents(replacements);

        return new GeneratedModel(this.getModelPrefix() + this.getUnLocalizedName(), ModelType.BLOCKSTATE,
                templateFile.getFileContents());
    }

    protected void spawnItemStackEntity(World world, ItemStack itemStack, BlockPos blockPos) {
        EntityItem entityItem = new EntityItem(world, blockPos.getX(), blockPos.getY() + 0.5, blockPos.getZ(), itemStack);
        if (!world.isRemote) {
            world.spawnEntity(entityItem);
        }
    }

    protected String getModelPrefix() {
        return "";
    }
}
