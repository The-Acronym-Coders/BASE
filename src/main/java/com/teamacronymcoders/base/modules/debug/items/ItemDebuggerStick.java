package com.teamacronymcoders.base.modules.debug.items;

import com.teamacronymcoders.base.api.IDebuggable;
import com.teamacronymcoders.base.items.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.LinkedHashMap;
import java.util.List;

public class ItemDebuggerStick extends ItemBase {
    public ItemDebuggerStick() {
        super("debugger_stick");
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull ItemStack itemStack, World world, EntityPlayer entityPlayer,
                                                    EnumHand hand) {
        RayTraceResult result = ProjectileHelper.forwardsRaycast(entityPlayer, true, true, null);
        ActionResult<ItemStack> actionResult = ActionResult.newResult(EnumActionResult.PASS, itemStack);
        ;
        if (result != null) {
            boolean wroteOutput = false;
            switch (result.typeOfHit) {
                case BLOCK:
                    TileEntity tileEntity = world.getTileEntity(result.getBlockPos());
                    Block block = world.getBlockState(result.getBlockPos()).getBlock();
                    if (tileEntity instanceof IDebuggable) {
                        writeDebug((IDebuggable) tileEntity);
                    } else if (block instanceof IDebuggable) {
                        writeDebug((IDebuggable) block);
                    } else {
                        this.getMod().getLogger().info(block.getRegistryName().toString());
                    }
                    wroteOutput = true;
                    break;
                case ENTITY:
                    Entity entity = result.entityHit;
                    if (entity instanceof IDebuggable) {
                        writeDebug((IDebuggable) entity);
                    } else if (entity != null) {
                        this.getMod().getLogger().info(entity.getName());
                    }
                    wroteOutput = true;
                    break;
                case MISS:
                    break;
            }
            if (wroteOutput) {
                actionResult = ActionResult.newResult(EnumActionResult.SUCCESS, itemStack);
            }
        }

        return actionResult;
    }

    public void writeDebug(IDebuggable debuggable) {
        LinkedHashMap<String, String> debugStrings = new LinkedHashMap<>();

        debugStrings = debuggable.getDebugStrings(debugStrings);

        if (!debugStrings.isEmpty()) {
            for (String debugString : debugStrings.values()) {
                this.getMod().getLogger().info(debugString);
            }
        }
    }

    @Override
    public List<String> getModelNames(List<String> modelNames) {
        modelNames.add("debugger_stick");
        return modelNames;
    }
}
