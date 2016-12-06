package com.teamacronymcoders.base.modules.materials.blocks;

import net.minecraft.block.Block;

public class BlockProperties {
    private float hardness;
    private float resistance;
    private int toolTier;
    private String toolClass;

    public BlockProperties(float hardness, float resistance, String toolClass, int toolTier) {
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setToolClass(toolClass);
        this.setToolTier(toolTier);
    }

    public float getHardness() {
        return hardness;
    }

    public void setHardness(float hardness) {
        this.hardness = hardness;
    }

    public float getResistance() {
        return resistance;
    }

    public void setResistance(float resistance) {
        this.resistance = resistance;
    }

    public int getToolTier() {
        return toolTier;
    }

    public void setToolTier(int toolTier) {
        this.toolTier = toolTier;
    }

    public String getToolClass() {
        return toolClass;
    }

    public void setToolClass(String toolClass) {
        this.toolClass = toolClass;
    }

    public void setPropertiesToBlock(Block block) {
        block.setHardness(this.getHardness());
        block.setResistance(this.getResistance());
        block.setHarvestLevel(this.getToolClass(), this.getToolTier());
    }
}
