package com.acronym.base.data;

import com.acronym.base.api.materials.Material;
import com.acronym.base.api.materials.Resource;
import com.acronym.base.api.materials.registries.MaterialRegistry;
import com.acronym.base.util.ColourHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static net.minecraft.client.Minecraft.getMinecraft;

/**
 * Created by Jared on 6/27/2016.
 */
public class Recipes {

    public static void preInit() {

    }

    public static void init() {
        MaterialRegistry.addMaterial("Copper", Materials.copper);
        MaterialRegistry.addMaterial("Tin", Materials.tin);
        for (String s : OreDictionary.getOreNames()) {
            if (s.startsWith("ingot") && !OreDictionary.getOres("ore" + s.substring(5)).isEmpty()) {
                String name = s.substring(5);
                List<ItemStack> list = OreDictionary.getOres(s);
                for (ItemStack item : list) {
                    //TODO replace reflection with an AT
                    Map<String, TextureAtlasSprite> map = ReflectionHelper.getPrivateValue(TextureMap.class, getMinecraft().getTextureMapBlocks(), "mapRegisteredSprites");
                    for (Map.Entry<String, TextureAtlasSprite> entry : map.entrySet()) {
                        if (entry.getKey().endsWith(item.getItem().getRegistryName().getResourcePath())) {
                            try {
                                ResourceLocation res = new ResourceLocation(entry.getKey().split(":")[0], "textures/" + entry.getValue().getIconName().split(":")[1] + ".png");
                                int colour = ColourHelper.getColour(Minecraft.getMinecraft().getResourceManager().getResource(res).getInputStream());
                                MaterialRegistry.addMaterial(name, new Material(name, colour, Arrays.asList(new Resource[]{new Resource(String.format("ingot%s", name)), new Resource(String.format("dust%s", name)), new Resource(String.format("ore%s", name))})));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }


                }
            }
        }
    }

    public static void postInit() {

    }
}
