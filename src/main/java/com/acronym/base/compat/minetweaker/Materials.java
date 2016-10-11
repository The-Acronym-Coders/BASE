package com.acronym.base.compat.minetweaker;

import com.acronym.base.api.materials.MaterialRegistry;
import com.acronym.base.api.materials.MaterialType;
import com.acronym.base.reference.Reference;
import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.awt.*;

/**
 * Created by Jared.
 */
@ZenClass("mods.base.Materials")
public class Materials {

    @ZenMethod
    public static IMaterialType getOrRegister(String name, @Optional int ID, @Optional int colour, @Optional boolean hasEffect) {
        if (MaterialRegistry.isRegistered(name)) {
            return new BaseMaterialType(MaterialRegistry.getFromName(name));
        }
        if (ID == 0) {
            MineTweakerAPI.logError("ID is either missing or is 0!");
            return null;
        }
        Color col = new Color(colour);
        MineTweakerAPI.apply(new Add(name, ID, col, hasEffect));
        return new BaseMaterialType(MaterialRegistry.getFromName(name));
    }

    public static class Change implements IUndoableAction {
        private MaterialType mat;
        private MaterialType.EnumPartType type;

        public Change(MaterialType mat, MaterialType.EnumPartType type) {
            this.mat = mat;
            this.type = type; //MaterialRegistry.getFromName(name);

        }

        @Override
        public void apply() {
            mat.getTypes().add(type);
        }

        @Override
        public boolean canUndo() {
            return true;
        }

        @Override
        public void undo() {
//            mat.getTypes().remove(type);
        }

        @Override
        public String describe() {
            return String.format("[%s] Registering Part Type, %s, for Material, %s", Reference.MODID, type.getName(), mat.getName());
        }

        @Override
        public String describeUndo() {
            return String.format("[%s] Removing Part Type, %s, for Material, %s", Reference.MODID, type.getName(), mat.getName());
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

        public Add(String name, int ID, Color colour, boolean hasEffect) {
            this.name = name;
            this.ID = ID;
            this.colour = colour;
            this.hasEffect = hasEffect;
        }

        @Override
        public void apply() {
            if (!MaterialRegistry.isRegistered(name)) {
                MaterialRegistry.registerMaterial(ID, new MaterialType(name, colour, hasEffect));
            }
        }

        @Override
        public boolean canUndo() {
            return true;
        }

        @Override
        public void undo() {
        }

        @Override
        public String describe() {
            return String.format("[%s] Registering Material, %s", Reference.MODID, name);
        }

        @Override
        public String describeUndo() {
            return String.format("[%s] Unable to remove Material, %s, while the game is running!", Reference.MODID, name);
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }
    }

}
