package com.teamacronymcoders.base.items;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeightedDropTable implements IDropTable {
    private List<List<ItemStack>> dropTable = new ArrayList<List<ItemStack>>();
    private List<Boolean> fortuneTable = new ArrayList<Boolean>();
    private Random rng = new Random();
    
    public WeightedDropTable(List<List<ItemStack>> dropTable, List<Boolean> fortuneTable) {
        this.dropTable = dropTable;
        this.fortuneTable = fortuneTable;
    }
    
    public List<ItemStack> getDrops(int fortune) {
        List<ItemStack> itemsToDrop = new ArrayList<ItemStack>();
        for (int index = 0; index < dropTable.size(); index++) {
            List<ItemStack> itemSlot = dropTable.get(index);
            int count;
            if (fortuneTable.get(index)) {
                count = Math.max(rng.nextInt(fortune + 2), 1);
            } else {
                count = 1;
            }
            for (int i = 0; i < count; i++) {
                ItemStack drop = itemSlot.get(rng.nextInt(itemSlot.size()));
                if (drop != null) {
                    itemsToDrop.add(drop.copy());
                }
            }
        }
        return itemsToDrop;
    }
}
