package com.teamacronymcoders.base.materialsystem.blocks;

import static com.teamacronymcoders.base.materialsystem.parttype.OrePartType.DROP_DATA_NAME;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.GeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.ModelType;
import com.teamacronymcoders.base.items.IDropTable;
import com.teamacronymcoders.base.items.ItemStackDropTable;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.partdata.MaterialPartData;
import com.teamacronymcoders.base.util.DropUtils;
import com.teamacronymcoders.base.util.files.templates.TemplateFile;
import com.teamacronymcoders.base.util.files.templates.TemplateManager;

import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;

public class SubBlockOrePart extends SubBlockPart {
    private String itemDrop;
    private IBaseMod mod;
    private ResourceLocation variantLocation;
    private IDropTable dropTable = null;

    public SubBlockOrePart(MaterialPart materialPart, ResourceLocation variantLocation, MaterialUser materialUser) {
        super(materialPart, MaterialSystem.materialCreativeTab);
        MaterialPartData data = materialPart.getData();
        this.mod = materialUser.getMod();
        if (data.containsDataPiece(DROP_DATA_NAME)) {
            itemDrop = data.getDataPiece(DROP_DATA_NAME);
        }
        this.variantLocation = variantLocation;
    }

    @Override
    public void getDrops(int fortune, List<ItemStack> itemStacks) {
        if(dropTable == null) {
            if (itemDrop != null && !itemDrop.isEmpty()) {
                dropTable = DropUtils.parseDrops(itemDrop);
            } else {
                dropTable = new ItemStackDropTable(getItemStack());
            }
        }
        
        itemStacks.addAll(dropTable.getDrops(fortune));
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return new ResourceLocation(this.mod.getID(), "materials/" + this.getUnLocalizedName());
    }

    @Override
    public IGeneratedModel getGeneratedModel() {
        TemplateFile templateFile = TemplateManager.getTemplateFile("ore_block_state");
        Map<String, String> replacements = Maps.newHashMap();

        String unlocalizedName = this.getMaterialPart().getPart().getShortUnlocalizedName();
        String variantTexture = new ResourceLocation(variantLocation.getNamespace(),
                "blocks/" + variantLocation.getPath()).toString();
        replacements.put("texture", variantTexture);
        replacements.put("particle", variantTexture);
        String modid = this.getMaterialPart().getPart().getOwnerId();
        replacements.put("ore_shadow", modid + ":blocks/" + unlocalizedName + "_shadow");
        replacements.put("ore", modid + ":blocks/" + unlocalizedName);
        templateFile.replaceContents(replacements);

        return new GeneratedModel("materials/" + this.getMaterialPart().getUnlocalizedName(), ModelType.BLOCKSTATE, templateFile.getFileContents());
    }
    
    @Override
    public BlockRenderLayer getRenderLayer() {
    	return BlockRenderLayer.TRANSLUCENT;
    }

}
