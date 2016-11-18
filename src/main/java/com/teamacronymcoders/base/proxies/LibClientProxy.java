package com.teamacronymcoders.base.proxies;

import com.teamacronymcoders.base.guisystem.IHasGui;
import com.teamacronymcoders.base.guisystem.target.GuiTarget;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.modulesystem.IModule;
import com.teamacronymcoders.base.modulesystem.proxies.IModuleProxy;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

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
        List<ItemStack> allSubItems = new ArrayList<>();
        item.getSubItems(item, item.getCreativeTab(), allSubItems);
        int locationsIndex = 0;
        List<ModelResourceLocation> modelResourceLocations = model.getModelResourceLocations(new ArrayList<>());
        if(modelResourceLocations.size() > 0) {
            for (int i = 0; i < allSubItems.size(); i++) {
                ModelLoader.setCustomModelResourceLocation(item, i, modelResourceLocations.get(locationsIndex));
                locationsIndex++;
                if (locationsIndex >= modelResourceLocations.size()) {
                    locationsIndex = 0;
                }
            }
        } else {
            getMod().getLogger().devInfo(item.getRegistryName() + " implements IHasModel, but lists no models");
        }
    }

    @Override
    public void registerFluidModel(Block fluidBlock, final ResourceLocation resourceLocation) {
        Item fluidItem = Item.getItemFromBlock(fluidBlock);
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(resourceLocation, "normal");
        if(fluidItem != null) {
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
    public void openGui(@Nonnull GuiTarget guiTarget, @Nonnull NBTTagCompound context, boolean openGuiFromServerContext,
                        EntityPlayer entityPlayer, World world) {
        if(!openGuiFromServerContext) {
            if(guiTarget.getTarget() instanceof IHasGui) {
                Gui gui = ((IHasGui) guiTarget.getTarget()).getGui(entityPlayer, world, context);
                FMLCommonHandler.instance().showGuiScreen(gui);
            }

        }
    }

    @Override
    public IModuleProxy getModuleProxy(IModule module) {
        return getModuleProxy(module.getClientProxyPath());
    }

}
