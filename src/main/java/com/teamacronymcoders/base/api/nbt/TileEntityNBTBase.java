package com.teamacronymcoders.base.api.nbt;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.Arrays;

/**
 * Created by Jared on 7/30/2016.
 */
public class TileEntityNBTBase extends TileEntity {

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        Arrays.stream(getClass().getFields()).filter(f -> f.isAnnotationPresent(NBT.class)).forEach(f -> {
            try {
                NBT nbt = f.getAnnotation(NBT.class);
                INBTConverter converter = nbt.value().getConverter().newInstance().setKey(f.getName());
                f.set(this, converter.convert(compound));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        Arrays.stream(getClass().getFields()).filter(f -> f.isAnnotationPresent(NBT.class)).forEach(f -> {
            try {
                NBT nbt = f.getAnnotation(NBT.class);
                INBTConverter converter = nbt.value().getConverter().newInstance().setKey(f.getName());
                converter.convert(compound, f.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        });
        return compound;
    }

}
