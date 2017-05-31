package com.teamacronymcoders.base.items.minecarts;

import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.entities.EntityMinecartBase;
import com.teamacronymcoders.base.util.BlockUtils;
import com.teamacronymcoders.base.util.ItemStackUtils;
import net.minecraft.block.BlockDispenser;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

//TODO Railcraft api
public abstract class ItemMinecartBase extends ItemMinecart implements /*IMinecartItem,*/ IHasModel {
    public ItemMinecartBase(String name) {
        super(EntityMinecart.Type.TNT);
        this.setUnlocalizedName(name);
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, new DispenseItemMinecartBase());
    }

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos blockPos, EnumHand hand,
                                      EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemStack = player.getHeldItem(hand);
        return placeCart(itemStack, world, blockPos, this.getEntityFromItem(world, itemStack));
    }

    public EnumActionResult placeCart(ItemStack itemStack, World world, BlockPos blockPos, EntityMinecartBase entityMinecart) {
        if (ItemStackUtils.isValid(itemStack) && BlockUtils.isRailBlock(world.getBlockState(blockPos))) {
            if (itemStack.hasDisplayName()) {
                entityMinecart.setCustomNameTag(itemStack.getDisplayName());
            }

            if (!world.isRemote) {
                entityMinecart.posX = (float) blockPos.getX() + 0.5F;
                entityMinecart.posY = (float) blockPos.getY() + 0.5F;
                entityMinecart.posZ = (float) blockPos.getZ() + 0.5F;
                world.spawnEntity(entityMinecart);
            }
            itemStack.shrink(1);
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

    @Override
    public Item getItem() {
        return this;
    }

    @Nonnull
    public abstract EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack);
}
