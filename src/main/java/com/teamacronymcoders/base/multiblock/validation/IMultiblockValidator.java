package com.teamacronymcoders.base.multiblock.validation;

public interface IMultiblockValidator {

    /**
     * @return the last validation error encountered when trying to assemble the multiblock, or null if there is no
     * error.
     */
    ValidationError getLastError();

    /**
     * Set a validation error
     *
     * @param error the error
     */
    void setLastError(ValidationError error);

    /**
     * Set a validation error
     *
     * @param messageFormatStringResourceKey a translation key for a message or a message format string
     * @param messageParameters              optional parameters for a message format string
     */
    void setLastError(String messageFormatStringResourceKey, Object... messageParameters);
}
