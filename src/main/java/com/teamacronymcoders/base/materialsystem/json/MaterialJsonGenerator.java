package com.teamacronymcoders.base.materialsystem.json;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.json.resources.IResource;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.util.files.BaseFileUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SideOnly(Side.CLIENT)
public class MaterialJsonGenerator {
    public static void generate(MaterialSystem materialSystem, File resourceFolder) {
        File blockstates = new File(resourceFolder, "blockstates");
        BaseFileUtils.createFolder(blockstates);
        File materialBlockStates = new File(resourceFolder, "materials");
        BaseFileUtils.createFolder(materialBlockStates);

        Stream<MaterialPart> materialPartStream = materialSystem.getMaterialParts().values().stream();
        List<IResource> resources = Lists.newArrayList();
        materialPartStream.forEach(materialPart -> resources.addAll(materialPart.generateResources()));
        resources.forEach(resource -> {
            File file = null;
            switch(resource.getResourceType()) {
                case BLOCKSTATE:
                    file = new File(materialBlockStates, resource.getName() + ".json");
                    break;
                case BLOCK_MODEL:
                    break;
                case ITEM_MODEL:
                    break;
                case TEXTURE:
                    break;
            }
            if (file != null && !file.exists()) {
                BaseFileUtils.writeStringToFile(resource.getJson(), file);
            }

        });

    }
}
