package com.teamacronymcoders.base.proxies;

import com.teamacronymcoders.base.blocks.IHasBlockColor;
import com.teamacronymcoders.base.blocks.IHasBlockStateMapper;
import com.teamacronymcoders.base.client.BlockStateMappers;
import com.teamacronymcoders.base.client.ClientHelper;
import com.teamacronymcoders.base.client.Colors;
import com.teamacronymcoders.base.client.models.handler.ModelHandler;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.items.IHasItemColor;
import com.teamacronymcoders.base.modulesystem.IModule;
import com.teamacronymcoders.base.modulesystem.proxies.IModuleProxy;
import com.teamacronymcoders.base.registrysystem.pieces.RegistrySide;
import com.teamacronymcoders.base.util.files.ResourceLoader;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.IResource;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.io.IOUtils;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@SideOnly(Side.CLIENT)
public class LibClientProxy extends LibCommonProxy {
    private static ResourceLoader resourceLoader;
    private ModelHandler modelHandler;

    @Override
    public void addOBJDomain() {
        OBJLoader.INSTANCE.addDomain(getMod().getID());
    }

    @Override
    public void setAllItemModels(IHasModel model) {
        this.getModelHandler().registerModels(model);
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
    public void registerBlockStateMapper(IHasBlockStateMapper stateMapper) {
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

    @Override
    public String getFileContents(ResourceLocation location) {
        location = new ResourceLocation(location.getResourceDomain(), "templates/" + location.getResourcePath() + ".json");
        IResource resource = ClientHelper.getResource(location);
        String fileContents = "";
        if (resource != null) {
            InputStream inputStream = resource.getInputStream();
            try {
                fileContents = IOUtils.toString(inputStream);
            } catch (IOException e) {
                this.getMod().getLogger().getLogger().error("Failed to get File: " + location + " Exception: " + e);
            }
        }

        return fileContents;
    }

    @Override
    public void createResourceLoader(String modid) {
        if (resourceLoader == null) {
            resourceLoader = new ResourceLoader();
            try {
                resourceLoader.setup();
                resourceLoader.createImportantFolders(modid);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                this.getMod().getLogger().getLogger().error(e);
            }
        }

    }

    private ModelHandler getModelHandler() {
        if (modelHandler == null) {
            this.modelHandler = new ModelHandler(this.getMod());
        }
        return modelHandler;
    }
}
