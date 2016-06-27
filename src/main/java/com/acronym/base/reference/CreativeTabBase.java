package com.acronym.base.reference;

import com.acronym.base.Base;
import com.google.common.base.Stopwatch;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jared on 6/27/2016.
 */
public class CreativeTabBase extends CreativeTabs {

    Stopwatch watch = Stopwatch.createStarted();

    int iconIndex = -1;

    public CreativeTabBase() {
        super(1, "B.A.S.E");
    }


    private void updateIcon() {

        World var1 = Base.proxy.getClientWorld();
        System.out.println(this.watch.elapsed(TimeUnit.MILLISECONDS));
        if (Base.proxy.isClient() && this.watch.elapsed(TimeUnit.MILLISECONDS) == 80) {

            Random random = new Random();

//            List<Integer> keys = new ArrayList<Integer>(SeedRegistry.getInstance().keySet());

//            iconIndex = keys.get(random.nextInt(keys.size()));

        }

    }

    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack() {

        updateIcon();

        if (iconIndex != -1) {

            return new ItemStack(Items.DIAMOND, 1, iconIndex);

        } else {

            return new ItemStack(Items.DIAMOND, 1);

        }

    }

    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {

        return this.getIconItemStack().getItem();

    }
}
