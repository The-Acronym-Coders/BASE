package com.teamacronymcoders.base.materialsystem.capabilities;

import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.materials.Material;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import net.minecraft.nbt.NBTTagCompound;

public class MaterialPartHolder implements IMaterialPartHolder {
    private MaterialPart materialPart;

    public MaterialPartHolder(MaterialPart materialPart) {
        this.materialPart = materialPart;
    }

    public MaterialPartHolder() {

    }

    @Override
    public MaterialPart getMaterialPart() {
        return materialPart;
    }

    @Override
    public Material getMaterial() {
        return materialPart.getMaterial();
    }

    @Override
    public Part getPart() {
        return materialPart.getPart();
    }

    @Override
    public MaterialUser getOwner() {
        return materialPart.getMaterialUser();
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setString("modid", this.getOwner().getId());
        compound.setInteger("materialPartId", this.getMaterialPart().getId());
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        MaterialUser user = MaterialSystem.getUser(nbt.getString("modid"));
        if (user != null) {
            materialPart = user.getMaterialPart(nbt.getInteger("materialPartId"));
        } else {
            materialPart = MaterialSystem.MISSING_MATERIAL_PART;
        }
    }
}
