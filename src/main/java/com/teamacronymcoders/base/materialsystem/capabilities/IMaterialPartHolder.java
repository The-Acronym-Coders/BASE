package com.teamacronymcoders.base.materialsystem.capabilities;

import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.materials.Material;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;


public interface IMaterialPartHolder extends INBTSerializable<NBTTagCompound> {
    MaterialPart getMaterialPart();

    Material getMaterial();

    Part getPart();

    MaterialUser getOwner();
}
