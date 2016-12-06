package com.teamacronymcoders.base;

import com.teamacronymcoders.base.api.materials.MaterialRegistry;
import com.teamacronymcoders.base.api.materials.MaterialType;
import com.teamacronymcoders.base.blocks.BaseBlocks;
import com.teamacronymcoders.base.modules.materials.MaterialRecipes;
import com.teamacronymcoders.base.items.BaseItems;
import com.teamacronymcoders.base.modules.materials.Material;
import com.teamacronymcoders.base.proxies.ModCommonProxy;
import com.teamacronymcoders.base.reference.TabBase;
import com.teamacronymcoders.base.util.LanguageHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.teamacronymcoders.base.reference.Reference.*;

@Mod(modid = MODID, name = NAME, version = VERSION, acceptedMinecraftVersions = "[" + MINECRAFT_VERSION + "]", dependencies = "after:MineTweaker3;")
public class Base extends BaseModFoundation<Base> {
    public static final LanguageHelper languageHelper = new LanguageHelper(MODID);
    private static long totalTime = 0;

    @Instance(MODID)
    public static Base instance;

    @SidedProxy(clientSide = "com.teamacronymcoders.base.proxies.ModClientProxy", serverSide = "com.teamacronymcoders.base.proxies.ModCommonProxy")
    public static ModCommonProxy proxy;

    public Base() {
        super(MODID, NAME, VERSION, null);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        BaseItems.preInit();
        BaseBlocks.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        super.init(event);
        MaterialRecipes.init();
        this.creativeTab = MaterialRegistry.getMaterials().isEmpty() ? CreativeTabs.MISC : new TabBase();
        Material.WOOD.getMaterialType().getTypes().add(MaterialType.EnumPartType.INGOT);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public Base getInstance() {
        return instance;
    }
}
