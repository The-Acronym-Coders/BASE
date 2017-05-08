package com.teamacronymcoders.base.materialsystem.materialparts;

import com.teamacronymcoders.base.Reference;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.materials.Material;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.materialsystem.parts.PartType;
import com.teamacronymcoders.base.util.TextUtils;
import com.teamacronymcoders.base.util.ItemStackUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

import java.util.Map;

public class MaterialPart {
    private Material material;
    private Part part;
    private ResourceLocation textureLocation;
    private boolean colorized;
    private MaterialPartData data;
    private String variant;
    private MaterialSystem materialSystem;
    private ItemStack itemStack = ItemStack.EMPTY;

    public MaterialPart(MaterialSystem materialSystem, Material material, Part part) {
        this(materialSystem, material, part, null);
    }
    public MaterialPart(MaterialSystem materialSystem, Material material, Part part, String variant) {
        this.setMaterial(material);
        this.setPart(part);
        this.materialSystem = materialSystem;
        this.setTextureLocation(new ResourceLocation(Reference.MODID, part.getUnlocalizedName()));
        this.colorized = true;
        this.data = new MaterialPartData(part.getData());

        if (variant != null) {
            this.variant = TextUtils.toSnakeCase(variant);
        }

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
        if(!ItemStackUtils.isValid(itemStack)) {
            itemStack = new ItemStack(MaterialsSystem.ITEM_MATERIAL_PART, 1, MaterialsSystem.getMaterialPartId(this));
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
        return this.colorized ? this.getMaterial().getColor().getRGB() : -1;
    }

    public boolean isColorized() {
        return colorized;
    }

    public void setColorized(boolean colorized) {
        this.colorized = colorized;
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

    public void setup() {
        this.getPart().getPartType().setup(this);
    }
}
