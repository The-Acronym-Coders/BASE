package com.teamacronymcoders.base.materialsystem.parttype;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.materialsystem.items.ItemMaterialPart;
import com.teamacronymcoders.base.materialsystem.items.ItemSingularMaterialPart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.registrysystem.config.ConfigEntry;
import com.teamacronymcoders.base.registrysystem.config.ConfigRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Property.Type;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ItemPartType extends PartType {
    public static final String BURN_DATA_NAME = "burn";
    public static final String STACKSIZE_DATA_NAME = "stacksize";

    public final int itemSize;

    public Map<String, Map<Integer, ItemMaterialPart>> itemMaterialParts;

    public ItemPartType() {
        super("Item", setupItemData());
        ConfigRegistry configRegistry = Base.instance.getRegistry(ConfigRegistry.class, "CONFIG");
        configRegistry.addEntry("max_size", new ConfigEntry("item", "size", Type.INTEGER,
                "0", "0 - All Item Parts on a Single MC Item, 1 - All Item Parts have their own MC Item, " +
                "> 1 - That many Parts per item"), "material_system");
        int potentialItemSize = configRegistry.getInt("max_size", -1);
        if (potentialItemSize <= 0) {
            itemSize = 0;
        } else {
            itemSize = potentialItemSize;
        }
        itemMaterialParts = Maps.newHashMap();
    }

    private static List<PartDataPiece> setupItemData() {
        List<PartDataPiece> dataPieces = Lists.newArrayList();
        dataPieces.add(new PartDataPiece(BURN_DATA_NAME, false));
        dataPieces.add(new PartDataPiece(STACKSIZE_DATA_NAME, false));
        return dataPieces;
    }


    @Override
    public void setup(@Nonnull MaterialPart materialPart, @Nonnull MaterialUser materialUser) {
        if (!itemMaterialParts.containsKey(materialUser.getId())) {
            itemMaterialParts.put(materialUser.getId(), Maps.newHashMap());
        }

        if (itemSize == 0) {
            Map<Integer, ItemMaterialPart> materialPartMap = itemMaterialParts.get(materialUser.getId());
            if (!materialPartMap.containsKey(0)) {
                ItemMaterialPart itemMaterialPart = new ItemMaterialPart(materialUser.getId());
                materialPartMap.put(0, itemMaterialPart);
                materialUser.registerItem(itemMaterialPart);
            }

            materialPartMap.get(0).addMaterialPart(materialPart.getId(), materialPart);
        } else if (itemSize == 1) {
            materialUser.registerItem(new ItemSingularMaterialPart(materialPart));
        } else {
            int materialItemNumber = materialPart.getId() / itemSize;
            int materialMetaNumber = materialPart.getId() % itemSize;

            Map<Integer, ItemMaterialPart> materialPartMap = itemMaterialParts.get(materialUser.getId());
            if (!materialPartMap.containsKey(materialItemNumber)) {
                ItemMaterialPart itemMaterialPart = new ItemMaterialPart(materialUser.getId(), materialItemNumber);
                materialPartMap.put(materialItemNumber, itemMaterialPart);
                materialUser.registerItem(itemMaterialPart);
            }

            materialPartMap.get(materialItemNumber).addMaterialPart(materialMetaNumber, materialPart);
        }
    }

    @Override
    public ItemStack getItemStack(MaterialPart materialPart) {
        MaterialUser materialUser = materialPart.getMaterialUser();
        ItemStack itemStack;

        if (itemSize == 0) {
            itemStack = new ItemStack(this.itemMaterialParts.get(materialUser.getId()).get(0), 1,
                    materialUser.getMaterialPartId(materialPart));
        } else if (itemSize == 1) {
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(materialPart.getMaterialUser().getId(),
                    materialPart.getUnlocalizedName()));

            itemStack = Optional.ofNullable(item)
                    .map(ItemStack::new)
                    .orElse(ItemStack.EMPTY);
        } else {
            int materialItemId = materialPart.getId() / itemSize;
            int materialMetaId = materialPart.getId() % itemSize;

            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(materialPart.getMaterialUser().getId(),
                    "material_part_" + materialItemId));
            itemStack = Optional.ofNullable(item)
                    .map(itemPart -> new ItemStack(itemPart, 1, materialMetaId))
                    .orElse(ItemStack.EMPTY);
        }

        if (itemStack.isEmpty()) {
            materialPart.getMaterialUser().logError(String.format("Couldn't find Item for MaterialPart %s",
                    materialPart.getUnlocalizedName()));
        }
        return itemStack;
    }
}
