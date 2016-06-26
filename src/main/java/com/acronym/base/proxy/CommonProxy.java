package com.acronym.base.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Jared on 6/27/2016.
 */
public abstract class CommonProxy {

    public void registerRenderers() {
        //NO-OP
    }


    public World getClientWorld() {
        return null;
    }

    public boolean isClient() {
        return false;
    }

    public boolean isServer() {
        return true;
    }

    public EntityPlayer getClientPlayer() {
        return null;
    }

}
