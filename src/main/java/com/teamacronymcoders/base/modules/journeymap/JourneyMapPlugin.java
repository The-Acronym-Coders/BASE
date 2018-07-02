package com.teamacronymcoders.base.modules.journeymap;

import com.teamacronymcoders.base.Base;
import journeymap.client.api.ClientPlugin;
import journeymap.client.api.IClientAPI;
import journeymap.client.api.IClientPlugin;
import journeymap.client.api.event.ClientEvent;

import static com.teamacronymcoders.base.Reference.MODID;
import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

@ClientPlugin
public class JourneyMapPlugin implements IClientPlugin {

    @Override
    public void initialize(IClientAPI jAPI) {
        if (Base.instance.getModuleHandler().isModuleEnabled("JourneyMap")) {
            EVENT_BUS.register(new JourneyMapWaypointListener(jAPI));
        }
    }

    @Override
    public String getModId() {
        return MODID;
    }

    @Override
    public void onEvent(ClientEvent event) {
    }
}
