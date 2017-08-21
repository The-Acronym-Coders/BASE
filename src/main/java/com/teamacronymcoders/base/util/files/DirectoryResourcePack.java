package com.teamacronymcoders.base.util.files;

import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DirectoryResourcePack extends AbstractResourcePack {
    private Set<String> domains;

    public DirectoryResourcePack(File resourceFolder) {
        super(resourceFolder);
    }

    @Override
    @Nonnull
    protected InputStream getInputStreamByName(@Nonnull String name) throws IOException {
        return new FileInputStream(this.getFile(name));
    }

    @Override
    protected boolean hasResourceName(@Nonnull String name) {
        return this.getFile(name).exists();
    }

    private File getFile(String name) {
        return new File(this.resourcePackFile, name.replace("assets/", ""));
    }

    @Override
    @Nonnull
    public Set<String> getResourceDomains() {
        if (domains == null) {
            this.domains = new HashSet<>();
            String[] folderNames = this.resourcePackFile.list();
            if (folderNames != null) {
                domains.addAll(Arrays.asList(folderNames));
            }
        }
        return domains;
    }
}
