package com.teamacronymcoders.base.materialsystem.entity.minecart;

import com.teamacronymcoders.base.entities.EntityMinecartBase;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.items.minecart.ItemMaterialMinecart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntityMaterialMinecart extends EntityMinecartBase {
    private MaterialPart materialPart;

    private static final DataParameter<String> MATERIAL_PART_STRING =  EntityDataManager.createKey(EntityMaterialMinecart.class, DataSerializers.STRING);

    public EntityMaterialMinecart(World world) {
        super(world);
    }

    public EntityMaterialMinecart(World world, MaterialPart materialPart) {
        this(world);
        this.materialPart = materialPart;
        this.dataManager.set(MATERIAL_PART_STRING, materialPart.getUnlocalizedName());
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(MATERIAL_PART_STRING, "");
    }

    @Override
    public ItemStack getCartItem() {
        return this.getMaterialPart().getItemStack();
    }

    @Override
    public boolean canBeRidden() {
        return true;
    }

    @Override
    public ItemMinecart getItem() {
        return (ItemMaterialMinecart) this.getMaterialPart().getItemStack().getItem();
    }

    public MaterialPart getMaterialPart() {
        if (materialPart == null) {
            materialPart = MaterialSystem.getMaterialPart(this.dataManager.get(MATERIAL_PART_STRING));
        }
        return this.materialPart;
    }

    @Override
    public String getName() {
        String name;
        if (this.hasCustomName()) {
            name = this.getCustomNameTag();
        } else {
            name = this.getMaterialPart().getLocalizedName();
        }
        return name;
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbtTagCompound) {
        this.dataManager.set(MATERIAL_PART_STRING, nbtTagCompound.getString("material_part"));
    }


    @Override
    public void writeEntityToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setString("material_part", this.getMaterialPart().getUnlocalizedName());
    }
}
