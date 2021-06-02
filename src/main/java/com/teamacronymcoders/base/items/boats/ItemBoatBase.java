package com.teamacronymcoders.base.items.boats;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.multiblocksystem.rectangular.PartPosition;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public abstract class ItemBoatBase<BOAT extends EntityBoat> extends ItemBoat implements IHasModel {
    private boolean creativeTabSet = false;
    private IBaseMod mod;

    public ItemBoatBase(String unlocalizedName) {
        super(EntityBoat.Type.OAK);
        this.setTranslationKey(unlocalizedName);
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World world, @Nonnull EntityPlayer entityPlayer, @Nonnull EnumHand hand) {
        ItemStack itemStack = entityPlayer.getHeldItem(hand);
        float f = 1.0F;
        float f1 = entityPlayer.prevRotationPitch + (entityPlayer.rotationPitch - entityPlayer.prevRotationPitch) * f;
        float f2 = entityPlayer.prevRotationYaw + (entityPlayer.rotationYaw - entityPlayer.prevRotationYaw) * f;
        double d0 = entityPlayer.prevPosX + (entityPlayer.posX - entityPlayer.prevPosX) * (double) f;
        double d1 =
                entityPlayer.prevPosY + (entityPlayer.posY - entityPlayer.prevPosY) * (double) f + (double) entityPlayer
                        .getEyeHeight();
        double d2 = entityPlayer.prevPosZ + (entityPlayer.posZ - entityPlayer.prevPosZ) * (double) f;
        Vec3d vec3d = new Vec3d(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0D;
        Vec3d vec3d1 = vec3d.add((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
        RayTraceResult raytraceresult = world.rayTraceBlocks(vec3d, vec3d1, true);

        if (raytraceresult == null) {
            return new ActionResult<>(EnumActionResult.PASS, itemStack);
        } else {
            Vec3d vec3d2 = entityPlayer.getLook(f);
            boolean flag = false;
            List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(entityPlayer, entityPlayer.getEntityBoundingBox()
                    .expand(vec3d2.x * 5.0D, vec3d2.y * 5.0D, vec3d2.z * 5.0D).grow(1.0D));

            for (Entity entity : list) {
                if (entity.canBeCollidedWith()) {
                    AxisAlignedBB axisalignedbb =
                            entity.getEntityBoundingBox().grow((double) entity.getCollisionBorderSize());

                    if (axisalignedbb.contains(vec3d)) {
                        flag = true;
                    }
                }
            }

            if (flag) {
                return new ActionResult<>(EnumActionResult.PASS, itemStack);
            } else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
                return new ActionResult<>(EnumActionResult.PASS, itemStack);
            } else {
                Block block = world.getBlockState(raytraceresult.getBlockPos()).getBlock();
                boolean isWater = block == Blocks.WATER || block == Blocks.FLOWING_WATER;
                BOAT entityBoat = this.getBoatToPlace(world, itemStack);
                double boatPosX = raytraceresult.hitVec.x;
                double boatPosY = isWater ? raytraceresult.hitVec.y - 0.12D : raytraceresult.hitVec.y;
                double boatPosZ = raytraceresult.hitVec.z;
                entityBoat.setPosition(boatPosX, boatPosY, boatPosZ);
                entityBoat.setBoatType(this.getType(itemStack));
                entityBoat.rotationYaw = entityPlayer.rotationYaw;

                if (!world.getCollisionBoxes(entityBoat, entityBoat.getEntityBoundingBox().grow(-0.1D))
                        .isEmpty()) {
                    return new ActionResult<>(EnumActionResult.FAIL, itemStack);
                } else {
                    if (!entityPlayer.capabilities.isCreativeMode) {
                        itemStack.shrink(1);
                    }

                    if (!world.isRemote) {
                        world.spawnEntity(entityBoat);
                        this.onPlace(entityBoat, entityPlayer, hand, itemStack);
                    }

                    this.increaseStat(entityPlayer);
                    return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
                }
            }
        }
    }

    protected EntityBoat.Type getType(ItemStack itemStack) {
        return EntityBoat.Type.values()[itemStack.getItemDamage()];
    }

    public abstract BOAT getBoatToPlace(World world, ItemStack itemStack);

    protected void onPlace(BOAT placedBoat, EntityPlayer entityPlayer, EnumHand hand, ItemStack itemStack) {

    }

    public void increaseStat(EntityPlayer entityPlayer) {
        StatBase stat = StatList.getObjectUseStats(this);
        if (stat != null) {
            entityPlayer.addStat(stat);
        }
    }

    @Override
    @Nonnull
    public Item setCreativeTab(@Nonnull CreativeTabs tab) {
        if (!creativeTabSet) {
            super.setCreativeTab(tab);
            this.creativeTabSet = true;
        }
        return this;
    }

    @Override
    public List<String> getModelNames(List<String> modelNames) {
        this.getAllSubItems(Lists.newArrayList()).forEach(itemStack -> modelNames.add(this.getTranslationKey(itemStack)));
        return modelNames;
    }

    @Override
    public void getSubItems(@Nullable CreativeTabs tab, @Nonnull NonNullList<ItemStack> itemStacks) {
        if (tab != null && tab == this.getCreativeTab() || tab == CreativeTabs.SEARCH) {
            itemStacks.addAll(this.getAllSubItems(Lists.newArrayList()));
        }
    }

    @Override
    public List<ItemStack> getAllSubItems(List<ItemStack> itemStacks) {
        itemStacks.add(new ItemStack(this, 1, 0));
        return itemStacks;
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
}
