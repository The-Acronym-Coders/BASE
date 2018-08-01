package com.teamacronymcoders.base.renderer.entity.loader;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public interface IEntityRenderer<T extends Entity> {
    Class<T> getEntityClass();

    IRenderFactory getRenderFactory();
}
