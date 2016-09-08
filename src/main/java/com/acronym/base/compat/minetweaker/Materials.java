package com.acronym.base.compat.minetweaker;

import com.acronym.base.api.materials.MaterialRegistry;
import com.acronym.base.api.materials.MaterialType;
import com.acronym.base.reference.Reference;
import com.acronym.base.util.IMetaItem;
import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.awt.*;
import java.util.Map;

import static com.acronym.base.items.BaseItems.renderMap;

/**
 * Created by Jared.
 */
@ZenClass("mods.base.Materials")
public class Materials {

    @ZenMethod
    public static void add(String name, int colour, String[] types) {
        MaterialType.EnumPartType[] partTypes = new MaterialType.EnumPartType[types.length];
        for (int i = 0; i < types.length; i++) {
            partTypes[i] = MaterialType.EnumPartType.valueOf(types[i]);
        }
        Color col = new Color(colour);
        MineTweakerAPI.apply(new Add(name, col, partTypes));
    }


    private static class Add implements IUndoableAction {
        private String name;
        private Color colour;
        private MaterialType.EnumPartType[] types;

        public Add(String name, Color colour, MaterialType.EnumPartType[] types) {
            this.name = name;
            this.colour = colour;
            this.types = types;
        }

        @Override
        public void apply() {
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            MaterialRegistry.registerMaterial(name, new MaterialType(name, colour, types));
            for (Map.Entry<String, Item> ent : renderMap.entrySet()) {
                if (ent.getValue() instanceof IMetaItem) {
                    IMetaItem metaItem = (IMetaItem) ent.getValue();
                    for (int i : metaItem.getMetaData()) {
                        renderItem.getItemModelMesher().register(ent.getValue(), i, new ModelResourceLocation(Reference.MODID + ":" + ent.getKey(), "inventory"));
                    }
                } else renderItem.getItemModelMesher().register(ent.getValue(), 0, new ModelResourceLocation(Reference.MODID + ":" + ent.getKey(), "inventory"));
            }
        }

        @Override
        public boolean canUndo() {
            return true;
        }

        @Override
        public void undo() {
            MaterialRegistry.unregisterMaterial(name);
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
