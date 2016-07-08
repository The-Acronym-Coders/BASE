package com.acronym.base.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.IResource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.util.Map;

import static net.minecraft.client.Minecraft.getMinecraft;

/**
 * Created by Jared on 6/29/2016.
 */
public class ResourceUtils {

    /**
     * Gets the IResource from an item
     *
     * @param item
     * @return IResource
     */
    public static IResource getResourceFromItem(ItemStack item) throws Exception {
        return Minecraft.getMinecraft().getResourceManager().getResource(getResourceLocationFromItem(item));
    }


    public static ResourceLocation getResourceLocationFromItem(ItemStack item) throws Exception {
        Map<String, TextureAtlasSprite> map = getMinecraft().getTextureMapBlocks().mapRegisteredSprites;
        for (Map.Entry<String, TextureAtlasSprite> entry : map.entrySet()) {
            if (entry.getValue().getIconName().split("/").length > 1 && entry.getValue().getIconName().split("/")[1].startsWith(item.getItem().getRegistryName().getResourcePath()))
                return new ResourceLocation(entry.getKey().split(":")[0], "textures/" + entry.getValue().getIconName().split(":")[1] + ".png");
        }
        return null;
    }
}
