package com.teamacronymcoders.base.proxies;

import com.teamacronymcoders.base.blocks.IHasBlockColor;
import com.teamacronymcoders.base.blocks.IHasBlockStateMapper;
import com.teamacronymcoders.base.client.BlockStateMappers;
import com.teamacronymcoders.base.client.Colors;
import com.teamacronymcoders.base.client.Models;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.items.IHasItemColor;
import com.teamacronymcoders.base.modulesystem.IModule;
import com.teamacronymcoders.base.modulesystem.proxies.IModuleProxy;
import com.teamacronymcoders.base.registrysystem.pieces.RegistrySide;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class LibClientProxy extends LibCommonProxy {
    @Override
    public void addOBJDomain() {
        OBJLoader.INSTANCE.addDomain(getMod().getID());
    }

    @Override
    public void setItemModel(Item item, int metadata, ResourceLocation resourceLocation) {
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(resourceLocation, ""));
    }

    @Override
    public void setAllItemModels(Item item, IHasModel model) {
        Models.registerModels(model);
    }

    @Override
    public void registerFluidModel(Block fluidBlock, final ResourceLocation resourceLocation) {
        Item fluidItem = Item.getItemFromBlock(fluidBlock);
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(resourceLocation, "normal");
        if (fluidItem != null) {
            ModelBakery.registerItemVariants(fluidItem);
            ModelLoader.setCustomMeshDefinition(fluidItem, stack -> modelResourceLocation);
            ModelLoader.setCustomStateMapper(fluidBlock, new StateMapperBase() {
                @Override
                @Nonnull
                protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
                    return modelResourceLocation;
                }
            });
        }
    }

    @Override
    public void registerItemColor(Item item, IHasItemColor itemColor) {
        Colors.registerItemColor(item, itemColor);
    }

    public void registerItemColor(Block block, IHasItemColor itemColor) {
        Colors.registerItemColor(block, itemColor);
    }

    @Override
    public void registerBlockColor(IHasBlockColor blockColor) {
        Colors.registerBlockColor(blockColor);
    }

    @Override
    public void registerBlockStateMapper(Block block, IHasBlockStateMapper stateMapper) {
        BlockStateMappers.registerStateMapper(stateMapper);
    }

    @Override
    public RegistrySide getRegistrySide() {
        return RegistrySide.CLIENT;
    }

    @Override
    public IModuleProxy getModuleProxy(IModule module) {
        return getModuleProxy(module.getClientProxyPath());
    }

    @Override
    public void registerModelVariant(Item item, ResourceLocation resourceLocation) {
        ModelBakery.registerItemVariants(item, resourceLocation);
    }
}
