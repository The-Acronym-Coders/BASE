package com.teamacronymcoders.base.client.models.wrapped;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.teamacronymcoders.base.client.models.EmptyModelState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collection;
import java.util.Collections;

@SideOnly(Side.CLIENT)
public class WrappedModel implements IModel {
    private WrappedBlockEntry blockEntry;

    public WrappedModel(WrappedBlockEntry blockEntry) {
        this.blockEntry = blockEntry;
    }

    @Override
    public Collection<ResourceLocation> getDependencies() {
        return Lists.newArrayList();
    }

    @Override
    public Collection<ResourceLocation> getTextures() {
        return blockEntry.getLayers();
    }

    @Override
    public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        return new WrappedBakedModel(this.getModelForWrap(), this.blockEntry);
    }

    @Override
    public IModelState getDefaultState() {
        return new EmptyModelState();
    }

    private IBakedModel getModelForWrap() {
        return Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(blockEntry.getBlockState());
    }
}
