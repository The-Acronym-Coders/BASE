package com.acronym.base.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Jared on 7/2/2016.
 */
public class PlayerUtils {

    /**
     * Gets the block that an entity is looking at
     *
     * @param world                          Current world
     * @param entity                         Entity
     * @param ignoreBlocksWithoutBoundingBox ignoreBlocksWithoutBoundingBox
     * @return current block that player is looking at
     */
    public static RayTraceResult getTargetBlock(World world, Entity entity, boolean ignoreBlocksWithoutBoundingBox) {
        float var4 = 1.0F;
        float var5 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * var4;
        float var6 = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * var4;
        double var7 = entity.prevPosX + (entity.posX - entity.prevPosX) * var4;
        double var9 = entity.prevPosY + (entity.posY - entity.prevPosY) * var4 + 1.62D - entity.getYOffset();
        double var11 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * var4;

        Vec3d var13 = new Vec3d(var7, var9, var11);

        float var14 = MathHelper.cos(-var6 * 0.01745329F - 3.141593F);
        float var15 = MathHelper.sin(-var6 * 0.01745329F - 3.141593F);
        float var16 = -MathHelper.cos(-var5 * 0.01745329F);
        float var17 = MathHelper.sin(-var5 * 0.01745329F);
        float var18 = var15 * var16;
        float var20 = var14 * var16;
        double var21 = 10.0D;

        Vec3d var23 = var13.addVector(var18 * var21, var17 * var21, var20 * var21);

        return world.rayTraceBlocks(var13, var23, ignoreBlocksWithoutBoundingBox, !ignoreBlocksWithoutBoundingBox, false);
    }

    /**
     * Gets the target block without an entity
     *
     * @param world                          Current World
     * @param x                              x
     * @param y                              y
     * @param z                              z
     * @param yaw                            current yaw
     * @param pitch                          current pitch
     * @param ignoreBlocksWithoutBoundingBox ignoreBlocksWithoutBoundingBox
     * @param range                          range
     * @return current block that is being targeted
     */
    public static RayTraceResult getTargetBlock(World world, double x, double y, double z, float yaw, float pitch, boolean ignoreBlocksWithoutBoundingBox, double range) {
        Vec3d vec = new Vec3d(x, y, z);

        float var14 = MathHelper.cos(-yaw * 0.01745329F - 3.141593F);
        float var15 = MathHelper.sin(-yaw * 0.01745329F - 3.141593F);
        float var16 = -MathHelper.cos(-pitch * 0.01745329F);
        float var17 = MathHelper.sin(-pitch * 0.01745329F);
        float var18 = var15 * var16;
        float var20 = var14 * var16;

        Vec3d var23 = vec.addVector(var18 * range, var17 * range, var20 * range);

        return world.rayTraceBlocks(vec, var23, ignoreBlocksWithoutBoundingBox, !ignoreBlocksWithoutBoundingBox, false);
    }

    /**
     * Gets the current entity being pointed at
     *
     * @param world         Current World
     * @param entityplayer  playerIn
     * @param range         range
     * @param collideRadius collision radiius
     * @param nonCollide    should non collidable entities be caught?
     * @return Entity being targeted
     */
    public static Entity getPointedEntity(World world, EntityLivingBase entityplayer, double range, double collideRadius, boolean nonCollide) {
        Entity pointedEntity = null;

        Vec3d vec3d = new Vec3d(entityplayer.posX, entityplayer.posY + entityplayer.getEyeHeight(), entityplayer.posZ);
        Vec3d vec3d1 = entityplayer.getLookVec();
        Vec3d vec3d2 = vec3d.addVector(vec3d1.xCoord * range, vec3d1.yCoord * range, vec3d1.zCoord * range);

        List list = world.getEntitiesWithinAABBExcludingEntity(entityplayer, entityplayer.getEntityBoundingBox().addCoord(vec3d1.xCoord * range, vec3d1.yCoord * range, vec3d1.zCoord * range).expand(collideRadius, collideRadius, collideRadius));
        double d2 = 0.0D;

        for (Object aList : list) {
            Entity entity = (Entity) aList;

            RayTraceResult mop = world.rayTraceBlocks(
                new Vec3d(entityplayer.posX, entityplayer.posY + entityplayer.getEyeHeight(), entityplayer.posZ),
                new Vec3d(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ),
                false
            );

            if (((entity.canBeCollidedWith()) || (nonCollide)) && mop == null) {
                float f2 = Math.max(0.8F, entity.getCollisionBorderSize());
                AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().expand(f2, f2, f2);
                RayTraceResult movingobjectposition = axisalignedbb.calculateIntercept(vec3d, vec3d2);
                if (axisalignedbb.isVecInside(vec3d)) {
                    if ((0.0D < d2) || (d2 == 0.0D)) {
                        pointedEntity = entity;
                        d2 = 0.0D;
                    }

                } else if (movingobjectposition != null) {
                    double d3 = vec3d.distanceTo(movingobjectposition.hitVec);
                    if ((d3 < d2) || (d2 == 0.0D)) {
                        pointedEntity = entity;
                        d2 = d3;
                    }
                }
            }
        }
        return pointedEntity;
    }
}
