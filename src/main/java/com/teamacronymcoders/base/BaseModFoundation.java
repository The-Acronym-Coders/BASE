package com.teamacronymcoders.base;

import com.teamacronymcoders.base.proxies.LibClientProxy;
import com.teamacronymcoders.base.proxies.LibCommonProxy;
import com.teamacronymcoders.base.proxies.LibServerProxy;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.io.File;

public abstract class BaseModFoundation<T extends BaseModFoundation> implements IBaseMod<T> {
    private final ItemGroup itemGroup;
    private final Logger logger;
    private final LibCommonProxy libProxy;
    private String modid;

    public BaseModFoundation(String modid, ItemGroup itemGroup) {
        this.modid = modid;
        this.itemGroup = itemGroup;
        this.logger = LogManager.getLogger(modid);

        this.libProxy = DistExecutor.runForDist(() -> LibClientProxy::new, () -> LibServerProxy::new);
        this.getLibProxy().setMod(this);
        BaseMods.addBaseMod(this);
    }

    public void setup(FMLCommonSetupEvent event) {
        //TODO FIX Module Handler/Registries
    }


    @Override
    public ItemGroup getItemGroup() {
        return this.itemGroup;
    }

    @Override
    public String getID() {
        return this.modid;
    }

    @Override
    public Logger getLogger() {
        return this.logger;
    }

    @Override
    public LibCommonProxy getLibProxy() {
        return this.libProxy;
    }

    @Nullable
    public File getResourceFolder() {
        //TODO FIX THIS SO MUCH
        return null;
    }
}
