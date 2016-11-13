package com.teamacronymcoders.base.api.structures;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public abstract class StructurePart {

    //Name - Key for this part
    private final String name;
    //parts that can be adjacent to this part / parts that appear seamless with this part / Facing, List of part Keys / which direction it is seamless
    private HashMap<EnumFacing, List<String>> validAdjacentParts;

    public StructurePart(String name) {
        this.name = name;
        this.validAdjacentParts = new LinkedHashMap<>();
    }

    public abstract boolean generate(World world, BlockPos pos);


    public String getName() {
        return name;
    }

    public HashMap<EnumFacing, List<String>> getValidAdjacentParts() {
        return validAdjacentParts;
    }

    public void setValidAdjacentParts(HashMap<EnumFacing, List<String>> validAdjacentParts) {
        this.validAdjacentParts = validAdjacentParts;
    }
}
