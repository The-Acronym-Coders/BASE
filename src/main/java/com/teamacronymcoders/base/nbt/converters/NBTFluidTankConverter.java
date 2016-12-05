package com.teamacronymcoders.base.nbt.converters;

import com.teamacronymcoders.base.nbt.INBTConverter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

/**
 * Created by Jared on 7/30/2016.
 */
public class NBTFluidTankConverter implements INBTConverter<FluidTank> {

    private String name;

    public NBTFluidTankConverter() {
    }

    @Override
    public NBTFluidTankConverter setKey(String key) {
        this.name = key;
        return this;
    }

    @Override
    public FluidTank convert(NBTTagCompound tag) {
        NBTTagCompound tank = tag.getCompoundTag(name);
        FluidStack fluid = null;

        if (!tank.hasKey("Empty")) {
            String fluidName = tank.getString("FluidName");

            if (fluidName == null || FluidRegistry.getFluid(fluidName) == null) {
                fluid = null;
            } else
                fluid = new FluidStack(FluidRegistry.getFluid(fluidName), tank.getInteger("Amount"));

        } else {
            fluid = null;
        }
        if (fluid != null) {
            return new FluidTank(fluid, tank.getInteger("capacity"));
        } else {
            return new FluidTank(tank.getInteger("capacity"));
        }

    }

    @Override
    public NBTTagCompound convert(NBTTagCompound tag, FluidTank value) {
        NBTTagCompound tank = new NBTTagCompound();
        tank.setInteger("capacity", value.getCapacity());
        if (value.getFluid() != null) {
            tank.setString("FluidName", FluidRegistry.getFluidName(value.getFluid()));
            tank.setInteger("Amount", value.getFluidAmount());
        } else {
            tank.setString("Empty", "");
        }
        tag.setTag(name, tank);
        return tag;
    }
}
