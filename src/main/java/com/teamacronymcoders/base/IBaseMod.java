package com.teamacronymcoders.base;

import net.minecraft.item.ItemGroup;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.io.File;

public interface IBaseMod<T> {
    T getInstance();

    String getID();

    @Nullable
    ItemGroup getItemGroup();

    Logger getLogger();

    @Nullable
    File getResourceFolder();
}
