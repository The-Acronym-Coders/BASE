package com.teamacronymcoders.base.proxies;

import com.google.common.base.Strings;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.blocks.IHasBlockColor;
import com.teamacronymcoders.base.blocks.IHasBlockStateMapper;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.items.IHasItemColor;
import com.teamacronymcoders.base.modulesystem.IModule;
import com.teamacronymcoders.base.modulesystem.ModuleHandler;
import com.teamacronymcoders.base.modulesystem.proxies.IModuleProxy;
import com.teamacronymcoders.base.registrysystem.pieces.RegistrySide;
import com.teamacronymcoders.base.util.ClassLoading;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.discovery.ASMDataTable;

import javax.annotation.Nullable;

public abstract class LibCommonProxy {
    private IBaseMod mod;

    public void addOBJDomain() {
        // Only add Client RegistrySide
    }

    public void setAllItemModels(IHasModel model) {
        // Only set Client RegistrySide
    }

    public void registerFluidModel(Block fluidBlock, final ResourceLocation loc) {
        // Only done Client RegistrySide
    }

    public abstract IModuleProxy getModuleProxy(IModule module);

    @Nullable
    protected IModuleProxy getModuleProxy(String path) {
        IModuleProxy moduleProxy = null;

        if (!Strings.isNullOrEmpty(path.trim())){
            moduleProxy = ClassLoading.createInstanceOf(IModuleProxy.class, path);
        }

        return moduleProxy;
    }

    public RegistrySide getRegistrySide() {
        return RegistrySide.BOTH;
    }

    public boolean isRightSide(RegistrySide side) {
        // Check both and getRegistrySide since it can be overrode to not be both
        return side == RegistrySide.BOTH || side == this.getRegistrySide();
    }

    public IBaseMod getMod() {
        return this.mod;
    }

    public void setMod(IBaseMod mod) {
        this.mod = mod;
    }

    public void registerItemColor(Item item, IHasItemColor itemColor) {
        // Only done Client RegistrySide
    }

    public void registerItemColor(Block block, IHasItemColor itemColor) {
        // Only done Client RegistrySide
    }

    public void registerBlockColor(IHasBlockColor blockColor) {
        // Only done Client RegistrySide
    }

    public void registerBlockStateMapper(IHasBlockStateMapper stateMapper) {
        // Only done Client RegistrySide
    }

    public void registerModelVariant(Item item, ResourceLocation resourceLocation) {
        // Only done Client RegistrySide
    }

    public String getFileContents(ResourceLocation resourceLocation) {
        return "";
    }

    public void createResourceLoader(String modid) {

    }

    public void loadEntityRenderers(ASMDataTable table, ModuleHandler moduleHandler) {
        // Only done Client Side
    }
}
