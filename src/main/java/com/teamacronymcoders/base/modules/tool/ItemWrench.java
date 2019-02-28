package com.teamacronymcoders.base.modules.tool;

import com.teamacronymcoders.base.Capabilities;
import com.teamacronymcoders.base.api.ITool;
import com.teamacronymcoders.base.items.IHasRecipe;
import com.teamacronymcoders.base.items.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemWrench extends ItemBase implements IHasRecipe {

    public ItemWrench() {
        super("wrench");
        this.setCreativeTab(ItemGroup.TOOLS);
        this.setMaxStackSize(1);
    }

    @Override
    @Nonnull
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new CapabilityProvider();
    }

    public static class CapabilityProvider implements ICapabilityProvider {
        private ITool spanner;

        public CapabilityProvider() {
            this(new ITool(){});
        }

        public CapabilityProvider(ITool cap) {
            this.spanner = cap;
        }

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing) {
            return capability == Capabilities.TOOL;
        }

        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing facing) {
            return capability == Capabilities.TOOL ? Capabilities.TOOL.cast(spanner) : null;
        }
    }

    @Override
    public List<IRecipe> getRecipes(List<IRecipe> recipes) {
        return recipes;
    }

    @Override
    @Nonnull
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
                                      float hitX, float hitY, float hitZ) {
        EnumActionResult result = EnumActionResult.PASS;
        Block block = world.getBlockState(pos).getBlock();
        if (block.rotateBlock(world, pos, facing)) {
            result = EnumActionResult.SUCCESS;
        }
        return result;
    }
}
