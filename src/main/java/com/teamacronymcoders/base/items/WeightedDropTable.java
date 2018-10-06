package com.teamacronymcoders.base.items;

import net.minecraft.item.ItemStack;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class WeightedDropTable implements IDropTable {
    private ItemStack[][] dropTable;
    private Boolean[] fortuneTable;
    private Random rng = new Random();
    
    public WeightedDropTable(List<List<ItemStack>> dropTable, List<Boolean> fortuneTable) {
        this.dropTable = new ItemStack[dropTable.size()][];
        for (int slot = 0; slot < dropTable.size(); slot++) {
            this.dropTable[slot] = dropTable.get(slot).toArray(new ItemStack[0]);
        }
        this.fortuneTable = fortuneTable.toArray(new Boolean[0]);
    }
    
    public List<ItemStack> getDrops(int fortune) {
        List<ItemStack> itemsToDrop = new LinkedList<>();
        for (int slot = 0; slot < dropTable.length; slot++) {
            int count;
            if (fortuneTable[slot]) {
                count = Math.max(rng.nextInt(fortune + 2), 1);
            } else {
                count = 1;
            }
            for (int i = 0; i < count; i++) {
                ItemStack drop = dropTable[slot][rng.nextInt(dropTable[slot].length)];
                itemsToDrop.add(drop.copy());
            }
        }
        return itemsToDrop;
    }
}
