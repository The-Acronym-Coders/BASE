package com.teamacronymcoders.base.proxies;

import com.teamacronymcoders.base.client.ClientHelper;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.client.models.handler.ModelHandler;
import com.teamacronymcoders.base.client.models.sided.ModelLoaderSidedBlock;
import com.teamacronymcoders.base.modulesystem.IModule;
import com.teamacronymcoders.base.modulesystem.ModuleHandler;
import com.teamacronymcoders.base.modulesystem.proxies.IModuleProxy;
import com.teamacronymcoders.base.registrysystem.pieces.RegistrySide;
import com.teamacronymcoders.base.renderer.entity.loader.EntityRendererLoader;
import com.teamacronymcoders.base.util.files.ResourceLoader;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.IResource;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.io.IOUtils;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@SideOnly(Side.CLIENT)
public class LibClientProxy extends LibCommonProxy {
    private static ResourceLoader resourceLoader;
    private ModelHandler modelHandler;

    @Override
    public void addOBJDomain() {
        OBJLoader.INSTANCE.addDomain(getMod().getID());
    }

    @Override
    public void addSidedBlockDomain() {
        ModelLoaderSidedBlock.getInstance().addDomain(getMod().getID());
    }

    @Override
    public void setAllItemModels(IHasModel model) {
        this.getModelHandler().registerModels(model);
    }

    @Override
    public void registerFluidModel(Block fluidBlock, final ResourceLocation resourceLocation) {
        Item fluidItem = Item.getItemFromBlock(fluidBlock);
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(resourceLocation, "normal");
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

    @Override
    public RegistrySide getRegistrySide() {
        return RegistrySide.CLIENT;
    }

    @Override
    public IModuleProxy getModuleProxy(IModule module) {
        return getModuleProxy(module.getClientProxyPath());
    }

    @Override
    public String getFileContents(ResourceLocation location) {
        location = new ResourceLocation(location.getNamespace(), "templates/" + location.getPath() + ".json");
        IResource resource = ClientHelper.getResource(location);
        String fileContents = "";
        if (resource != null) {
            InputStream inputStream = resource.getInputStream();
            try {
                fileContents = IOUtils.toString(inputStream, Charset.defaultCharset());
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

    @Override
    public void loadEntityRenderers(ASMDataTable table, ModuleHandler moduleHandler) {
        EntityRendererLoader.loadRenderersFor(table, moduleHandler);
    }

    @Override
    public World getWorld(MessageContext ctx) {
        return Minecraft.getMinecraft().world;
    }
}
