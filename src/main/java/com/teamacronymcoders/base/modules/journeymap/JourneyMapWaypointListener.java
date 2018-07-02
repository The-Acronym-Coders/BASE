package com.teamacronymcoders.base.modules.journeymap;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.event.PlaceWaypointEvent;
import journeymap.client.api.IClientAPI;
import journeymap.client.api.display.Waypoint;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.teamacronymcoders.base.Reference.MODID;

public class JourneyMapWaypointListener {
    private IClientAPI jAPI;
    
    public JourneyMapWaypointListener(IClientAPI jAPI) {
        this.jAPI = jAPI;
    }
    
    @SubscribeEvent
    public void onWaypointPlaced(PlaceWaypointEvent e) {
        try {
            jAPI.show(new Waypoint(MODID, e.getName() + e.getDim() + e.getPos().hashCode(), e.getName(), e.getDim(), e.getPos()).setColor(e.getColor()));
        } catch(Throwable t) {
            Base.instance.getLogger().warning(t.getLocalizedMessage());
        }
    }
}
