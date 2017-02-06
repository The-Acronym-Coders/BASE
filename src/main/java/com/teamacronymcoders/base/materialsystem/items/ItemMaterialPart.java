package com.teamacronymcoders.base.materialsystem.items;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.api.materials.MaterialRegistry;
import com.teamacronymcoders.base.api.materials.MaterialType;
import com.teamacronymcoders.base.items.IHasItemColor;
import com.teamacronymcoders.base.items.IHasItemMeshDefinition;
import com.teamacronymcoders.base.items.ItemBase;
import com.teamacronymcoders.base.materialsystem.MaterialPart;
import com.teamacronymcoders.base.materialsystem.MaterialsSystem;
import com.teamacronymcoders.base.materialsystem.parts.PartType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
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
                materialPart.getPart().getPartType() == PartType.ITEM).forEach(materialPart -> {
            ItemStack itemStack = new ItemStack(this, 1, 0);
            itemStack.setTagInfo("material", new NBTTagString(materialPart.getMaterial().getUnlocalizedName()));
            itemStack.setTagInfo("part", new NBTTagString(materialPart.getPart().getUnlocalizedName()));
            itemStacks.add(itemStack);
            materialPart.setItemStack(itemStack);
        });
        return itemStacks;
    }

    @Override
    public ResourceLocation getResourceLocation(ItemStack itemStack) {
        return getMaterialParkFromItemStack(itemStack).getTextureLocation();
    }

    @Override
    public int getColorFromItemstack(@Nonnull ItemStack itemStack, int tintIndex) {
        MaterialPart materialPart = getMaterialParkFromItemStack(itemStack);
        if (materialPart != null && tintIndex == 0) {
            return materialPart.getMaterial().getColor().getRGB();
        }
        return 0xFFFFFF;
    }

    private MaterialPart getMaterialParkFromItemStack(ItemStack itemStack) {
        NBTTagCompound nbtTagCompound = itemStack.getTagCompound();
        if(nbtTagCompound == null) {
            nbtTagCompound = new NBTTagCompound();
        }
        String material = nbtTagCompound.getString("material");
        String part = nbtTagCompound.getString("part");
        return MaterialsSystem.MATERIAL_PARTS.getValue(new ResourceLocation(material, part));
    }

    public void registerItemVariant(ResourceLocation resourceLocation) {
        Base.instance.getModelLoader().registerModelVariant(this, resourceLocation);
    }
}
