package com.acronym.base.items;

import com.acronym.base.Base;
import com.acronym.base.api.materials.MaterialRegistry;
import com.acronym.base.api.materials.MaterialType;
import com.acronym.base.util.IMetaItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.util.List;
import java.util.stream.Collectors;

import static com.acronym.base.reference.Reference.tab;

/**
 * Created by Jared on 6/30/2016
 */
public class ItemPart extends Item implements IMetaItem {

    MaterialType.EnumPartType type;

    public ItemPart(MaterialType.EnumPartType type) {
        this.type = type;
        setHasSubtypes(true);
        setCreativeTab(tab);
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        subItems.addAll(MaterialRegistry.getMaterials().entrySet().stream().filter(ent -> ent.getValue().isTypeSet(this.type)).map(ent -> new ItemStack(itemIn, 1, ent.getKey())).collect(Collectors.toList()));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        MaterialType mat = MaterialRegistry.getFromID(stack.getItemDamage());
        if (mat != null && mat.isTypeSet(type))
            return String.format("item.base.%s.%s", this.type.name().toLowerCase(), mat.getName().toLowerCase());

        return "item.base.null_part";
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        MaterialType mat = MaterialRegistry.getFromID(stack.getItemDamage());
        if (mat != null && mat.isTypeSet(type))
            return String.format("%s %s", mat.getLocalizedName(), this.type.getLocalizedName());

        return TextFormatting.RED + Base.languageHelper.error("null_part");
    }

    @Override
    public List<Integer> getMetaData() {
        return MaterialRegistry.getIDList();
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        MaterialType mat = MaterialRegistry.getFromID(stack.getItemDamage());
        if (mat != null && mat.isTypeSet(type)) {
            return mat.isHasEffect();
        }
        return super.hasEffect(stack);
    }
}
