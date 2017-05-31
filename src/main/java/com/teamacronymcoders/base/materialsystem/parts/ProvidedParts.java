package com.teamacronymcoders.base.materialsystem.parts;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.blocks.BlockMaterialFluid;
import com.teamacronymcoders.base.materialsystem.blocks.SubBlockOrePart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPartData;
import com.teamacronymcoders.base.materialsystem.parttype.*;
import com.teamacronymcoders.base.registry.BlockRegistry;
import com.teamacronymcoders.base.subblocksystem.SubBlockSystem;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.List;

import static net.minecraftforge.fml.common.registry.ForgeRegistries.BLOCKS;

public class ProvidedParts {
    private IBaseMod mod;
    private MaterialSystem materialSystem;


    public ProvidedParts(IBaseMod mod, MaterialSystem materialSystem, SubBlockSystem subBlockSystem) {
        this.mod = mod;
        this.materialSystem = materialSystem;
    }

    public void initPartsAndTypes() {
        PartType item = new ItemPartType(this.mod);
        PartType block = new BlockPartType(this.mod);
        PartType ore = new OrePartType(this.mod);
        PartType fluid = new FluidPartType(this.mod);

        materialSystem.registerPartType(item);
        materialSystem.registerPartType(block);
        materialSystem.registerPartType(ore);
        materialSystem.registerPartType(fluid);

        registerPart(new PartBuilder(materialSystem).setName("Ingot").setPartType(item));
        registerPart(new PartBuilder(materialSystem).setName("Beam").setPartType(item));
        registerPart(new PartBuilder(materialSystem).setName("Gear").setPartType(item));
        registerPart(new PartBuilder(materialSystem).setName("Bolt").setPartType(item));
        registerPart(new PartBuilder(materialSystem).setName("Dust").setPartType(item));
        registerPart(new PartBuilder(materialSystem).setName("Plate").setPartType(item));
        registerPart(new PartBuilder(materialSystem).setName("Nugget").setPartType(item));

        List<PartDataPiece> blockDataPieces = Lists.newArrayList();
        blockDataPieces.add(new PartDataPiece("hardness", false));
        blockDataPieces.add(new PartDataPiece("resistance", false));
        blockDataPieces.add(new PartDataPiece("harvestLevel", false));
        blockDataPieces.add(new PartDataPiece("harvestTool", false));
        registerPart(new PartBuilder(materialSystem).setName("Block").setPartType(block).setData(blockDataPieces));

        List<PartDataPiece> oreDataPieces = Lists.newArrayList();
        oreDataPieces.addAll(blockDataPieces);
        oreDataPieces.add(new PartDataPiece("variants", false));
        oreDataPieces.add(new PartDataPiece("dropType", false));
        registerPart(new PartBuilder(materialSystem).setName("Ore").setPartType(ore).setData(oreDataPieces));
        registerPart(new PartBuilder(materialSystem).setName("Poor Ore").setPartType(ore).setData(oreDataPieces));
        registerPart(new PartBuilder(materialSystem).setName("Dense Ore").setPartType(ore).setData(oreDataPieces));

        List<PartDataPiece> fluidDataPieces = Lists.newArrayList();
        fluidDataPieces.add(new PartDataPiece("temperature"));
        fluidDataPieces.add(new PartDataPiece("density"));
        fluidDataPieces.add(new PartDataPiece("viscosity"));
        fluidDataPieces.add(new PartDataPiece("vaporize"));
        registerPart(new PartBuilder(materialSystem).setName("Fluid").setPartType(fluid).setData(fluidDataPieces));
    }

    private void registerPart(PartBuilder partBuilder) {
        try {
            partBuilder.build();
        } catch (MaterialException e) {
            mod.getLogger().getLogger().error(e);
        }
    }
}
