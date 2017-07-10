package com.teamacronymcoders.base.materialsystem.parts;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.materialsystem.parttype.PartType;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.List;

public class GatherPartsEvent extends Event {
    private List<Part> partList;
    private List<PartType> partTypeList;

    public GatherPartsEvent() {
        partList = Lists.newArrayList();
        partTypeList = Lists.newArrayList();
    }

    public void addPart(Part part) {
        partList.add(part);
    }

    public void addPartType(PartType partType) {
        partTypeList.add(partType);
    }

    public List<Part> getPartList() {
        return this.partList;
    }

    public List<PartType> getPartTypeList() {
        return partTypeList;
    }
}
