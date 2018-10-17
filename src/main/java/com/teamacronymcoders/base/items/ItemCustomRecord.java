package com.teamacronymcoders.base.items;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.client.models.generator.IHasGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.util.Coloring;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemCustomRecord extends ItemRecord implements IHasGeneratedModel, IHasModel, IHasItemColor {
    private IBaseMod mod;
    private final Coloring color;

    public ItemCustomRecord(String recordName, SoundEvent soundEvent, Coloring color) {
        super(recordName, soundEvent);
        this.color = color;
    }

    @Override
    public IBaseMod getMod() {
        return mod;
    }

    @Override
    public void setMod(IBaseMod mod) {
        this.mod = mod;
    }

    @Override
    public int getColorFromItemstack(@Nonnull ItemStack stack, int tintIndex) {
        return color != null && tintIndex == 1 ? color.getIntColor() : -1;
    }

    @Override
    public Item getItem() {
        return this;
    }

    @Override
    public List<IGeneratedModel> getGeneratedModels() {
        return null;
    }
}
