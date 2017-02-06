package com.teamacronymcoders.base.materialsystem;

import com.teamacronymcoders.base.materialsystem.materials.Material;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.reference.Reference;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

public class MaterialPart implements IForgeRegistryEntry<MaterialPart> {
    private Material material;
    private Part part;
    private ResourceLocation textureLocation;
    private ItemStack itemStack;

    public MaterialPart(Material material, Part part) {
        this.setMaterial(material);
        this.setPart(part);
        this.setTextureLocation(new ResourceLocation(Reference.MODID, part.getUnlocalizedName()));
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

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public ResourceLocation getTextureLocation() {
        return textureLocation;
    }

    public void setTextureLocation(ResourceLocation textureLocation) {
        this.textureLocation = textureLocation;
    }

    @Override
    public MaterialPart setRegistryName(ResourceLocation name) {
        return this;
    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(this.getMaterial().getUnlocalizedName(), this.getPart().getUnlocalizedName());
    }

    @Override
    public Class<MaterialPart> getRegistryType() {
        return MaterialPart.class;
    }


}
