package com.teamacronymcoders.base.materialsystem.entities;

import com.teamacronymcoders.base.materialsystem.MaterialPart;
import com.teamacronymcoders.base.materialsystem.MaterialsSystem;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntityMaterialGolem extends EntityIronGolem {
    private DataParameter<MaterialPart> MATERIAL_PART = EntityDataManager.createKey(EntityMaterialGolem.class,
            MaterialsSystem.MATERIAL_PART_SERIALIZER);

    public EntityMaterialGolem(World world) {
        super(world);
    }

    public EntityMaterialGolem(World world, MaterialPart materialPart) {
        super(world);
        setMaterialPart(materialPart);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(MATERIAL_PART, MaterialsSystem.MISSING_MATERIAL_PART);
    }

    public MaterialPart getMaterialPart() {
        return this.dataManager.get(MATERIAL_PART);
    }

    public void setMaterialPart(MaterialPart materialPart) {

        this.dataManager.set(MATERIAL_PART, materialPart);
    }
}
