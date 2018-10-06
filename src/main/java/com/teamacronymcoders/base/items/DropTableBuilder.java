package com.teamacronymcoders.base.items;

import java.util.LinkedList;
import java.util.List;

public class DropTableBuilder {
    private final List<String> items = new LinkedList<>();
    private boolean fortuneEnabled = false;
    private String oldSlots;
    
    public DropTableBuilder() {
        this.oldSlots = "";
    }
    
    public DropTableBuilder(String oldSlots) {
        this.oldSlots = oldSlots;
    }
    
    public DropTableBuilder newSlot() {
        oldSlots = toString();
        items.clear();
        fortuneEnabled = false;
        return this;
    }
    
    public DropTableBuilder addItem(String item) {
        items.add(item);
        return this;
    }
    
    public DropTableBuilder addItem(String item, int weight) {
        items.add(item + "%" + weight);
        return this;
    }
    
    public DropTableBuilder addItem(String item, int weight, int count) {
        items.add(item + "%" + weight + "%" + count);
        return this;
    }
    
    public DropTableBuilder enableFortune() {
        fortuneEnabled = true;
        return this;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder(oldSlots);
        sb.append("[").append(items.get(0));

        for (int i = 1; i < items.size(); i++) {
            sb.append("#").append(items.get(i));
        }
        if (fortuneEnabled) {
            sb.append("$true");
        }
        return sb.append("]").toString();
    }
}
