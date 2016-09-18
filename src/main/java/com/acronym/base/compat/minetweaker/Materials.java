package com.acronym.base.compat.minetweaker;

import com.acronym.base.api.materials.MaterialRegistry;
import com.acronym.base.api.materials.MaterialType;
import com.acronym.base.reference.Reference;
import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.awt.*;

/**
 * Created by Jared.
 */
@ZenClass("mods.base.Materials")
public class Materials {

    @ZenMethod
    public static void add(String name, int colour, boolean hasEffect, String[] types) {
        MaterialType.EnumPartType[] partTypes = new MaterialType.EnumPartType[types.length];
        for (int i = 0; i < types.length; i++) {
            partTypes[i] = MaterialType.EnumPartType.valueOf(types[i]);
        }
        Color col = new Color(colour);
        MineTweakerAPI.apply(new Add(name, col, hasEffect, partTypes));
    }


    private static class Add implements IUndoableAction {
        private String name;
        private Color colour;
        private boolean hasEffect;
        private MaterialType.EnumPartType[] types;

        public Add(String name, Color colour, boolean hasEffect, MaterialType.EnumPartType[] types) {
            this.name = name;
            this.colour = colour;
            this.hasEffect = hasEffect;
            this.types = types;
        }

        @Override
        public void apply() {
            if (!MaterialRegistry.isRegistered(name)) {
                MaterialRegistry.registerMaterial(name, new MaterialType(name, colour, hasEffect, types));
            }
//            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

//            for (Map.Entry<String, Item> ent : renderMap.entrySet()) {
//                if (ent.getValue() instanceof IMetaItem) {
//                    IMetaItem metaItem = (IMetaItem) ent.getValue();
//                    for (int i : metaItem.getMetaData()) {
//                        renderItem.getItemModelMesher().register(ent.getValue(), i, new ModelResourceLocation(Reference.MODID + ":" + ent.getKey(), "inventory"));
//                    }
//                } else renderItem.getItemModelMesher().register(ent.getValue(), 0, new ModelResourceLocation(Reference.MODID + ":" + ent.getKey(), "inventory"));
//            }
//            if(Arrays.asList(types).contains(MaterialType.EnumPartType.BLOCK) || Arrays.asList(types).contains(MaterialType.EnumPartType.BLOCK)){
//
//            }
        }

        @Override
        public boolean canUndo() {
            return true;
        }

        @Override
        public void undo() {
//            MaterialRegistry.unregisterMaterial(name);
        }

        @Override
        public String describe() {
            return String.format("[%s] Registering Material, %s", Reference.MODID, name);
        }

        @Override
        public String describeUndo() {
            return String.format("[%s] Unregistering Material, %s", Reference.MODID, name);
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }
    }

}
