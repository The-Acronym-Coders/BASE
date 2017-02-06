package com.teamacronymcoders.base;

import com.teamacronymcoders.base.materialsystem.MaterialPart;
import com.teamacronymcoders.base.materialsystem.MaterialsSystem;
import com.teamacronymcoders.base.reference.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.NewRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.RegistryBuilder;

@EventBusSubscriber
public class RegistryEventHandler {
    @SubscribeEvent
    public static void registryRegistries(NewRegistry newRegistry) {
        new RegistryBuilder<MaterialPart>().setName(new ResourceLocation(Reference.MODID, "material_parts"))
                .setType(MaterialPart.class).create();
    }
}
