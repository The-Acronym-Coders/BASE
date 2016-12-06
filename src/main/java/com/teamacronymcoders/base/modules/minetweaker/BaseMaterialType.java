package com.teamacronymcoders.base.modules.minetweaker;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.api.materials.MaterialType;
import com.teamacronymcoders.base.blocks.BaseBlocks;
import com.teamacronymcoders.base.blocks.BlockOre;
import com.teamacronymcoders.base.blocks.BlockStorage;
import com.teamacronymcoders.base.items.ItemBlockOre;
import com.teamacronymcoders.base.items.ItemBlockStorage;
import com.teamacronymcoders.base.reference.Reference;
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
        BlockStorage ore = new BlockStorage(type, hardness, resistance, toolClass, toolTier);
        BaseBlocks.storageBlockMap.put(type, BaseBlocks.registerBlock(ore, Reference.MODID, "storage_" + type.getName().toLowerCase(), "%s Block", "storage", null, Base.instance.getCreativeTab(), new ItemBlockStorage(ore)));
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
        BaseBlocks.oreBlockMap.put(type, BaseBlocks.registerBlock(ore, Reference.MODID, "ore_" + type.getName().toLowerCase(), "%s Ore", "ore", null, Base.instance.getCreativeTab(), new ItemBlockOre(ore)));
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
