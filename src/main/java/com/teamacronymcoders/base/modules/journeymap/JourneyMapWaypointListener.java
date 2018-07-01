package com.teamacronymcoders.base.modules.journeymap;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.event.PlaceWaypointEvent;
import journeymap.client.api.IClientAPI;
import journeymap.client.api.display.Waypoint;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class JourneyMapWaypointListener {
    private IClientAPI jAPI;
    
    public JourneyMapWaypointListener(IClientAPI jAPI) {
        this.jAPI = jAPI;
    }
    
    @SubscribeEvent
    public void onWaypointPlaced(PlaceWaypointEvent event) {
        try {
            jAPI.show(new Waypoint(event.getModId(), event.getId(), event.getName(), event.getDimension(), event.getPos()).setColor(new Random().nextInt(0xffffff)));
        } catch(Throwable t) {
            Base.instance.getLogger().warning(t.getLocalizedMessage());
        }
    }
}
