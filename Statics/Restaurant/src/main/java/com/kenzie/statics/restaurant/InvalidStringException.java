package com.kenzie.statics.restaurant;

public class InvalidStringException extends Exception {

    private static final long serialVersionUID = -2649780919623011083L;

    /** Custom exception for the ForbiddenString.
     *
     * @param message error message
     */
    public InvalidStringException(String message) {
        super(message);
    }
}
