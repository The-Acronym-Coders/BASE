package com.teamacronymcoders.base.guicomponentsystem;

import com.teamacronymcoders.base.guisystem.IHasGui;
import net.minecraft.util.math.BlockPos;

public interface IGuiComponentHost extends IHasGui {
    BlockPos getLocation();
}
