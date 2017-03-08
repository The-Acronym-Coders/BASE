package com.teamacronymcoders.base.creativetabs;

import com.google.common.base.Stopwatch;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CreativeTabCarousel extends CreativeTabBase {
    private ItemStack defaultIconStack;
    private List<ItemStack> iconStacks;
    private int currentStackSize;
    private ItemStack currentIconStack;
    private Stopwatch watch;
    private Random random;

    public CreativeTabCarousel(String label) {
        super(label, null);
        iconStacks = new ArrayList<>();
        watch = Stopwatch.createStarted();
        random = new Random();
        updateIcon();
        this.setFunction(nothing -> getCurrentIconStack());
    }

    private ItemStack getCurrentIconStack() {
        updateIcon();
        return currentIconStack;
    }

    private void updateIcon() {
        if (currentStackSize > 0) {
            if (this.watch.elapsed(TimeUnit.MILLISECONDS) >= 1500L) {
                this.watch.reset();
                this.watch.start();
                currentIconStack = iconStacks.get(random.nextInt(iconStacks.size()));
            }
        } else {
            if (defaultIconStack == null) {
                defaultIconStack = new ItemStack(Items.STICK);
            }
            currentIconStack = defaultIconStack;
        }
    }

    public void setIconStacks(List<ItemStack> iconStacks) {
        this.iconStacks = iconStacks;
        this.currentStackSize = this.iconStacks.size();
    }

    public void addIconStacks(List<ItemStack> iconStacks) {
        this.iconStacks.addAll(iconStacks);
        this.currentStackSize = this.iconStacks.size();
    }
}
