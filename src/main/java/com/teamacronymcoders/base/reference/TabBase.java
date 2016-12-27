package com.teamacronymcoders.base.reference;

import com.google.common.base.Stopwatch;
import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.api.materials.MaterialRegistry;
import com.teamacronymcoders.base.modules.materials.ModuleMaterials;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TabBase extends CreativeTabs {

    Stopwatch watch = Stopwatch.createUnstarted();

    int iconIndex = -1;

    public TabBase() {
        super("BASE");
    }

    private void updateIcon() {
        if (!watch.isRunning()) {
            watch.reset();
            watch.start();
        }
        if (Base.proxy.isClient() && this.watch.elapsed(TimeUnit.MILLISECONDS) >= 1500L) {
            Random random = new Random();
            this.watch.reset();
            this.watch.start();
            iconIndex = MaterialRegistry.getIDList().get(random.nextInt(MaterialRegistry.getIDList().size()));
        }
    }

    @SideOnly(Side.CLIENT)
    @Nonnull
    public ItemStack getIconItemStack() {
        updateIcon();

        if (iconIndex != -1) {
            return new ItemStack(ModuleMaterials.GEAR, 1, iconIndex);
        } else {
            return new ItemStack(ModuleMaterials.GEAR, 1);
        }

    }


    @SideOnly(Side.CLIENT)
    @Nonnull
    public Item getTabIconItem() {
        return this.getIconItemStack().getItem();
    }
}
