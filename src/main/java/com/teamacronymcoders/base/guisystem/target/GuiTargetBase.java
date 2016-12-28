package com.teamacronymcoders.base.guisystem.target;

import com.teamacronymcoders.base.guisystem.IHasGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class GuiTargetBase<T> {
    private T target;

    public GuiTargetBase() {

    }

    public GuiTargetBase(@Nonnull T target) {
        this.target = target;
    }

    /*
     * @return the unique name for this target type
     */
    @Nonnull
    public abstract String getName();

    /*
     * @return the current target for which the Gui is going to be created for
     */
    @Nonnull
    public T getTarget() {
        return target;
    }

    /*
     * @param world The World in which the Target Exists
     * @param nbtTagCompound The NBTTagCompound containing the information about the target
     * @return The instance that has the gui system(Whether it be an Entity, Block, etc), or null
     */
    @Nullable
    public abstract IHasGui deserialize(@Nonnull EntityPlayer entityPlayer, @Nonnull World world,
                                        @Nonnull NBTTagCompound nbtTagCompound);

    /*
     * @param nbtTagCompound An empty nbt tag to add information to
     * @return The nbt tag that is to be sent to the client and used to get the instance
     */
    @Nonnull
    public abstract NBTTagCompound serialize(@Nonnull NBTTagCompound nbtTagCompound);
}
