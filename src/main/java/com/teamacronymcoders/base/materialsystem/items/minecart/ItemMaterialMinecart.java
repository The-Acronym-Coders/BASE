package com.teamacronymcoders.base.materialsystem.items.minecart;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.teamacronymcoders.base.client.models.generator.IHasGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.GeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.ModelType;
import com.teamacronymcoders.base.entities.EntityMinecartBase;
import com.teamacronymcoders.base.items.IHasItemColor;
import com.teamacronymcoders.base.items.minecarts.ItemMinecartBase;
import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.materialsystem.entity.minecart.EntityMaterialMinecart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.util.files.templates.TemplateFile;
import com.teamacronymcoders.base.util.files.templates.TemplateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ItemMaterialMinecart extends ItemMinecartBase implements IHasItemColor, IHasGeneratedModel {
    private final MaterialUser materialUser;
    private final Map<Integer, MaterialPart> itemMaterialParts;

    public ItemMaterialMinecart(MaterialUser materialUser) {
        super("material");
        this.setHasSubtypes(true);
        this.materialUser = materialUser;
        this.itemMaterialParts = new HashMap<>();
    }

    public Map<Integer, MaterialPart> getMaterialParts() {
        return itemMaterialParts;
    }

    @Override
    public List<ItemStack> getAllSubItems(List<ItemStack> itemStacks) {
        itemStacks.addAll(this.getMaterialParts().values()
                .stream()
                .map(materialPart -> new ItemStack(this, 1, materialPart.getId()))
                .collect(Collectors.toList()));
        return itemStacks;
    }

    @Nonnull
    @Override
    public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack) {
        return new EntityMaterialMinecart(world, getMaterialPartFromItemStack(itemStack), itemStack);
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack itemStack) {
        return this.getMaterialPartFromItemStack(itemStack).getLocalizedName();
    }

    @Nonnull
    private MaterialPart getMaterialPartFromItemStack(ItemStack itemStack) {
        return materialUser.getMaterialPart(itemStack.getItemDamage());
    }

    public void addMaterialPart(MaterialPart materialPart) {
        this.getMaterialParts().put(materialPart.getId(), materialPart);
    }

    @Override
    public List<ResourceLocation> getResourceLocations(List<ResourceLocation> resourceLocations) {
        this.getMaterialParts().forEach((id, materialPart) -> resourceLocations.add(
                new ResourceLocation(this.materialUser.getMod().getID(), materialPart.getUnlocalizedName())));
        return resourceLocations;
    }

    @Override
    public int getColorFromItemstack(@Nonnull ItemStack itemStack, int tintIndex) {
        return tintIndex == 0 ? this.getMaterialPartFromItemStack(itemStack).getColor() : -1;
    }

    @Override
    public List<IGeneratedModel> getGeneratedModels() {
        List<IGeneratedModel> models = Lists.newArrayList();
        for (MaterialPart materialPart : this.getMaterialParts().values()) {
            TemplateFile templateFile = TemplateManager.getTemplateFile("item_model");
            Map<String, String> replacements = Maps.newHashMap();
            replacements.put("texture", materialPart.getPart().getOwnerId() + ":items/" + materialPart.getPart().getShortUnlocalizedName());
            templateFile.replaceContents(replacements);
            models.add(new GeneratedModel(materialPart.getUnlocalizedName(), ModelType.ITEM_MODEL, templateFile.getFileContents()));
        }
        return models;
    }
}
