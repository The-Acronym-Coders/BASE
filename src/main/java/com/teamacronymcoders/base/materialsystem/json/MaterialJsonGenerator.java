package com.teamacronymcoders.base.materialsystem.json;

import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.util.files.BaseFileUtils;

import java.io.File;

public class MaterialJsonGenerator {
    public static void generate(MaterialSystem materialSystem, File resourceFolder) {
        File blockstates = new File(resourceFolder, "blockstates");
        BaseFileUtils.createFolder(blockstates);
        File materialBlockStates = new File(resourceFolder, "materials");
        BaseFileUtils.createFolder(materialBlockStates);
        materialSystem.getMaterialParts().forEach((id, material) -> {

        });
    }
}
