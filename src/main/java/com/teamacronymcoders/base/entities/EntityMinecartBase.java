package com.teamacronymcoders.base.entities;

import com.teamacronymcoders.base.Base;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;

import javax.annotation.Nonnull;
import java.util.Optional;

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

        this.getCartItemNBT().ifPresent(cartItemStack::setTagCompound);

        if (!this.getName().isEmpty()) {
            cartItemStack.setStackDisplayName(this.getName());
        }
        return cartItemStack;
    }

    public Optional<NBTTagCompound> getCartItemNBT() {
        return Optional.empty();
    }

    public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
        if (!MinecraftForge.EVENT_BUS.post(new MinecartInteractEvent(this, player, hand))) {
            if (this.canBeRidden()) {
                if (player.isSneaking()) {
                    return false;
                } else if (this.isBeingRidden()) {
                    return true;
                } else {
                    if (!this.world.isRemote) {
                        player.startRiding(this);
                    }

                    return true;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public void killMinecart(@Nonnull DamageSource damageSource) {
        this.setDead();
    }

    @Override
    @Nonnull
    public EntityMinecart.Type getType() {
        Base.instance.getLogger().fatal("Someone called EntityMinecart.getType(). This doesn't work for Modded Entities");
        return Type.TNT;
    }

    @Override
    public boolean isPoweredCart() {
        return false;
    }

    @Override
    public void setDead() {
        super.setDead();
        if (this.getEntityWorld().getGameRules().getBoolean("doEntityDrops")) {
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
        if (!this.getEntityWorld().isRemote) {
            this.entityDropItem(cartItem, 0.1F);
        }
    }

    public int getMetadata() {
        return 0;
    }
}
