package com.teamacronymcoders.base.proxies;

import com.teamacronymcoders.base.client.models.sided.ModelLoaderSidedBlock;
import com.teamacronymcoders.base.registrysystem.pieces.RegistrySide;
import com.teamacronymcoders.base.util.files.ResourceLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.obj.OBJLoader;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Optional;

@OnlyIn(Dist.CLIENT)
public class LibClientProxy extends LibCommonProxy {
    private static ResourceLoader resourceLoader;

    @Override
    public void addOBJDomain() {
        OBJLoader.INSTANCE.addDomain(getMod().getID());
    }

    @Override
    public void addSidedBlockDomain() {
        ModelLoaderSidedBlock.getInstance().addDomain(getMod().getID());
    }

    @Override
    public RegistrySide getRegistrySide() {
        return RegistrySide.CLIENT;
    }

    @Override
    public String getFileContents(ResourceLocation location) {
        location = new ResourceLocation(location.getNamespace(), "templates/" + location.getPath() + ".json");

        String fileContents = "";

        try {
            IResource iResource = Minecraft.getInstance().getResourceManager().getResource(location);
            InputStream inputStream = iResource.getInputStream();
            fileContents = IOUtils.toString(inputStream, Charset.defaultCharset());
        } catch (IOException e) {
            this.getMod().getLogger().error("Failed to get File: " + location + " Exception: " + e);
        }


        return fileContents;
    }

    @Override
    public void createResourceLoader(String modid) {
        if (resourceLoader == null) {
            resourceLoader = new ResourceLoader();
        }
        resourceLoader.createImportantFolders(modid);
    }

    @Override
    public void handleSounds() {
        Optional.ofNullable(resourceLoader)
                .ifPresent(loader -> loader.handleSoundsJsonForMod(this.getMod()));
    }
}
