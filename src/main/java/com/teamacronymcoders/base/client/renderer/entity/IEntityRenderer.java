package com.teamacronymcoders.base.client.renderer.entity;

import net.minecraftforge.fml.client.registry.IRenderFactory;

public interface IEntityRenderer {
    Class getEntityClass();

    IRenderFactory getRenderFactory();
}
