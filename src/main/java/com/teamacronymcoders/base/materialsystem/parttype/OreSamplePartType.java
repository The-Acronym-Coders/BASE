package com.teamacronymcoders.base.materialsystem.parttype;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.materialsystem.blocks.SubBlockOreSamplePart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;

import javax.annotation.Nonnull;
import java.util.List;

public class OreSamplePartType extends BlockPartType {
    public static final String DROP_DATA_NAME = "drops";
    public static final String REQUIRE_TOOL_DATA_NAME = "requireTool";
    public static final String ACTIVATED_TEXT_DATA_NAME = "activatedText";

    public OreSamplePartType() {
        super("Ore Sample", setupOreSampleData());
    }

    private static List<PartDataPiece> setupOreSampleData() {
        List<PartDataPiece> oreDataPieces = Lists.newArrayList();
        oreDataPieces.add(new PartDataPiece(DROP_DATA_NAME, false));
        oreDataPieces.add(new PartDataPiece(REQUIRE_TOOL_DATA_NAME, false));
        oreDataPieces.add(new PartDataPiece(ACTIVATED_TEXT_DATA_NAME, false));
        return oreDataPieces;
    }

    @Override
    public void setup(@Nonnull MaterialPart materialPart, @Nonnull MaterialUser materialUser) {
        registerSubBlock(materialPart, new SubBlockOreSamplePart(materialPart, materialUser));
    }
}
