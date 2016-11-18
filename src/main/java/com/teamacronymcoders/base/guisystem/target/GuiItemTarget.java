package com.teamacronymcoders.base.guisystem.target;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.guisystem.IHasGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GuiItemTarget extends GuiTarget<Item> {
    private EnumHand hand;

    public GuiItemTarget() {
        super();
    }

    public GuiItemTarget(Item item, EnumHand hand) {
        super(item);
        this.hand = hand;
    }

    @Nonnull
    @Override
    public ResourceLocation getTargetType() {
        return new ResourceLocation(Base.instance.getID(), "item");
    }

    @Nullable
    @Override
    public IHasGui deserialize(@Nonnull EntityPlayer entityPlayer, @Nonnull World world, @Nonnull NBTTagCompound nbtTagCompound) {
        EnumHand hand = EnumHand.valueOf(nbtTagCompound.getString("hand"));
        ItemStack itemStack = entityPlayer.getHeldItem(hand);
        if(itemStack != null) {
            Item item = itemStack.getItem();
            return item instanceof IHasGui ? (IHasGui) item : null;
        }
        return null;
    }

    @Nonnull
    @Override
    public NBTTagCompound serialize(@Nonnull NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setString("hand", this.hand.name());
        return nbtTagCompound;
    }
}
