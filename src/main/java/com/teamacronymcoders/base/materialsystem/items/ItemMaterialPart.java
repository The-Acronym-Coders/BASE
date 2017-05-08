package com.teamacronymcoders.base.materialsystem.items;

import com.teamacronymcoders.base.items.IHasItemColor;
import com.teamacronymcoders.base.items.IHasItemMeshDefinition;
import com.teamacronymcoders.base.items.IHasOreDict;
import com.teamacronymcoders.base.items.ItemBase;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemMaterialPart extends ItemBase implements IHasItemMeshDefinition, IHasItemColor, IHasOreDict {
    private Map<Integer, MaterialPart> itemMaterialParts;
    private MaterialSystem materialSystem;

    public ItemMaterialPart(MaterialSystem materialSystem) {
        super("material_part");
        this.setHasSubtypes(true);
        this.materialSystem = materialSystem;
    }

    @Override
    public List<String> getModelNames(List<String> modelNames) {
        return modelNames;
    }

    @Override
    public List<ItemStack> getAllSubItems(List<ItemStack> itemStacks) {
        this.getItemMaterialParts().values().forEach(materialPart -> itemStacks.add(materialPart.getItemStack()));
        return itemStacks;
    }

    @Override
    public ResourceLocation getResourceLocation(ItemStack itemStack) {
        return this.getMaterialParkFromItemStack(itemStack).getTextureLocation();
    }

    @Override
    public int getColorFromItemstack(@Nonnull ItemStack itemStack, int tintIndex) {
        return tintIndex == 0 ? this.getMaterialParkFromItemStack(itemStack).getColor() : -1;
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack itemStack) {
        return this.getMaterialParkFromItemStack(itemStack).getLocalizedName();
    }

    @Override
    public boolean hasEffect(ItemStack itemStack) {
        return this.getMaterialParkFromItemStack(itemStack).hasEffect();
    }

    @Nonnull
    private MaterialPart getMaterialParkFromItemStack(ItemStack itemStack) {
        MaterialPart materialPart = materialSystem.getMaterialPart(itemStack.getItemDamage());
        return materialPart != null ? materialPart : MaterialSystem.MISSING_MATERIAL_PART;
    }

    public void registerItemVariant(ResourceLocation resourceLocation) {
        materialSystem.getMod().getModelLoader().registerModelVariant(this, resourceLocation);
    }

    public Map<Integer, MaterialPart> getItemMaterialParts() {
        if (itemMaterialParts == null) {
            itemMaterialParts = new HashMap<>();
        }
        return itemMaterialParts;
    }

    @Nonnull
    @Override
    public Map<ItemStack, String> getOreDictNames(@Nonnull Map<ItemStack, String> names) {
        this.getItemMaterialParts().values().forEach(materialPart -> materialPart.setOreDict(names));
        return names;
    }

    public void addMaterialPart(int id, MaterialPart materialPart) {
        this.getItemMaterialParts().put(id, materialPart);
    }
}
