package com.teamacronymcoders.base.proxy;

import com.teamacronymcoders.base.api.materials.MaterialRegistry;
import com.teamacronymcoders.base.api.materials.MaterialType;
import com.teamacronymcoders.base.blocks.BaseBlocks;
import com.teamacronymcoders.base.items.BaseItems;
import com.teamacronymcoders.base.util.IMetaItem;
import com.teamacronymcoders.base.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    public void registerRenderers() {
        super.registerRenderers();
    }

    public World getClientWorld() {
        return FMLClientHandler.instance().getClient().theWorld;
    }

    public boolean isClient() {
        return true;
    }

    public boolean isServer() {
        return false;
    }

    public EntityPlayer getClientPlayer() {
        return FMLClientHandler.instance().getClient().thePlayer;
    }


    public void preInitBlocks() {
        BaseBlocks.oreBlockMap.values().forEach(block -> {
            ModelLoader.setCustomStateMapper(block, new StateMapperBase() {
                @Override
                protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                    return new ModelResourceLocation(new ResourceLocation(Reference.MODID, "ore"), "normal");
                }
            });
        });
        BaseBlocks.storageBlockMap.values().forEach(block -> {
            ModelLoader.setCustomStateMapper(block, new StateMapperBase() {
                @Override
                protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                    return new ModelResourceLocation(new ResourceLocation(Reference.MODID, "storage"), "normal");
                }
            });
        });
    }

    @Override
    public void initBlockRenders() {
        super.initBlockRenders();
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        for (Map.Entry<String, Block> ent : BaseBlocks.renderMap.entries()) {
            renderItem.getItemModelMesher().register(Item.getItemFromBlock(ent.getValue()), 0, new ModelResourceLocation(
					Reference.MODID + ":" + ent.getKey(), "normal"));
        }

        BlockColors bc = Minecraft.getMinecraft().getBlockColors();
        ItemColors ic = Minecraft.getMinecraft().getItemColors();
        for (Map.Entry<MaterialType, Block> entry : BaseBlocks.oreBlockMap.entrySet()) {
            bc.registerBlockColorHandler(new IBlockColor() {
                @Override
                public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
                    return entry.getKey().getColour().getRGB();
                }
            }, entry.getValue());
            ic.registerItemColorHandler(new IItemColor() {
                @Override
                public int getColorFromItemstack(ItemStack stack, int tintIndex) {
                    return entry.getKey().getColour().getRGB();
                }

            }, entry.getValue());
        }

        for (Map.Entry<MaterialType, Block> entry : BaseBlocks.storageBlockMap.entrySet()) {
            bc.registerBlockColorHandler(new IBlockColor() {
                @Override
                public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
                    return entry.getKey().getColour().getRGB();
                }
            }, entry.getValue());
            ic.registerItemColorHandler(new IItemColor() {
                @Override
                public int getColorFromItemstack(ItemStack stack, int tintIndex) {
                    return entry.getKey().getColour().getRGB();
                }

            }, entry.getValue());
        }
    }

    @Override
    public void initItemRenders() {
        super.initItemRenders();

        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

        for (Map.Entry<String, Item> ent : BaseItems.renderMap.entrySet()) {
            if (ent.getValue() instanceof IMetaItem) {
                IMetaItem metaItem = (IMetaItem) ent.getValue();
                for (int i : metaItem.getMetaData()) {
                    renderItem.getItemModelMesher().register(ent.getValue(), i, new ModelResourceLocation(
							Reference.MODID + ":" + ent.getKey(), "inventory"));
                }
            } else
                renderItem.getItemModelMesher().register(ent.getValue(), 0, new ModelResourceLocation(
						Reference.MODID + ":" + ent.getKey(), "inventory"));
        }
        for (Map.Entry<Item, int[]> ent : BaseItems.colourMap.entrySet()) {
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
                @Override
                public int getColorFromItemstack(ItemStack stack, int tintIndex) {
                    MaterialType mat = MaterialRegistry.getFromID(stack.getItemDamage());
                    if (mat == null) {
                        return 0xFFFFFF;
                    }
                    for (int i : ent.getValue()) {
                        if (tintIndex == i) {
                            return mat.getColour().getRGB();
                        }
                    }
                    return 0xFFFFFF;
                }
            }, ent.getKey());
        }
    }

    /**
     * Translates a message
     *
     * @param label   prefix
     * @param message message
     * @return Translated String
     */
    public String translateMessage(String label, String message) {
        if (Objects.equals(label, "")) return I18n.format(message);
        return I18n.format(String.format("%s.%s.%s", label, Reference.MODID, message));
    }

    @Override
    public void initEvents() {
        super.initEvents();
    }


}
