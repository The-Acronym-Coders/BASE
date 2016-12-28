package com.teamacronymcoders.base.modules.minetweaker;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.api.materials.MaterialType;
import com.teamacronymcoders.base.modules.materials.blocks.BlockMaterial;
import com.teamacronymcoders.base.modules.materials.blocks.BlockProperties;
import com.teamacronymcoders.base.registry.BlockRegistry;
import minetweaker.MineTweakerAPI;

/**
 * Created by Jared.
 */
public class BaseMaterialType implements IMaterialType {
    private final MaterialType type;

    public BaseMaterialType(MaterialType type) {
        this.type = type;
    }

    @Override
    public void registerBlock(float hardness, float resistance, String toolClass, int toolTier) {
        MineTweakerAPI.apply(new Materials.Change(type, MaterialType.EnumPartType.BLOCK));
        BlockProperties blockProperties = new BlockProperties(hardness, resistance, toolClass, toolTier);
        BlockMaterial blockMaterial = new BlockMaterial(type, MaterialType.EnumPartType.BLOCK, blockProperties);
        Base.instance.getRegistry(BlockRegistry.class, "BLOCK").register(blockMaterial);
    }

    @Override
    public void registerDust() {
        MineTweakerAPI.apply(new Materials.Change(type, MaterialType.EnumPartType.DUST));
    }

    @Override
    public void registerGear() {
        MineTweakerAPI.apply(new Materials.Change(type, MaterialType.EnumPartType.GEAR));
    }

    @Override
    public void registerIngot() {
        MineTweakerAPI.apply(new Materials.Change(type, MaterialType.EnumPartType.INGOT));
    }

    @Override
    public void registerNugget() {
        MineTweakerAPI.apply(new Materials.Change(type, MaterialType.EnumPartType.NUGGET));
    }

    @Override
    public void registerOre(float hardness, float resistance, String toolClass, int toolTier) {
        MineTweakerAPI.apply(new Materials.Change(type, MaterialType.EnumPartType.ORE));
        BlockProperties blockProperties = new BlockProperties(hardness, resistance, toolClass, toolTier);
        BlockMaterial blockMaterial = new BlockMaterial(type, MaterialType.EnumPartType.ORE, blockProperties);
        Base.instance.getRegistry(BlockRegistry.class, "BLOCK").register(blockMaterial);
    }

    @Override
    public void registerPlate() {
        MineTweakerAPI.apply(new Materials.Change(type, MaterialType.EnumPartType.PLATE));
    }

    @Override
    public Object getInternal() {
        return type;
    }
}
