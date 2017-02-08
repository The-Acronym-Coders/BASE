package com.teamacronymcoders.base.materialsystem;

import com.teamacronymcoders.base.materialsystem.materials.Material;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.Reference;
import com.teamacronymcoders.base.materialsystem.parts.PartType;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.base.MaterialPart")
public class MaterialPart implements IForgeRegistryEntry<MaterialPart> {
    private Material material;
    private Part part;
    private ResourceLocation textureLocation;
    private boolean colorize;

    public MaterialPart(Material material, Part part) {
        this.setMaterial(material);
        this.setPart(part);
        this.setTextureLocation(new ResourceLocation(Reference.MODID, part.getUnlocalizedName()));
        this.colorize = true;
    }

    @ZenMethod
    public String getName() {
        return material.getUnlocalizedName() + "." + part.getUnlocalizedName();
    }

    @ZenMethod
    public String getLocalizedName() {
        return String.format("%s %s", material.getName(), I18n.format(part.getUnlocalizedName()));
    }

    @ZenMethod
    public boolean hasEffect() {
        return this.getMaterial().isHasEffect();
    }

    @ZenMethod
    public Material getMaterial() {
        return material;
    }

    @ZenMethod
    public void setMaterial(Material material) {
        this.material = material;
    }

    @ZenMethod
    public Part getPart() {
        return part;
    }

    @ZenMethod
    public void setPart(Part part) {
        this.part = part;
    }

    public ItemStack getItemStack() {
        ItemStack itemStack = new ItemStack(MaterialsSystem.ITEM_MATERIAL_PART, 1, 0);
        itemStack.setTagInfo("material", new NBTTagString(this.getMaterial().getUnlocalizedName()));
        itemStack.setTagInfo("part", new NBTTagString(this.getPart().getUnlocalizedName()));
        return itemStack;
    }

    public ResourceLocation getTextureLocation() {
        return textureLocation;
    }

    public void setTextureLocation(ResourceLocation textureLocation) {
        this.textureLocation = textureLocation;
        MaterialsSystem.ITEM_MATERIAL_PART.registerItemVariant(textureLocation);
    }

    @ZenMethod
    public void setTextureLocation(String modid, String path) {
        this.setTextureLocation(new ResourceLocation(modid, path));
    }

    @ZenMethod
    public int getColor() {
        return this.colorize ? this.getMaterial().getColor().getRGB() : -1;
    }

    @ZenMethod
    public boolean isColorize() {
        return colorize;
    }

    @ZenMethod
    public void setColorize(boolean colorize) {
        this.colorize = colorize;
    }

    public boolean matchesPartType(PartType partType) {
        return this.getPart().getPartType() == partType;
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
