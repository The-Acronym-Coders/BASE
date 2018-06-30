package com.teamacronymcoders.base.modules.journeymap;

import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import com.teamacronymcoders.base.modulesystem.dependencies.IDependency;
import com.teamacronymcoders.base.modulesystem.dependencies.ModDependency;

import journeymap.client.api.IClientAPI;
import journeymap.client.api.IClientPlugin;
import journeymap.client.api.event.ClientEvent;

import java.util.List;

import static com.teamacronymcoders.base.Reference.MODID;
import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

@Module(MODID)
@journeymap.client.api.ClientPlugin
public class JourneyMapModule extends ModuleBase implements IClientPlugin {
    @Override
    public String getName() {
        return "JourneyMap";
    }

    @Override
    public List<IDependency> getDependencies(List<IDependency> dependencies) {
        dependencies.add(new ModDependency("journeymap"));
        return super.getDependencies(dependencies);
    }
    
    @Override
    public void initialize(IClientAPI jAPI) {
        EVENT_BUS.register(new JourneyMapWaypointListener(jAPI));
    }

    @Override
    public String getModId() {
        return MODID;
    }

    @Override
    public void onEvent(ClientEvent event) {
    }
}
