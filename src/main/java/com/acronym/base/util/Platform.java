package com.acronym.base.util;

import net.minecraft.block.properties.IProperty;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.annotation.Nullable;
import java.util.Map;

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
                ItemStack.areItemStackTagsEqual(itemStack1,itemStack2);
    }

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

    public static String getPropertyString(Map<IProperty<?>, Comparable<?>> values, String... extrasArgs) {
        StringBuilder stringbuilder = new StringBuilder();

        for (Map.Entry<IProperty<?>, Comparable<?>> entry : values.entrySet()) {
            if (stringbuilder.length() != 0) {
                stringbuilder.append(",");
            }

            IProperty<?> iproperty = (IProperty) entry.getKey();
            stringbuilder.append(iproperty.getName());
            stringbuilder.append("=");
            stringbuilder.append(getPropertyName(iproperty, (Comparable) entry.getValue()));
        }

        if (stringbuilder.length() == 0) {
            stringbuilder.append("inventory");
        }

        for (String args : extrasArgs) {
            if (stringbuilder.length() != 0)
                stringbuilder.append(",");
            stringbuilder.append(args);
        }

        return stringbuilder.toString();
    }

    private static <T extends Comparable<T>> String getPropertyName(IProperty<T> p_187489_1_, Comparable<?> p_187489_2_) {
        return p_187489_1_.getName((T) p_187489_2_);
    }

    public static boolean isDevEnv() {
        return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }
}
