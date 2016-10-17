package com.teamacronymcoders.base.util;

import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class Platform {

    /**
     * Check if the code is running on the server instance
     *
     * @return True if running on the server
     */
    public static boolean isServer() {
        return FMLCommonHandler.instance().getEffectiveSide().isServer();
    }

    /**
     * Check if the code is running on the client instance
     *
     * @return True if running on the client
     */
    public static boolean isClient() {
        return FMLCommonHandler.instance().getEffectiveSide().isClient();
    }

    /**
     * Compare two ItemStacks to see if they are the same
     *
     * @param itemStack1 ItemStack 1 to compare
     * @param itemStack2 ItemStack 2 to compare
     * @return True is they are the same
     */
    public static boolean isSameItemIgnoreNBT(@Nullable ItemStack itemStack1, @Nullable ItemStack itemStack2) {
        return itemStack1 != null && itemStack2 != null && itemStack1.isItemEqual(itemStack2);
    }

    /**
     * Compare two ItemStacks to see if they are the same
     *
     * @param itemStack1 ItemStack 1 to compare
     * @param itemStack2 ItemStack 2 to compare
     * @return True is they are the same
     */
    public static boolean isSameItemWithNBT(@Nullable ItemStack itemStack1, @Nullable ItemStack itemStack2) {
        return itemStack1 != null && itemStack2 != null && itemStack1.isItemEqual(itemStack2) &&
                ItemStack.areItemStackTagsEqual(itemStack1, itemStack2);
    }

    /**
     * Rotates an EnumFacing
     *
     * @param forward EnumFacing to rotate
     * @return
     */
    public static EnumFacing rotateAround(final EnumFacing forward) {
        switch (forward) {
            case NORTH:
                return EnumFacing.EAST;
            case EAST:
                return EnumFacing.SOUTH;
            case SOUTH:
                return EnumFacing.WEST;
            case WEST:
                return EnumFacing.NORTH;
            default:
                return forward;
        }
    }

    /**
     * Is this a development environment?
     *
     * @return boolean showing if the game is currently in dev
     */
    public static boolean isDevEnv() {
        return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }

    /**
     * Should we generate jsons?
     *
     * @return boolean showing if we should generate jsons
     */
    public static boolean generateBaseTextures() {
        return getArgumentList().contains("generateBaseTextures");
    }

    /**
     * Gets the program args used to run the game
     *
     * @return ArrayList containing the programs args used to run the game
     */
    public static ArrayList getArgumentList() {
        return (ArrayList) Launch.blackboard.get("ArgumentList");
    }
}
