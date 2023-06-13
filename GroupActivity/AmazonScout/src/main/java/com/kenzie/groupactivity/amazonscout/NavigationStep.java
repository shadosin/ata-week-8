package com.kenzie.groupactivity.amazonscout;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Represents one navigational move in a route that includes
 * straightaways and right angle turns.
 *
 * A given navigation can be reversed to get the corresponding
 * step for a return route.
 */
public enum NavigationStep {
    TURN_LEFT,
    TURN_RIGHT,
    GO_STRAIGHT;

    // map each navigation to its reverse when retracing steps
    private static final Map<NavigationStep, NavigationStep> NAVIGATION_TO_REVERSE_NAVIGATION =
        //              key          value
        ImmutableMap.of(TURN_LEFT,   TURN_RIGHT,
                        TURN_RIGHT,  TURN_LEFT,
                        GO_STRAIGHT, GO_STRAIGHT);

    /**
     * Returns the corresponding navigational step
     * for the given NavigationStep.
     *
     * @param navigationStep the NavigationStep to reverse
     * @return The reverse navigational step
     */
    public static NavigationStep reverseNavigationStep(NavigationStep navigationStep) {
        return NAVIGATION_TO_REVERSE_NAVIGATION.get(navigationStep);
    }

    /**
     * Provides the navigational steps to reverse direction.
     * @return a list of navigationStep to reverse direction.
     */
    public static List<NavigationStep> turnAround() {
        return ImmutableList.of(TURN_LEFT, TURN_LEFT);
    }

    /**
     * Returns a new list with the same elements as the provided list,
     * but in the reverse order. Given {TURN_LEFT, STRAIGHT} this would
     * return a new list {STRAIGHT, TURN_LEFT}.
     * @param forwardSteps The list in its regular forward order.
     * @return A copy of the list in the reverse order.
     */
    public static List<NavigationStep> reversedSteps(List<NavigationStep> forwardSteps) {
        List<NavigationStep> reversedSteps = new ArrayList<>(forwardSteps);
        Collections.reverse(reversedSteps);
        return reversedSteps;
    }
}
