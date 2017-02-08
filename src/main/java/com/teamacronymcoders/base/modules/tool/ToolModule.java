package com.teamacronymcoders.base.modules.tool;

import com.teamacronymcoders.base.api.ITool;
import com.teamacronymcoders.base.api.ToolImpl;
import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import com.teamacronymcoders.base.Reference;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Module(value = Reference.MODID)
public class ToolModule extends ModuleBase {
	public static Item wrench;

	@Override
	public String getName() {
		return "Tool";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		CapabilityManager.INSTANCE.register(ITool.class, new Capability.IStorage<ITool>() {
			@Override
			public NBTTagCompound writeNBT(Capability<ITool> capability, ITool instance, EnumFacing side) {
				return instance.serializeNBT();
			}

			@Override
			public void readNBT(Capability<ITool> capability, ITool instance, EnumFacing side, NBTBase nbt) {
				if(nbt instanceof NBTTagCompound)
					instance.deserializeNBT((NBTTagCompound) nbt);
			}
		}, ToolImpl.class);
		wrench = new ItemTool();
		this.getItemRegistry().register(wrench);
	}
}
