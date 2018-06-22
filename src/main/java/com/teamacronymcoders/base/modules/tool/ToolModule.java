package com.teamacronymcoders.base.modules.tool;

import com.teamacronymcoders.base.Reference;
import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Module(value = Reference.MODID)
public class ToolModule extends ModuleBase {
    public static Item wrench;

    @Override
    public String getName() {
        return "Tool";
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        wrench = new ItemWrench();
        this.getItemRegistry().register(wrench);
    }
}
