package com.teamacronymcoders.base.items.minecart;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.IModAware;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.entities.EntityMinecartBase;
import com.teamacronymcoders.base.util.ItemStackUtils;

import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockRailBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class ItemMinecartBase extends ItemMinecart implements IHasModel, IModAware {
    private IBaseMod mod;
    private String name;

    public ItemMinecartBase(String name) {
        super(EntityMinecart.Type.TNT);
        this.name = "minecart_" + name;
        this.setTranslationKey(this.name);
        this.hasSubtypes = true;
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
        if (ItemStackUtils.isValid(itemStack) && BlockRailBase.isRailBlock(world, blockPos)) {
            if (itemStack.hasDisplayName()) {
                entityMinecart.setCustomNameTag(itemStack.getDisplayName());
            }

            if (!world.isRemote) {
            	//For minecarts, this method also sets up the bounding box, because of course.
            	entityMinecart.setPosition((float) blockPos.getX() + 0.5F, (float) blockPos.getY() + 0.5F, (float) blockPos.getZ() + 0.5F);
                world.spawnEntity(entityMinecart);
            }
            itemStack.shrink(1);
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

    @Override
    public List<String> getModelNames(List<String> modelNames) {
        modelNames.add(name);
        return modelNames;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            items.addAll(this.getAllSubItems(Lists.newArrayList()));
        }
    }

    @Override
    public Item getItem() {
        return this;
    }

    @Override
    public IBaseMod getMod() {
        return this.mod;
    }

    @Override
    public void setMod(IBaseMod mod) {
        this.mod = mod;
    }

    @Nonnull
    public abstract EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack);
}
