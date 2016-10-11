package com.acronym.base.compat.minetweaker;

import com.acronym.base.api.materials.MaterialRegistry;
import com.acronym.base.api.materials.MaterialType;
import com.acronym.base.reference.Reference;
import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static com.acronym.base.api.materials.MaterialType.EnumPartType.GEAR;
import static com.acronym.base.data.Materials.IRON;

/**
 * Created by Jared.
 */
@ZenClass("mods.base.Materials")
public class Materials {

    @ZenMethod
    public static IMaterialType getOrRegister(String name) {
        MineTweakerAPI.logInfo("size: " + MaterialRegistry.getMaterials().entrySet().size());
        if (MaterialRegistry.isRegistered(name)) {
            return new BaseMaterialType(MaterialRegistry.getFromName(name));
        }
        return new BaseMaterialType(IRON);
    }

    @ZenMethod
    public static void add(String name, int ID, int colour, boolean hasEffect, String[] types) {
        MaterialType.EnumPartType[] partTypes = new MaterialType.EnumPartType[types.length];
        for (int i = 0; i < types.length; i++) {
            partTypes[i] = MaterialType.EnumPartType.valueOf(types[i]);
        }
        Color col = new Color(colour);
        MineTweakerAPI.apply(new Add(name, ID, col, hasEffect, partTypes));
    }

    public static class Change implements IUndoableAction {
        private MaterialType type;

        public Change(MaterialType type) {
            this.type = type; //MaterialRegistry.getFromName(name);
        }

        @Override
        public void apply() {
            System.out.println("registering");
            System.out.println(type);
            System.out.println(type.getTypes());
            MineTweakerAPI.logInfo(type.getTypes().toString());
            type.getTypes().add(GEAR);
            MineTweakerAPI.logInfo(type.getTypes().toString());
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
            return String.format("[%s] Registering Material, %s", Reference.MODID, type.getName());
        }

        @Override
        public String describeUndo() {
            return String.format("[%s] Unregistering Material, %s", Reference.MODID, type.getName());
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }
    }

    private static class Add implements IUndoableAction {
        private String name;
        private int ID;
        private Color colour;
        private boolean hasEffect;
        private MaterialType.EnumPartType[] types;

        public Add(String name, int ID, Color colour, boolean hasEffect, MaterialType.EnumPartType[] types) {
            this.name = name;
            this.ID = ID;
            this.colour = colour;
            this.hasEffect = hasEffect;
            this.types = types;
        }

        @Override
        public void apply() {
            if (!MaterialRegistry.isRegistered(name)) {
                MaterialRegistry.registerMaterial(ID, new MaterialType(name, colour, hasEffect, new ArrayList<MaterialType.EnumPartType>(Arrays.asList(types))));
            }
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
