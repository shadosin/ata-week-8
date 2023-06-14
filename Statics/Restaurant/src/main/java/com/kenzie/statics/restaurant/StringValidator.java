package com.kenzie.statics.restaurant;

public final class StringValidator {
    private static final String FORBIDDEN_WORD = "fish";
    private static final int MAX_LENGTH = 250;


    // Create a private constructor so StringValidator objects cannot be created.
    private StringValidator() {}

    /**
     * Checks to see if the string entered contains a forbidden string.
     *
     * @param input string that's being checked
     * @throws InvalidStringException if the input contains a forbidden string
     */
    public static void containsForbiddenWord(String input) throws InvalidStringException {
        if(input.contains(FORBIDDEN_WORD))
        throw new InvalidStringException("The given String contains a forbidden word.");
    }

    /**
     * Checks to see if the string entered has a length greater than the max allowed length.
     *
     * @param input string that's being checked
     * @throws InvalidStringException checks to see if the input is a forbidden string
     */
    public static void greaterThanMaxLength(String input) throws InvalidStringException {
        if(input.length() > MAX_LENGTH)
        throw new InvalidStringException("The given String has a length greater than the max length allowed.");
    }
}
