package com.teamacronymcoders.base.renderer.entity.loader;

import com.teamacronymcoders.base.modulesystem.ModuleHandler;
import com.teamacronymcoders.base.util.ClassLoading;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityRendererLoader {
    @SuppressWarnings("unchecked")
    public static void loadRenderersFor(ASMDataTable table, ModuleHandler handler) {
        ClassLoading.getInstances(table, EntityRenderer.class, IEntityRenderer.class, aClass -> {
            EntityRenderer entityRenderer = aClass.getAnnotation(EntityRenderer.class);
            boolean load = false;
            if (entityRenderer != null) {
                String entityRendererHandler = entityRenderer.handler().trim();
                load = entityRendererHandler.isEmpty() || entityRendererHandler.equalsIgnoreCase(handler.getName());
                load &= handler.isModuleEnabled(entityRenderer.module());
            }
            return load;
        }).forEach(entityRender ->
                RenderingRegistry.registerEntityRenderingHandler(entityRender.getEntityClass(), entityRender.getRenderFactory()));
    }
}
