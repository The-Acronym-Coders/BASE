package com.teamacronymcoders.base.guisystem.target;

import com.teamacronymcoders.base.guisystem.IHasGui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@GuiTarget
public class GuiEntityTarget extends GuiTargetBase<Entity> {
    public GuiEntityTarget() {
        super();
    }

    public GuiEntityTarget(@Nonnull Entity target) {
        super(target);
    }

    @Override
    @Nonnull
    public String getName() {
        return "entity";
    }

    @Override
    @Nullable
    public IHasGui deserialize(@Nonnull EntityPlayer entityPlayer, @Nonnull World world, @Nonnull NBTTagCompound nbtTagCompound) {
        int entityId = nbtTagCompound.getInteger("entityId");
        Entity entity = world.getEntityByID(entityId);
        return entity instanceof IHasGui ? (IHasGui) entity : null;
    }

    @Override
    @Nonnull
    public NBTTagCompound serialize(@Nonnull NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setInteger("entityId", this.getTarget().getEntityId());
        return nbtTagCompound;
    }
}
