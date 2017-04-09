package com.teamacronymcoders.base.modules.tool;

import com.teamacronymcoders.base.Reference;
import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import net.minecraft.item.Item;
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
        wrench = new ItemTool();
        this.getItemRegistry().register(wrench);
    }
}
