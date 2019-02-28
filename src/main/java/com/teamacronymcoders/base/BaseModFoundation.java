package com.teamacronymcoders.base;

import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.io.File;

public abstract class BaseModFoundation<T extends BaseModFoundation> implements IBaseMod<T> {
    private final ItemGroup itemGroup;
    private final Logger logger;
    private String modid;

    public BaseModFoundation(String modid, ItemGroup itemGroup) {
        this.modid = modid;
        this.itemGroup = itemGroup;
        this.logger = LogManager.getLogger(modid);
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

    @Nullable
    public File getResourceFolder() {
        //TODO FIX THIS SO MUCH
        return null;
    }
}
