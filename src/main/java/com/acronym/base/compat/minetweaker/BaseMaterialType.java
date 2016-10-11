package com.acronym.base.compat.minetweaker;

import com.acronym.base.api.materials.MaterialType;
import com.acronym.base.blocks.BaseBlocks;
import com.acronym.base.blocks.BlockOre;
import com.acronym.base.blocks.sets.wood.BlockStorage;
import com.acronym.base.items.ItemBlockOre;
import com.acronym.base.items.ItemBlockStorage;
import com.acronym.base.reference.Reference;
import minetweaker.MineTweakerAPI;

import static com.acronym.base.blocks.BaseBlocks.oreBlockMap;
import static com.acronym.base.blocks.BaseBlocks.storageBlockMap;
import static com.acronym.base.reference.Reference.tab;

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
        BlockStorage ore = new BlockStorage(type, hardness, resistance, toolClass, toolTier);
        storageBlockMap.put(type, BaseBlocks.registerBlock(ore, Reference.MODID, "storage_" + type.getName().toLowerCase(), "%s Block", "storage", null, tab, new ItemBlockStorage(ore)));
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
        BlockOre ore = new BlockOre(type, hardness, resistance, toolClass, toolTier);
        oreBlockMap.put(type, BaseBlocks.registerBlock(ore, Reference.MODID, "ore_" + type.getName().toLowerCase(), "%s Ore", "ore", null, tab, new ItemBlockOre(ore)));
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
