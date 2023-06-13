package com.kenzie.statics.fulfillment;

public class DimensionValueException extends Exception {

    private static final long serialVersionUID = -6605683679021606030L;

    /**Custom exception that tells if the dimension length is too great.
     *
     * @param message error message
     */
    public DimensionValueException(String message) {
        super(message);
    }
}
