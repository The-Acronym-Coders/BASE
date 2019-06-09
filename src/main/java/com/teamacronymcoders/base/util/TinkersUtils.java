package com.teamacronymcoders.base.util;

import com.google.common.collect.ImmutableList;
import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.items.ItemBaseNoModel;
import com.teamacronymcoders.base.items.tools.IAreaBreakingTool;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;

public class TinkersUtils {
    // Stolen from TConstruct https://github.com/SlimeKnights/TinkersConstruct/

    /**
     * Preconditions for
     * {@link #breakExtraBlock(ItemStack, World, EntityPlayer, BlockPos, BlockPos)}
     *
     * @return true if the extra block can be broken
     */
    private static boolean canBreakExtraBlock(ItemStack stack, World world, EntityPlayer player, BlockPos pos,
                                              BlockPos refPos) {
        // prevent calling that stuff for air blocks, could lead to unexpected behaviour
        // since it fires events
        if (world.isAirBlock(pos)) {
            return false;
        }

        // check if the block can be broken, since extra block breaks shouldn't
        // instantly break stuff like obsidian
        // or precious ores you can't harvest while mining stone
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        // only effective materials
        if (!isToolEffective(stack, state)) {
            return false;
        }

        IBlockState refState = world.getBlockState(refPos);
        float refStrength = ForgeHooks.blockStrength(refState, player, world, refPos);
        float strength = ForgeHooks.blockStrength(state, player, world, pos);

        // only harvestable blocks that aren't impossibly slow to harvest
        if (!ForgeHooks.canHarvestBlock(block, player, world, pos) || refStrength / strength > 10f) {
            return false;
        }

        // From this point on it's clear that the player CAN break the block

        if (player.capabilities.isCreativeMode) {
            block.onBlockHarvested(world, pos, state, player);
            if (block.removedByPlayer(state, world, pos, player, false)) {
                block.onPlayerDestroy(world, pos, state);
            }

            // send update to client
            if (!world.isRemote) {
                world.notifyBlockUpdate(pos, state, world.getBlockState(pos), 2);
            }
            return false;
        }
        return true;
    }

