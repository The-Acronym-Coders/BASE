package com.teamacronymcoders.base.materialsystem.items;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.items.IHasItemColor;
import com.teamacronymcoders.base.items.IHasItemMeshDefinition;
import com.teamacronymcoders.base.items.ItemBase;
import com.teamacronymcoders.base.materialsystem.MaterialPart;
import com.teamacronymcoders.base.materialsystem.MaterialsSystem;
import com.teamacronymcoders.base.materialsystem.parts.PartType;
import com.teamacronymcoders.base.materialsystem.parts.ProvidedParts;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemMaterialPart extends ItemBase implements IHasItemMeshDefinition, IHasItemColor {
    public ItemMaterialPart() {
        super("material_part");
        this.setHasSubtypes(true);
    }

    @Override
    public List<String> getModelNames(List<String> modelNames) {
        return modelNames;
    }

    @Override
    public List<ItemStack> getAllSubItems(List<ItemStack> itemStacks) {
        MaterialsSystem.MATERIAL_PARTS.getValues().stream().filter(materialPart ->
                materialPart.getPart().getPartType() == ProvidedParts.ITEM).forEach(materialPart -> {
            itemStacks.add(materialPart.getItemStack());
        });
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
        NBTTagCompound nbtTagCompound = itemStack.getTagCompound();
        if(nbtTagCompound == null) {
            nbtTagCompound = new NBTTagCompound();
        }
        String material = nbtTagCompound.getString("material");
        String part = nbtTagCompound.getString("part");
        MaterialPart materialPart = MaterialsSystem.MATERIAL_PARTS.getValue(new ResourceLocation(material, part));
        return materialPart != null ? materialPart : MaterialsSystem.MISSING_MATERIAL_PART;
    }

    public void registerItemVariant(ResourceLocation resourceLocation) {
        Base.instance.getModelLoader().registerModelVariant(this, resourceLocation);
    }
}
