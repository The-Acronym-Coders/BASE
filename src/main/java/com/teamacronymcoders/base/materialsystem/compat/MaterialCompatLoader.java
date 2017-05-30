package com.teamacronymcoders.base.materialsystem.compat;

import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.util.ClassLoading;
import net.minecraftforge.fml.common.discovery.ASMDataTable;

import java.util.List;

public class MaterialCompatLoader {
    private List<IMaterialCompat> materialCompatList;

    public void loadCompat(ASMDataTable dataTable) {
        materialCompatList = ClassLoading.getInstances(dataTable, MaterialCompat.class, IMaterialCompat.class);
    }

    public void doCompat(MaterialSystem materialSystem) {
        materialCompatList.forEach(materialCompat -> {
            try {
                materialCompat.doCompat(materialSystem);
            } catch (MaterialException e) {
                materialSystem.getMod().getLogger().warning(e.getMessage());
            }
        });
    }
}
