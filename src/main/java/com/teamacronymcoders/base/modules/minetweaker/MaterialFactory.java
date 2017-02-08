package com.teamacronymcoders.base.modules.minetweaker;

import com.teamacronymcoders.base.Reference;
import com.teamacronymcoders.base.api.materials.MaterialRegistry;
import com.teamacronymcoders.base.api.materials.MaterialType;
import com.teamacronymcoders.base.exceptions.TooLateException;
import com.teamacronymcoders.base.materialsystem.MaterialPart;
import com.teamacronymcoders.base.materialsystem.MaterialsSystem;
import com.teamacronymcoders.base.materialsystem.materials.Material;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.materialsystem.parts.PartType;
import minetweaker.IUndoableAction;
import net.minecraft.util.ResourceLocation;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.awt.*;

import static com.teamacronymcoders.base.Reference.MODID;

@ZenClass("mods.base.MaterialFactory")
public class MaterialFactory {
    public MaterialFactory() {

    }

    @ZenMethod
    public static Material createMaterial(String name) {
        checkIfTooLate();
        return new Material(name);
    }

    @ZenMethod
    public static Material createMaterial(String name, int color, boolean hasEffect) {
        checkIfTooLate();
        return new Material(name, new Color(color), hasEffect);
    }

    @ZenMethod
    public static Part createPart(String name, String partTypeName) {
        checkIfTooLate();
        return new Part(name, PartType.valueOf(partTypeName));
    }

    @ZenMethod
    public static MaterialPart getMaterialPart(Material material, Part part) {
        return MaterialsSystem.MATERIAL_PARTS.getValue(new ResourceLocation(material.getUnlocalizedName(), part.getUnlocalizedName()));
    }

    public static void checkIfTooLate() {
        if(MinetweakerModule.tooLate) {
            throw new TooLateException("You have to put scripts using BASE Functionality in ./config/ACRONYM/BASE/scripts");
        }
    }

    private static class Add implements IUndoableAction {
        private MaterialPart materialPart;

        public Add(MaterialPart materialPart) {
            this.materialPart = materialPart;
        }

        @Override
        public void apply() {
            if(MaterialsSystem.MATERIAL_PARTS.containsKey(materialPart.getRegistryName())) {
                MaterialsSystem.MATERIAL_PARTS.register(materialPart);
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
            return String.format("[%s] Registering Part, %s", MODID, materialPart.getName());
        }

        @Override
        public String describeUndo() {
            return String.format("[%s] Unable to remove Part, %s, while the game is running!", MODID, materialPart.getName());
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }
    }
}
