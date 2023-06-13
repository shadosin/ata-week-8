package com.kenzie.groupactivity.amazonscout;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NavigationStepTest {
    @Test
    void reverseNavigation_left_returnsRight() {
        // GIVEN
        // WHEN
        NavigationStep result = NavigationStep.reverseNavigationStep(NavigationStep.TURN_LEFT);

        // THEN
        assertEquals(NavigationStep.TURN_RIGHT, result);
    }

    @Test
    void reverseNavigation_right_returnsLeft() {
        // GIVEN
        // WHEN
        NavigationStep result = NavigationStep.reverseNavigationStep(NavigationStep.TURN_RIGHT);

        // THEN
        assertEquals(NavigationStep.TURN_LEFT, result);
    }

    @Test
    void reverseNavigation_goStraight_returnsGoStraight() {
        // GIVEN
        // WHEN
        NavigationStep result = NavigationStep.reverseNavigationStep(NavigationStep.GO_STRAIGHT);

        // THEN
        assertEquals(NavigationStep.GO_STRAIGHT, result);
    }
}
