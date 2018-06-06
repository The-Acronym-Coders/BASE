package com.teamacronymcoders.base.client.models.sided;

import com.google.common.collect.Maps;
import com.teamacronymcoders.base.blocks.properties.SideType;
import net.minecraft.util.EnumFacing;

import java.util.Map;
import java.util.Set;

public class SidedTypeRegistry {
    private static final Map<String, ITextureNamer> TEXTURE_NAMERS = Maps.newHashMap();

    static {
        //every side seperately
        addTextureNamer("all6", new ITextureNamer() {
        });
        //all sides, same texture
        addTextureNamer("s", new ITextureNamer() {
            @Override
            public String nameFromSide(EnumFacing side, SideType cfg) {
                return "side";
            }
        });
        //horizontal, up, down
        addTextureNamer("hud", new ITextureNamer() {
            @Override
            public String nameFromSide(EnumFacing side, SideType cfg) {
                return side.ordinal() < 2 ? side.getName() : "side";
            }
        });
        //horizontal, vertical
        addTextureNamer("hv", new ITextureNamer() {
            @Override
            public String nameFromSide(EnumFacing side, SideType cfg) {
                return side.ordinal() < 2 ? "up" : "side";
            }
        });
        //up, down, sides not configureable
        addTextureNamer("ud", new ITextureNamer() {
            @Override
            public String nameFromSide(EnumFacing side, SideType cfg) {
                return side.ordinal() < 2 ? side.getName() : "side";
            }

            @Override
            public String nameFromCfg(EnumFacing side, SideType cfg) {
                return side.ordinal() < 2 ? cfg.getName() : null;
            }
        });
        //vertical, sides not configureable
        addTextureNamer("v", new ITextureNamer() {
            @Override
            public String nameFromSide(EnumFacing side, SideType cfg) {
                return side.ordinal() < 2 ? "up" : "side";
            }

            @Override
            public String nameFromCfg(EnumFacing side, SideType cfg) {
                return side.ordinal() < 2 ? cfg.getName() : null;
            }
        });
    }

    public static void addTextureNamer(String name, ITextureNamer textureNamer) {
        TEXTURE_NAMERS.put(name, textureNamer);
    }

    public static ITextureNamer getTextureNamer(String name) {
        return TEXTURE_NAMERS.get(name);
    }

    public static Set<Map.Entry<String, ITextureNamer>> getEntries() {
        return TEXTURE_NAMERS.entrySet();
    }
}
