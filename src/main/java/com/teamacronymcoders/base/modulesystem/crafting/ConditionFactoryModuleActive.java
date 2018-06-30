package com.teamacronymcoders.base.modulesystem.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.teamacronymcoders.base.BaseMods;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.modulesystem.ModuleHandler;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;

import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;

public class ConditionFactoryModuleActive implements IConditionFactory {
    @Override
    public BooleanSupplier parse(JsonContext context, JsonObject json) {
        String moduleName = JsonUtils.getString(json, "name");

        String modName = Optional.ofNullable(json.get("mod"))
                .map(JsonElement::getAsString)
                .orElse(context.getModId());

        String moduleHandlerName = Optional.ofNullable(json.get("handler"))
                .map(JsonElement::getAsString)
                .orElse(modName);

        IBaseMod mod = Optional.ofNullable(BaseMods.getBaseMod(modName))
                .orElse(BaseMods.getBaseMod(moduleHandlerName));
        if (mod != null) {
            if (mod.getID().equals(moduleHandlerName)) {
                return () -> mod.getModuleHandler().isModuleEnabled(moduleName);
            } else {
                List<ModuleHandler> moduleHandlerList = mod.getOtherModuleHandlers();
                for (ModuleHandler moduleHandler : moduleHandlerList) {
                    if (moduleHandlerName.equals(moduleHandler.getName())) {
                        return () -> moduleHandler.isModuleEnabled(moduleName);
                    }
                }
                return () -> false;
            }
        } else {
            return () -> false;
        }
    }
}
