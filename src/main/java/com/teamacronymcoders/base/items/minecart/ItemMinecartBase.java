package com.teamacronymcoders.base.items.minecart;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.IModAware;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.entities.EntityMinecartBase;
import com.teamacronymcoders.base.util.BlockUtils;
import com.teamacronymcoders.base.util.ItemStackUtils;
import net.minecraft.block.BlockDispenser;
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

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public abstract class ItemMinecartBase extends ItemMinecart implements IHasModel, IModAware {
    private IBaseMod mod;
    private String name;

    public ItemMinecartBase(String name) {
        super(EntityMinecart.Type.TNT);
        this.name = "minecart_" + name;
        this.setUnlocalizedName(this.name);
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
