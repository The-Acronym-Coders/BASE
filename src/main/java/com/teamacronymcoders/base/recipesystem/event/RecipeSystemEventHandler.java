package com.teamacronymcoders.base.recipesystem.event;

import com.teamacronymcoders.base.recipesystem.RecipeSystem;
import com.teamacronymcoders.base.recipesystem.condition.BiomeCondition;
import com.teamacronymcoders.base.recipesystem.condition.ICondition;
import com.teamacronymcoders.base.recipesystem.input.BlockStateInput;
import com.teamacronymcoders.base.recipesystem.input.ForgeEnergyInput;
import com.teamacronymcoders.base.recipesystem.input.IInput;
import com.teamacronymcoders.base.recipesystem.output.BlockStateOutput;
import com.teamacronymcoders.base.recipesystem.output.IOutput;
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
        RecipeSystem.loadRecipes();
    }

    @SubscribeEvent
    public static void registerConditions(RegisterRecipeFactoriesEvent<ICondition> conditionEvent) {
        conditionEvent.register(new ResourceLocation(MODID, "in_biome"), BiomeCondition.class);
    }

    @SubscribeEvent
    public static void registerInput(RegisterRecipeFactoriesEvent<IInput> inputEvent) {
        inputEvent.register(new ResourceLocation(MODID, "blockstate"), BlockStateInput.class);
        inputEvent.register(new ResourceLocation(MODID, "forge_energy"), ForgeEnergyInput.class);
    }

    @SubscribeEvent
    public static void registerOutput(RegisterRecipeFactoriesEvent<IOutput> outputEvent) {
        outputEvent.register(new ResourceLocation(MODID, "blockstate"), BlockStateOutput.class);
    }
}
