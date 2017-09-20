package com.teamacronymcoders.base.subblocksystem.blocks;

import com.google.common.collect.Maps;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.GeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.ModelType;
import com.teamacronymcoders.base.util.files.templates.TemplateFile;
import com.teamacronymcoders.base.util.files.templates.TemplateManager;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

import static com.teamacronymcoders.base.Reference.MODID;

public abstract class SubBlockBase implements ISubBlock {
    private String name;
    private ResourceLocation textureLocation;
    private ItemStack itemStack;

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
        itemStacks.add(this.itemStack);
    }

    @Override
    public void setRecipes(List<IRecipe> recipes) {

    }

    @Override
    public String getOreDict() {
        return null;
    }

    @Override
    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    @Nonnull
    public ItemStack getItemStack() {
        return this.itemStack.copy();
    }


    @Override
    public Material getMaterial() {
        return Material.IRON;
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

    protected String getModelPrefix() {
        return "";
    }
}
