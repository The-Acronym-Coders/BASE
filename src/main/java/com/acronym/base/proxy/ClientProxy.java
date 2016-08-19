package com.acronym.base.proxy;

import com.acronym.base.blocks.BaseBlocks;
import com.acronym.base.blocks.BlockTest;
import com.acronym.base.client.render.RenderTileEntityTest;
import com.acronym.base.items.BaseItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

    public void registerRenderers() {
        super.registerRenderers();
        ClientRegistry.registerTileEntity(BlockTest.TileEntityTest.class, "testRender", new RenderTileEntityTest());
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

    @Override
    public void initBlockRenders() {
        super.initBlockRenders();
        BaseBlocks.init();
    }

    @Override
    public void initItemRenders() {
        super.initItemRenders();
        BaseItems.init();
    }
}
