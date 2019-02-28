package com.teamacronymcoders.base;

import com.teamacronymcoders.base.entities.dataserializers.BaseDataSerializers;
import com.teamacronymcoders.base.items.ItemEventHandler;
import com.teamacronymcoders.base.recipesystem.RecipeSystem;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.teamacronymcoders.base.Base.ID;

@Mod(ID)
public class Base extends BaseModFoundation<Base> {
    public static final String ID = "base";

    static {
        FluidRegistry.enableUniversalBucket();
    }

    public static Base instance;

    public Base() {
        super(ID, ItemGroup.MISC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    public void setup(FMLCommonSetupEvent event) {
        BaseDataSerializers.registerSerializers();
        Capabilities.register();
        RecipeSystem.setup();
        MinecraftForge.EVENT_BUS.register(new ItemEventHandler());
    }

    @Override
    public Base getInstance() {
        return instance;
    }
}
