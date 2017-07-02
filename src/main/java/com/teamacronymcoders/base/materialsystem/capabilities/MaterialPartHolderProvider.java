package com.teamacronymcoders.base.materialsystem.capabilities;

import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.teamacronymcoders.base.materialsystem.capabilities.MaterialPartCapability.MATERIAL_PART_HOLDER;

public class MaterialPartHolderProvider implements ICapabilityProvider {
    private MaterialPartHolder holder;

    public MaterialPartHolderProvider(MaterialPart materialPart) {
        this.holder = new MaterialPartHolder(materialPart);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == MATERIAL_PART_HOLDER;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return hasCapability(capability, facing) ? MATERIAL_PART_HOLDER.cast(holder) : null;
    }
}
