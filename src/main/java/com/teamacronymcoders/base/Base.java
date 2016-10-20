package com.teamacronymcoders.base;

import com.teamacronymcoders.base.api.materials.MaterialRegistry;
import com.teamacronymcoders.base.api.materials.MaterialType;
import com.teamacronymcoders.base.blocks.BaseBlocks;
import com.teamacronymcoders.base.compat.CompatHandler;
import com.teamacronymcoders.base.config.ConfigMaterials;
import com.teamacronymcoders.base.data.Materials;
import com.teamacronymcoders.base.data.Recipes;
import com.teamacronymcoders.base.items.BaseItems;
import com.teamacronymcoders.base.proxies.ModCommonProxy;
import com.teamacronymcoders.base.reference.TabBase;
import com.teamacronymcoders.base.util.LanguageHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
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
        getLogger().info("Starting PreInit");
        long time = System.currentTimeMillis();

        ConfigMaterials.init(this);

        Recipes.preInit();
        new CompatHandler();
        BaseItems.preInit();
        BaseBlocks.preInit();
        proxy.preInitBlocks();
        proxy.initEvents();
        Recipes.preInitLate();

        time = (System.currentTimeMillis() - time);
        totalTime += time;
        getLogger().info(String.format("Completed PreInit in: %d ms", time));
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        getLogger().info("Starting Init");
        long time = System.currentTimeMillis();
        proxy.initBlockRenders();
        proxy.initItemRenders();
        proxy.registerRenderers();
        Recipes.init();
        this.creativeTab = MaterialRegistry.getMaterials().isEmpty() ? CreativeTabs.MISC : new TabBase();
        Materials.WOOD.getTypes().add(MaterialType.EnumPartType.INGOT);
        time = (System.currentTimeMillis() - time);
        totalTime += time;
        getLogger().info(String.format("Completed Init in: %d ms", time));
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        getLogger().info("Starting PostInit");
        long time = System.currentTimeMillis();
        Recipes.postInit();
        time = (System.currentTimeMillis() - time);
        totalTime += time;
        getLogger().info(String.format("Completed PostInit in: %d ms", time));
    }

    @EventHandler
    public void loadComplete(FMLLoadCompleteEvent e) {
        getLogger().info("Starting LoadComplete");
        long time = System.currentTimeMillis();
        time = (System.currentTimeMillis() - time);
        totalTime += time;
        getLogger().info(String.format("Completed LoadComplete in: %d ms", time));
        getLogger().info(String.format("Loaded In: %d ms", totalTime));
    }

    @Override
    public Base getInstance() {
        return instance;
    }
}
