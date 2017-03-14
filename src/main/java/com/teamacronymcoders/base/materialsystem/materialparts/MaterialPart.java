package com.teamacronymcoders.base.materialsystem.materialparts;

import com.teamacronymcoders.base.Reference;
import com.teamacronymcoders.base.materialsystem.MaterialsSystem;
import com.teamacronymcoders.base.materialsystem.materials.Material;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.materialsystem.parts.PartType;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

public class MaterialPart {
    private Material material;
    private Part part;
    private ResourceLocation textureLocation;
    private boolean colorize;
    private MaterialPartData data;
    private ItemStack itemStack;

    public MaterialPart(Material material, Part part) {
        this.setMaterial(material);
        this.setPart(part);
        this.setTextureLocation(new ResourceLocation(Reference.MODID, part.getUnlocalizedName()));
        this.colorize = true;
    }

    public String getName() {
        return material.getUnlocalizedName() + "." + part.getUnlocalizedName();
    }

    public String getLocalizedName() {
        return String.format("%s %s", material.getName(), I18n.format(part.getUnlocalizedName()));
    }

    public boolean hasEffect() {
        return this.getMaterial().isHasEffect();
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public ItemStack getItemStack() {
        if(itemStack == null) {
            itemStack = new ItemStack(MaterialsSystem.ITEM_MATERIAL_PART, 1, MaterialsSystem.getMaterialPartId(this));
        }

        return itemStack;
    }

    public ResourceLocation getTextureLocation() {
        return textureLocation;
    }

    public void setTextureLocation(ResourceLocation textureLocation) {
        this.textureLocation = textureLocation;
        MaterialsSystem.ITEM_MATERIAL_PART.registerItemVariant(textureLocation);
    }

    public int getColor() {
        return this.colorize ? this.getMaterial().getColor().getRGB() : -1;
    }

    public boolean isColorize() {
        return colorize;
    }

    public void setColorize(boolean colorize) {
        this.colorize = colorize;
    }

    public boolean matchesPartType(PartType partType) {
        return this.getPart().getPartType() == partType;
    }

    public void setOreDict(Map<ItemStack,String> oreDict) {
        oreDict.put(itemStack, part.getUnlocalizedName() + material.getName());
    }
}
