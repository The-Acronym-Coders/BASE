package com.teamacronymcoders.base.util;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.items.WeightedDropTable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DropUtils {
    private static final String FORTUNE_DELIM = "\\$";
    private static final String WEIGHT_DELIM = "%";
    private static final String DROP_DELIM = "#";
    
    private static Map<String, WeightedDropTable> wdMap = new HashMap<String, WeightedDropTable>();
    private static Pattern p = Pattern.compile("([^\\[\\]]+)");
    private static Matcher m = p.matcher("");
    
    private DropUtils() {
    }
    
    public static WeightedDropTable parseDrops(String drops) {
        if (wdMap.containsKey(drops)) {
            return wdMap.get(drops);
        }
        
        List<List<ItemStack>> dropTable = new ArrayList<List<ItemStack>>();
        List<Boolean> fortuneTable = new ArrayList<Boolean>();
        List<String> itemSlots = new ArrayList<String>();
        m.reset(drops);
        while (m.find()) {
            itemSlots.add(m.group());
        }
        if (itemSlots.size() == 0) {
            itemSlots.add(drops);
        }
        
        for (String itemSlot : itemSlots) {
            String[] fortuneArray = itemSlot.split(FORTUNE_DELIM);
            boolean fortune = fortuneArray.length > 1 ? Boolean.parseBoolean(fortuneArray[1]) : false;
            List<ItemStack> slotTable = new ArrayList<ItemStack>();
            
            for (String drop : fortuneArray[0].split(DROP_DELIM)) {
                String[] dropArray = drop.split(WEIGHT_DELIM);
                Base.instance.getLogger().info("itemSlot: "+itemSlot+" | drop: "+drop);
                int weight = dropArray.length > 1 ? Integer.parseInt(dropArray[1]) : 1;
                int count = dropArray.length > 2 ? Integer.parseInt(dropArray[2]) : 1;
                String[] itemArray = dropArray[0].split(":");
                String itemString = itemArray[0];
                ItemStack itemDrop = ItemStack.EMPTY;
                
                switch (itemString.toLowerCase()) {
                    case "oredict":
                        itemDrop = OreDictUtils.getPreferredItemStack(itemArray[1]);
                        break;
                    default:
                        int meta = 0;
                        if (itemArray.length > 1) {
                            itemString += ":" + itemArray[1]; 
                            if (itemArray.length > 2) {
                                meta = Integer.parseInt(itemArray[2]);
                            }
                        }
                        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemString));
                        if (item != null) {
                            itemDrop = new ItemStack(item, count, meta);
                        } else {
                            Base.instance.getLogger().error("Could not find Item for name: " + itemString);
                        }
                }
                
                for (int i = 0; i < weight; i++) {
                    slotTable.add(itemDrop);
                }
            }
            dropTable.add(slotTable);
            fortuneTable.add(fortune);
        }
        WeightedDropTable wd = new WeightedDropTable(dropTable, fortuneTable);
        wdMap.put(drops, wd);
        return wd;
    }
}
