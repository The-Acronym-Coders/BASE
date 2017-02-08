package com.teamacronymcoders.base.modules.minetweaker;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.api.materials.MaterialRegistry;
import com.teamacronymcoders.base.creativetabs.CreativeTabCarousel;
import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import com.teamacronymcoders.base.modulesystem.dependencies.IDependency;
import com.teamacronymcoders.base.modulesystem.dependencies.ModDependency;
import com.teamacronymcoders.base.Reference;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;
import java.util.List;

import static com.teamacronymcoders.base.modules.materials.ModuleMaterials.GEAR;

@Module(Reference.MODID)
public class MinetweakerModule extends ModuleBase {
    public static boolean tooLate = false;

    @Override
    public String getName() {
        return "MineTweaker3";
    }

    @Override
    public List<IDependency> getDependencies(List<IDependency> dependencyList) {
        dependencyList.add(new ModDependency("MineTweaker3"));
        return dependencyList;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        MineTweakerRegistration.init(this);
        if(MaterialRegistry.getMaterials().size() > 0) {
            CreativeTabCarousel tabCarousel = new CreativeTabCarousel("base");
            tabCarousel.setIconStacks(GEAR.getAllSubItems(new ArrayList<>()));
            Base.instance.setCreativeTab(tabCarousel);
        }
        tooLate = true;
    }
}
