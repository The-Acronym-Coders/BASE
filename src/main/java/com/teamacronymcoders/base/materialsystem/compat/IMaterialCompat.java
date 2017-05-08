package com.teamacronymcoders.base.materialsystem.compat;

import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;

public interface IMaterialCompat {
    void doCompat(MaterialSystem materialSystem) throws MaterialException;
}