    // Stolen from TCon
    public static void breakExtraBlock(ItemStack stack, World world, EntityPlayer player, BlockPos pos,
                                       BlockPos refPos) {
        if (!canBreakExtraBlock(stack, world, player, pos, refPos)) {
            return;
        }

        Base.instance.getLogger().devInfo("Could break block");

        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        // callback to the tool the player uses. Called on both sides. This damages the
        // tool n stuff.
        stack.onBlockDestroyed(world, state, pos, player);

        // server sided handling
        if (!world.isRemote) {
            // send the blockbreak event
            int xp = ForgeHooks.onBlockBreakEvent(world, ((EntityPlayerMP) player).interactionManager.getGameType(),
                    (EntityPlayerMP) player, pos);
            if (xp == -1) {
                return;
            }

            // serverside we reproduce ItemInWorldManager.tryHarvestBlock

            TileEntity tileEntity = world.getTileEntity(pos);
            // ItemInWorldManager.removeBlock
            if (block.removedByPlayer(state, world, pos, player, true)) { // boolean is if block can be harvested,
                // checked above
                block.onPlayerDestroy(world, pos, state);
                block.harvestBlock(world, player, pos, state, tileEntity, stack);
                block.dropXpOnBlockBreak(world, pos, xp);
            }

            // always send block update to client
            world.notifyBlockUpdate(pos, state, world.getBlockState(pos), 2);
        }
        // client sided handling
        else {
            // clientside we do a "this clock has been clicked on long enough to be broken"
            // call. This should not send any new packets
            // the code above, executed on the server, sends a block-updates that give us
            // the correct state of the block we destroy.

            // following code can be found in PlayerControllerMP.onPlayerDestroyBlock
            world.playBroadcastSound(2001, pos, Block.getStateId(state));
            if (block.removedByPlayer(state, world, pos, player, true)) {
                block.onPlayerDestroy(world, pos, state);
            }
            // callback to the tool
            stack.onBlockDestroyed(world, state, pos, player);

            if (stack.getCount() == 0 && stack == player.getHeldItemMainhand()) {
                ForgeEventFactory.onPlayerDestroyItem(player, stack, EnumHand.MAIN_HAND);
                player.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
            }

            // send an update to the server, so we get an update back
            // if(PHConstruct.extraBlockUpdates)
            NetHandlerPlayClient netHandlerPlayClient = Minecraft.getMinecraft().getConnection();
            assert netHandlerPlayClient != null;
            netHandlerPlayClient.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK,
                    pos, Minecraft.getMinecraft().objectMouseOver.sideHit));
        }
    }

    public static boolean isToolEffective(ItemStack stack, IBlockState state) {
        // check material
        for (String type : stack.getItem().getToolClasses(stack)) {
            if (state.getBlock().isToolEffective(type, state)) {
                return true;
            }
        }

        return false;
    }

    /* Harvesting */
    public static ImmutableList<BlockPos> calcAOEBlocks(ItemStack stack, World world, EntityPlayer player,
                                                        BlockPos origin, int width, int height, int depth, int distance) {
        // only works with toolcore because we need the raytrace call
        if (stack.isEmpty() || !(stack.getItem() instanceof ItemBaseNoModel)) {
            return ImmutableList.of();
        }

        // find out where the player is hitting the block
        IBlockState state = world.getBlockState(origin);

        if (!isToolEffective(stack, state)) {
            return ImmutableList.of();
        }

        // raytrace to get the side, but has to result in the same block
        RayTraceResult mop = ((ItemBaseNoModel) stack.getItem()).rayTrace(world, player, true);
        if (mop == null || !origin.equals(mop.getBlockPos())) {
            mop = ((ItemBaseNoModel) stack.getItem()).rayTrace(world, player, false);
            if (mop == null || !origin.equals(mop.getBlockPos())) {
                return ImmutableList.of();
            }
        }

        // we know the block and we know which side of the block we're hitting. time to
        // calculate the depth along the different axes
        int x, y, z;
        BlockPos start = origin;
        switch (mop.sideHit) {
            case DOWN:
            case UP:
                // x y depends on the angle we look?
                Vec3i vec = player.getHorizontalFacing().getDirectionVec();
                x = vec.getX() * height + vec.getZ() * width;
                y = mop.sideHit.getAxisDirection().getOffset() * -depth;
                z = vec.getX() * width + vec.getZ() * height;
                start = start.add(-x / 2, 0, -z / 2);
                if (x % 2 == 0) {
                    if (x > 0 && mop.hitVec.x - mop.getBlockPos().getX() > 0.5d) {
                        start = start.add(1, 0, 0);
                    } else if (x < 0 && mop.hitVec.x - mop.getBlockPos().getX() < 0.5d) {
                        start = start.add(-1, 0, 0);
                    }
                }
                if (z % 2 == 0) {
                    if (z > 0 && mop.hitVec.z - mop.getBlockPos().getZ() > 0.5d) {
                        start = start.add(0, 0, 1);
                    } else if (z < 0 && mop.hitVec.z - mop.getBlockPos().getZ() < 0.5d) {
                        start = start.add(0, 0, -1);
                    }
                }
                break;
            case NORTH:
            case SOUTH:
                x = width;
                y = height;
                z = mop.sideHit.getAxisDirection().getOffset() * -depth;
                start = start.add(-x / 2, -y / 2, 0);
                if (x % 2 == 0 && mop.hitVec.x - mop.getBlockPos().getX() > 0.5d) {
                    start = start.add(1, 0, 0);
                }
                if (y % 2 == 0 && mop.hitVec.y - mop.getBlockPos().getY() > 0.5d) {
                    start = start.add(0, 1, 0);
                }
                break;
            case WEST:
            case EAST:
                x = mop.sideHit.getAxisDirection().getOffset() * -depth;
                y = height;
                z = width;
                start = start.add(-0, -y / 2, -z / 2);
                if (y % 2 == 0 && mop.hitVec.y - mop.getBlockPos().getY() > 0.5d) {
                    start = start.add(0, 1, 0);
                }
                if (z % 2 == 0 && mop.hitVec.z - mop.getBlockPos().getZ() > 0.5d) {
                    start = start.add(0, 0, 1);
                }
                break;
            default:
                x = y = z = 0;
        }

        ImmutableList.Builder<BlockPos> builder = ImmutableList.builder();
        for (int xp = start.getX(); xp != start.getX() + x; xp += x / MathHelper.abs(x)) {
            for (int yp = start.getY(); yp != start.getY() + y; yp += y / MathHelper.abs(y)) {
                for (int zp = start.getZ(); zp != start.getZ() + z; zp += z / MathHelper.abs(z)) {
                    // don't add the origin block
                    if (xp == origin.getX() && yp == origin.getY() && zp == origin.getZ()) {
                        continue;
                    }
                    if (distance > 0 && MathHelper.abs(xp - origin.getX()) + MathHelper.abs(yp - origin.getY())
                            + MathHelper.abs(zp - origin.getZ()) > distance) {
                        continue;
                    }
                    BlockPos pos = new BlockPos(xp, yp, zp);
                    if (isToolEffective(stack, world.getBlockState(pos))) {
                        builder.add(pos);
                    }
                }
            }
        }

        return builder.build();
    }

}
