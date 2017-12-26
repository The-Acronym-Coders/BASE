package com.teamacronymcoders.base.materialsystem.parttype;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.materialsystem.entity.minecart.EntityMaterialMinecart;
import com.teamacronymcoders.base.materialsystem.items.minecart.ItemMaterialMinecart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.registrysystem.EntityRegistry;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

public class MinecartPartType extends PartType {
    private Map<String, ItemMaterialMinecart> minecartMap = Maps.newHashMap();
    private boolean registeredCart = false;

    public MinecartPartType() {
        super("minecart", createMinecartDataPieces());
    }

    private static List<PartDataPiece> createMinecartDataPieces() {
        List<PartDataPiece> partDataPieces = Lists.newArrayList();
        partDataPieces.add(new PartDataPiece("maxSpeed", false));
        partDataPieces.add(new PartDataPiece("drag", false));
        partDataPieces.add(new PartDataPiece("riddenDrag", false));
        return partDataPieces;
    }

    @Override
    public void setup(@Nonnull MaterialPart materialPart, @Nonnull MaterialUser materialUser) {
        if (!minecartMap.containsKey(materialUser.getId())) {
            ItemMaterialMinecart minecart = new ItemMaterialMinecart(materialUser);
            minecartMap.put(materialUser.getId(), minecart);
            materialUser.registerItem(minecart);

            if (!registeredCart) {
                Base.instance.getRegistry(EntityRegistry.class, "ENTITY").register(EntityMaterialMinecart.class);
                registeredCart = true;
            }
        }
        minecartMap.get(materialUser.getId()).addMaterialPart(materialPart);
    }

    @Override
    public ItemStack getItemStack(MaterialPart materialPart) {
        return new ItemStack(minecartMap.get(materialPart.getMaterialUser().getId()), 1, materialPart.getId());
    }
}
