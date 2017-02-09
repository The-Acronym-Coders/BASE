package com.teamacronymcoders.base.entities;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public abstract class EntityMinecartBase extends EntityMinecart {
    public EntityMinecartBase(World world) {
        super(world);
    }

    @Nonnull
    public abstract ItemMinecart getItem();

    @Override
    @Nonnull
    public ItemStack getCartItem() {
        ItemStack cartItemStack = new ItemStack(this.getItem(), 1, this.getMetadata());
        if(!this.getName().isEmpty()) {
            cartItemStack.setStackDisplayName(this.getName());
        }
        return cartItemStack;
    }

    @Override
    public void killMinecart(DamageSource damageSource) {
        this.setDead();
    }

    @Override
    @Nonnull
    public EntityMinecart.Type getType() {
        return Type.TNT;
    }

    @Override
    public boolean isPoweredCart() {
        return false;
    }

    @Override
    public void setDead() {
        super.setDead();
        if(this.worldObj.getGameRules().getBoolean("doEntityDrops")) {
            dropCart();
        }
    }

    @Override
    public boolean canBeRidden() {
        return false;
    }

    public void dropCart() {
        this.dropCartItemStack(this.getCartItem());
    }

    public void dropCartItemStack(ItemStack cartItem) {
        if(!worldObj.isRemote) {
            this.entityDropItem(cartItem, 0.1F);
        }
    }

    public int getMetadata() {
        return 0;
    }
}
