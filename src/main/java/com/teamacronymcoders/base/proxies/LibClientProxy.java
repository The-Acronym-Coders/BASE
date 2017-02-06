package com.teamacronymcoders.base.proxies;

import javax.annotation.Nonnull;

import com.teamacronymcoders.base.blocks.IHasBlockColor;
import com.teamacronymcoders.base.blocks.IHasBlockStateMapper;
import com.teamacronymcoders.base.client.BlockStateMappers;
import com.teamacronymcoders.base.client.Colors;
import com.teamacronymcoders.base.client.Models;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.guisystem.IHasGui;
import com.teamacronymcoders.base.guisystem.network.PacketOpenGui;
import com.teamacronymcoders.base.guisystem.target.GuiTargetBase;
import com.teamacronymcoders.base.items.IHasItemColor;
import com.teamacronymcoders.base.modulesystem.IModule;
import com.teamacronymcoders.base.modulesystem.proxies.IModuleProxy;
import com.teamacronymcoders.base.registry.pieces.RegistrySide;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
    public void openGui(@Nonnull GuiTargetBase guiTarget, @Nonnull NBTTagCompound context,
                        boolean openGuiFromServerContext, EntityPlayer entityPlayer, World world) {
        if (!openGuiFromServerContext) {
            if (guiTarget.getTarget() instanceof IHasGui) {
                Gui gui = ((IHasGui) guiTarget.getTarget()).getGui(entityPlayer, world, context);
                FMLCommonHandler.instance().showGuiScreen(gui);
            }
        }
    }

    @Override
    public void openGuiFromPacket(PacketOpenGui message, MessageContext ctx) {
        Minecraft mc = Minecraft.getMinecraft();
        IHasGui hasGui = message.getTarget().deserialize(mc.thePlayer, mc.theWorld, message.getContext());
        if (hasGui != null) {
            Gui gui = hasGui.getGui(mc.thePlayer, mc.theWorld, message.getContext());
            if (gui != null) {
                FMLCommonHandler.instance().showGuiScreen(gui);
            }
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
