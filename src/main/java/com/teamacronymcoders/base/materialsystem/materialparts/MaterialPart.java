package com.teamacronymcoders.base.materialsystem.materialparts;

import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.materialsystem.materials.Material;
import com.teamacronymcoders.base.materialsystem.partdata.MaterialPartData;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.materialsystem.parttype.PartType;
import com.teamacronymcoders.base.util.ItemStackUtils;
import com.teamacronymcoders.base.util.TextUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

public class MaterialPart {
    private Material material;
    private Part part;
    private ResourceLocation textureLocation;
    private boolean colorized;
    private MaterialPartData data;
    private String variant;
    private MaterialUser materialUser;
    private ItemStack itemStack = ItemStack.EMPTY;

    public MaterialPart(MaterialUser materialUser, Material material, Part part) {
        this(materialUser, material, part, null);
    }
    public MaterialPart(MaterialUser materialUser, Material material, Part part, String variant) {
        this.setMaterial(material);
        this.setPart(part);
        this.materialUser = materialUser;
        if (materialUser != null) {
            this.setTextureLocation(new ResourceLocation(materialUser.getMod().getID(), part.getShortUnlocalizedName()));
        }
        this.colorized = true;
        this.data = new MaterialPartData(this, part.getPartType().getData());

        if (variant != null) {
            this.variant = TextUtils.toSnakeCase(variant);
        }

    }

    public int getId() {
        return this.getMaterialUser().getMaterialPartId(this);
    }

    public String getUnlocalizedName() {
        return material.getUnlocalizedName() + "_" + part.getShortUnlocalizedName() + ((variant != null) ? "_" + variant : "");
    }

    public String getLocalizedName() {
        //noinspection deprecation
        return I18n.translateToLocalFormatted(part.getUnlocalizedName(), material.getName());
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
            itemStack = this.getPartType().getItemStack(this);
        }

        return itemStack;
    }

    public ResourceLocation getTextureLocation() {
        return textureLocation;
    }

    public void setTextureLocation(ResourceLocation textureLocation) {
        this.textureLocation = textureLocation;
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
        return this.getPartType() == partType;
    }

    public String getOreDictString() {
        return this.getPart().getOreDictPrefix() + this.getMaterial().getOreDictSuffix();
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

    public MaterialUser getMaterialUser() {
        return this.materialUser;
    }

    public PartType getPartType() {
        return this.getPart().getPartType();
    }
}
