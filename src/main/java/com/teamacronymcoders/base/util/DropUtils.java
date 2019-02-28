package com.teamacronymcoders.base.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.items.WeightedDropTable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DropUtils {
    private static final String FORTUNE_DELIM = "\\$";
    private static final String WEIGHT_DELIM = "%";
    private static final String DROP_DELIM = "#";
    
    private static LoadingCache<String, WeightedDropTable> wdMap = CacheBuilder.newBuilder()
            .build(new CacheLoader<String, WeightedDropTable>() {
                @Override
                public WeightedDropTable load(String key) throws Exception {
                    return parseDrops(key);
                }
            });
    private static Pattern bracketPattern = Pattern.compile("([^\\[\\]]+)");
    private static Matcher bracketMatcher = bracketPattern.matcher("");
    
    private DropUtils() {

    }

    public static WeightedDropTable getDrops(String drops) {
        return wdMap.getUnchecked(drops);
    }
    
    private static WeightedDropTable parseDrops(String drops) {
        List<List<ItemStack>> dropTable = new LinkedList<>();
        List<Boolean> fortuneTable = new LinkedList<>();
        List<String> itemSlots = new LinkedList<>();
        bracketMatcher.reset(drops);
        while (bracketMatcher.find()) {
            itemSlots.add(bracketMatcher.group());
        }
        if (itemSlots.size() == 0) {
            itemSlots.add(drops);
        }
        
        for (String itemSlot : itemSlots) {
            String[] fortuneArray = itemSlot.split(FORTUNE_DELIM);
            boolean fortune = fortuneArray.length > 1 && Boolean.parseBoolean(fortuneArray[1]);
            List<ItemStack> slotTable = new LinkedList<>();
            
            for (String drop : fortuneArray[0].split(DROP_DELIM)) {
                String[] dropArray = drop.split(WEIGHT_DELIM);
                int weight = dropArray.length > 1 ? Integer.parseInt(dropArray[1]) : 1;
                int count = dropArray.length > 2 ? Integer.parseInt(dropArray[2]) : 1;
                String[] itemArray = dropArray[0].split(":");
                String itemString = itemArray[0];
                ItemStack itemDrop = ItemStack.EMPTY;
                
                switch (itemString.toLowerCase()) {
                    case "empty":
                        break;
                    case "oredict":
                        //TODO MAKE TAGS NOT OREDICT
                        break;
                    default:
                        if (itemArray.length > 1) {
                            itemString += ":" + itemArray[1];
                        }
                        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemString));
                        if (item != null) {
                            itemDrop = new ItemStack(item, count);
                        } else {
                            Base.instance.getLogger().warn("Could not find Item for name: " + itemString);
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
