package com.kenzie.statics.restaurant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class StringValidatorTest {

    @Test
    public void greaterThanMaxLength_longerThanMaxLength_throwsException() {
        //GIVEN
        StringBuilder inputBuilder = new StringBuilder();
        for(int i = 0; i < 251; i++) {
            inputBuilder.append("a");
        }

        //WHEN
        assertThrows(InvalidStringException.class, () -> StringValidator.greaterThanMaxLength(inputBuilder.toString()),
            "Expected an InvalidStringException to be thrown for a String longer than the max length.");
    }

    @Test
    public void greaterThanMaxLength_equalsToMaxLength_returns() throws InvalidStringException {
        //GIVEN
        StringBuilder inputBuilder = new StringBuilder();
        for(int i = 0; i < 250; i++) {
            inputBuilder.append("a");
        }

        //WHEN
        StringValidator.greaterThanMaxLength(inputBuilder.toString());

        // THEN - no exception thrown
    }

    @Test
    public void greaterThanMaxLength_shorterThanMaxLength_returns() throws InvalidStringException {
        //GIVEN
        String input = "fish";

        //WHEN

        StringValidator.greaterThanMaxLength(input);

        //THEN - no exception thrown
    }


    @Test
    public void containsForbiddenWord_isWord_throwsException() {
        //GIVEN
        String input = "fish";

        //WHEN && THEN
        assertThrows(InvalidStringException.class, () -> StringValidator.containsForbiddenWord(input),
            "An input value of 'fish' should cause a ForbiddenStringException.");
    }

    @Test
    public void containsForbiddenWord_containsWord_throwsException() {
        //GIVEN
        String input = "I love fish";

        //WHEN && THEN
        assertThrows(InvalidStringException.class, () -> StringValidator.containsForbiddenWord(input),
            "An input value containing 'fish' should cause a ForbiddenStringException.");
    }

    @Test
    public void containsForbiddenWord_doesNotContainWord_returns() throws InvalidStringException {
        //GIVEN
        String inputString = "burger";

        //WHEN
        StringValidator.containsForbiddenWord(inputString);

        //THEN - no exception thrown
    }
}
