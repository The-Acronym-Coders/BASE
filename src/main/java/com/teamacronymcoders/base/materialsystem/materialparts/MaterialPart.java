package com.teamacronymcoders.base.materialsystem.materialparts;

import com.teamacronymcoders.base.Reference;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.materials.Material;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.materialsystem.parts.PartType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

import java.util.Map;

public class MaterialPart {
    private Material material;
    private Part part;
    private ResourceLocation textureLocation;
    private boolean colorize;
    private MaterialPartData data;
    private ItemStack itemStack;
    private MaterialSystem materialSystem;
    private String variant;

    public MaterialPart(MaterialSystem materialSystem, Material material, Part part) {
        this(materialSystem, material, part, null);
    }
    public MaterialPart(MaterialSystem materialSystem, Material material, Part part, String variant) {
        this.setMaterial(material);
        this.setPart(part);
        this.materialSystem = materialSystem;
        this.setTextureLocation(new ResourceLocation(Reference.MODID, part.getUnlocalizedName()));
        this.colorize = true;
        this.data = new MaterialPartData(part.getData());

        this.variant = variant;
    }

    public String getUnlocalizedName() {
        return material.getUnlocalizedName() + "_" + part.getUnlocalizedName() + ((variant != null) ? "_" + variant : "");
    }

    public String getLocalizedName() {
        //noinspection deprecation
        return String.format("%s %s", material.getName(), I18n.translateToLocal(part.getUnlocalizedName()));
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
        if (itemStack == null) {
            itemStack = new ItemStack(this.materialSystem.itemMaterialPart, 1, this.materialSystem.getMaterialPartId(this));
        }

        return itemStack;
    }

    public ResourceLocation getTextureLocation() {
        return textureLocation;
    }

    public void setTextureLocation(ResourceLocation textureLocation) {
        this.textureLocation = textureLocation;
        this.materialSystem.itemMaterialPart.registerItemVariant(textureLocation);
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

    public void setOreDict(Map<ItemStack, String> oreDict) {
        oreDict.put(itemStack, part.getUnlocalizedName() + material.getName());
    }

    public MaterialPartData getData() {
        return data;
    }

    public void setData(MaterialPartData data) {
        this.data = data;
    }
}
