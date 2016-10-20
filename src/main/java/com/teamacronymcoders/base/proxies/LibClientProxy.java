package com.teamacronymcoders.base.proxies;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class LibClientProxy extends LibCommonProxy {
    @Override
    public void addOBJDomain() {
        OBJLoader.INSTANCE.addDomain(getMod().getID());
    }

    @Override
    public void setItemModel(Item item, int metadata, ResourceLocation resourceLocation) {
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(resourceLocation, ""));
    }

    @Override
    public void setAllItemModels(Item item, List<ResourceLocation> resourceLocations) {
        List<ItemStack> allSubItems = new ArrayList<>();
        item.getSubItems(item, item.getCreativeTab(), allSubItems);
        int locationsIndex = 0;
        for (int i = 0; i < allSubItems.size(); i++) {
            setItemModel(item, i, resourceLocations.get(i));
            locationsIndex++;
            if (locationsIndex >= resourceLocations.size()) {
                locationsIndex = 0;
            }
        }
    }

}
