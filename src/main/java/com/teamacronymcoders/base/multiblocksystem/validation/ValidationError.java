package com.teamacronymcoders.base.multiblocksystem.validation;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ValidationError {

    public static final ValidationError VALIDATION_ERROR_TOO_FEW_PARTS =
            new ValidationError("base.api.multiblock.validation.too_few_parts");
    protected final String _resourceKey;
    protected final Object[] _parameters;

    public ValidationError(String messageFormatStringResourceKey, Object... messageParameters) {
        this._resourceKey = messageFormatStringResourceKey;
        this._parameters = messageParameters;
    }

    public ITextComponent getChatMessage() {
        return new TranslationTextComponent(this._resourceKey, _parameters);
    }
}
