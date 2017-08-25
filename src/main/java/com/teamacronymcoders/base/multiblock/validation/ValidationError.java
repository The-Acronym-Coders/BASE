package com.teamacronymcoders.base.multiblock.validation;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class ValidationError {

    public static final ValidationError VALIDATION_ERROR_TOO_FEW_PARTS =
            new ValidationError("base.api.multiblock.validation.too_few_parts");

    public ValidationError(String messageFormatStringResourceKey, Object... messageParameters) {
        this._resourceKey = messageFormatStringResourceKey;
        this._parameters = messageParameters;
    }

    public ITextComponent getChatMessage() {
        return new TextComponentTranslation(this._resourceKey, _parameters);
    }

    protected final String _resourceKey;
    protected final Object[] _parameters;
}
