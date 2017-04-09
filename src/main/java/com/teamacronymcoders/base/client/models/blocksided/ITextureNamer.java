package com.teamacronymcoders.base.client.models.blocksided;

import com.teamacronymcoders.base.blocks.properties.SideType;
import net.minecraft.util.EnumFacing;

public interface ITextureNamer {
    default String getTextureName(EnumFacing side, SideType cfg) {
        String s = nameFromSide(side, cfg);
        String c = nameFromCfg(side, cfg);
        String textureName = "";
        if (s != null && c != null) {
            textureName =  s + "_" + c;
        } else if (s != null) {
            textureName =  s;
        } else if (c != null) {
            textureName =  c;
        }
        return textureName;
    }

    default String nameFromSide(EnumFacing side, SideType cfg) {
        return side.getName();
    }

    default String nameFromCfg(EnumFacing side, SideType cfg) {
        return cfg.getName();
    }
}
