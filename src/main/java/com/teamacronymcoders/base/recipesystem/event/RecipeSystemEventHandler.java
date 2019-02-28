package com.teamacronymcoders.base.recipesystem.event;

import com.teamacronymcoders.base.recipesystem.RecipeSystem;
import com.teamacronymcoders.base.recipesystem.condition.BiomeCondition;
import com.teamacronymcoders.base.recipesystem.condition.ICondition;
import com.teamacronymcoders.base.recipesystem.condition.VillageCondition;
import com.teamacronymcoders.base.recipesystem.input.BlockStateInput;
import com.teamacronymcoders.base.recipesystem.input.EntityInput;
import com.teamacronymcoders.base.recipesystem.input.ForgeEnergyInput;
import com.teamacronymcoders.base.recipesystem.input.IInput;
import com.teamacronymcoders.base.recipesystem.loader.JsonRecipeLoader;
import com.teamacronymcoders.base.recipesystem.loader.ILoader;
import com.teamacronymcoders.base.recipesystem.output.*;
import com.teamacronymcoders.base.recipesystem.output.json.OneOfOutputFactory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.teamacronymcoders.base.Reference.MODID;

@EventBusSubscriber(modid = MODID)
public class RecipeSystemEventHandler {
    @SubscribeEvent
    public static void registerRecipes(RegistryEvent<IRecipe> recipeRegistryEvent) {
        RecipeSystem.loadRecipeTypes();
        RecipeSystem.loadRecipes(false);
    }

    @SubscribeEvent
    public static void registerConditions(RegisterRecipeFactoriesEvent<ICondition> conditionEvent) {
        conditionEvent.register(new ResourceLocation(MODID, "biome"), BiomeCondition.class);
        conditionEvent.register(new ResourceLocation(MODID, "village"), VillageCondition.class);
    }

    @SubscribeEvent
    public static void registerInput(RegisterRecipeFactoriesEvent<IInput> inputEvent) {
        inputEvent.register(new ResourceLocation(MODID, "blockstate"), BlockStateInput.class);
        inputEvent.register(new ResourceLocation(MODID, "forge_energy"), ForgeEnergyInput.class);
        inputEvent.register(new ResourceLocation(MODID, "entity"), EntityInput.class);
    }

    @SubscribeEvent
    public static void registerOutput(RegisterRecipeFactoriesEvent<IOutput> outputEvent) {
        outputEvent.register(new ResourceLocation(MODID, "command"), CommandOutput.class);
        outputEvent.register(new ResourceLocation(MODID, "blockstate"), BlockStateOutput.class);
        outputEvent.register(new ResourceLocation(MODID, "one_of"), new OneOfOutputFactory());
        outputEvent.register(new ResourceLocation(MODID, "entity"), EntityOutput.class);
        outputEvent.register(new ResourceLocation(MODID, "explosion"), ExplosionOutput.class);
    }

    @SubscribeEvent
    public static void registerLoader(BaseRegistryEvent<ILoader> loaderRegistryEvent) {
        loaderRegistryEvent.register(new ResourceLocation(MODID, "json"), JsonRecipeLoader.getInstance());
    }
}
