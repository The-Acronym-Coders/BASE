package com.teamacronymcoders.base.materialsystem.items;

import com.teamacronymcoders.base.items.IHasItemColor;
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

public class ItemMaterialPart extends ItemBase implements IHasItemColor, IHasOreDict {
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
    public int getColorFromItemstack(@Nonnull ItemStack itemStack, int tintIndex) {
        return tintIndex == 0 ? this.getMaterialParkFromItemStack(itemStack).getColor() : -1;
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack itemStack) {
        return this.getMaterialParkFromItemStack(itemStack).getLocalizedName();
    }

    @Override
    public boolean hasEffect(@Nonnull ItemStack itemStack) {
        return this.getMaterialParkFromItemStack(itemStack).hasEffect();
    }

    @Nonnull
    private MaterialPart getMaterialParkFromItemStack(ItemStack itemStack) {
        MaterialPart materialPart = materialSystem.getMaterialPart(itemStack.getItemDamage());
        return materialPart != null ? materialPart : MaterialSystem.MISSING_MATERIAL_PART;
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
        for (Map.Entry<Integer, MaterialPart> entry : this.getItemMaterialParts().entrySet()) {
            names.put(new ItemStack(this, 1, entry.getKey()), entry.getValue().getOreDictString());
        }
        return names;
    }

    public void addMaterialPart(int id, MaterialPart materialPart) {
        this.getItemMaterialParts().put(id, materialPart);
    }

    @Override
    public List<ResourceLocation> getResourceLocations(List<ResourceLocation> resourceLocations) {
        this.getItemMaterialParts().forEach((id, materialPart) -> resourceLocations.add(materialPart.getTextureLocation()));
        return resourceLocations;
    }
}
