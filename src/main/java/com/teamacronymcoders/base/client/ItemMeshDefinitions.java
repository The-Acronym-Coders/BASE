package com.teamacronymcoders.base.client;

import com.teamacronymcoders.base.items.IHasItemMeshDefinition;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class ItemMeshDefinitions {
    public static void registerItemMeshDefinitions(IHasItemMeshDefinition itemMeshDefinition) {
        ModelLoader.setCustomMeshDefinition(itemMeshDefinition.getItem(), new ItemMeshDefinition() {
            @Override
            @Nonnull
            public ModelResourceLocation getModelLocation(@Nonnull ItemStack stack) {
                return new ModelResourceLocation(itemMeshDefinition.getResourceLocation(stack), itemMeshDefinition.getVariant(stack));
            }
        });
    }
}
