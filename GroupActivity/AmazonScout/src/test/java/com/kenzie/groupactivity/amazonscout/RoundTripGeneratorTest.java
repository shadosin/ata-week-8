package com.kenzie.groupactivity.amazonscout;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoundTripGeneratorTest {
    private RoundTripGenerator roundTripGenerator;

    @BeforeEach
    public void setup() {
        roundTripGenerator = new RoundTripGenerator();
    }

    @Test
    void generateRoundTrip_emptyNavigationList_turnsAround() {
        // GIVEN
        // WHEN
        List<NavigationStep> result = roundTripGenerator.generateRoundTrip(Collections.emptyList());

        // THEN
        // tolerate U-turn in either direction.
        boolean foundAcceptableResult = false;
        for (List<NavigationStep> validUTurn : validUTurns()) {
            if (validUTurn.equals(result)) {
                foundAcceptableResult = true;
            }
        }

        assertTrue(foundAcceptableResult, String.format("Didn't find expected result for empty list: %s. Expected " +
                "route to turn Scout around.", result.toString())
        );
    }

    @Test
    void generateRoundTrip_singleStraightNavigation_returnsRoundTrip() {
        // GIVEN
        // route with just one go straight
        List<NavigationStep> goStraight = ImmutableList.of(NavigationStep.GO_STRAIGHT);
        // acceptable round trips
        List<List<NavigationStep>> roundTrips = new ArrayList<>();
        roundTrips.add(ImmutableList.of(NavigationStep.GO_STRAIGHT,
                                        // U-Turn
                                        NavigationStep.TURN_RIGHT, NavigationStep.TURN_RIGHT,
                                        NavigationStep.GO_STRAIGHT)
        );
        roundTrips.add(ImmutableList.of(NavigationStep.GO_STRAIGHT,
                                        // U-Turn
                                        NavigationStep.TURN_LEFT, NavigationStep.TURN_LEFT,
                                        NavigationStep.GO_STRAIGHT)
        );

        // WHEN
        List<NavigationStep> result = roundTripGenerator.generateRoundTrip(goStraight);

        // THEN
        // result is one of the acceptable round trips
        boolean foundAcceptableResult = false;
        for (List<NavigationStep> roundTrip : roundTrips) {
            if (roundTrip.equals(result)) {
                foundAcceptableResult = true;
                break;
            }
        }

        assertTrue(
            foundAcceptableResult,
            String.format("Expected result of go straight to be one of %s, but was %s",
                          roundTrips.toString(),
                          result.toString())
        );
    }

    @Test
    void generateRoundTrip_singleLeftTurn_returnsRoundTrip() {
        // GIVEN
        // route with just one left turn
        List<NavigationStep> turnLeft = ImmutableList.of(NavigationStep.TURN_LEFT);
        // acceptable round trips
        List<List<NavigationStep>> roundTrips = new ArrayList<>();
        roundTrips.add(ImmutableList.of(NavigationStep.TURN_LEFT,
                                        // U-Turn
                                        NavigationStep.TURN_RIGHT, NavigationStep.TURN_RIGHT,
                                        NavigationStep.TURN_RIGHT)
        );
        roundTrips.add(ImmutableList.of(NavigationStep.TURN_LEFT,
                                        // U-Turn
                                        NavigationStep.TURN_LEFT, NavigationStep.TURN_LEFT,
                                        NavigationStep.TURN_RIGHT)
        );

        // WHEN
        List<NavigationStep> result = roundTripGenerator.generateRoundTrip(turnLeft);

        // THEN
        // result is one of the acceptable round trips
        boolean foundAcceptableResult = false;
        for (List<NavigationStep> roundTrip : roundTrips) {
            if (roundTrip.equals(result)) {
                foundAcceptableResult = true;
                break;
            }
        }

        assertTrue(
            foundAcceptableResult,
            String.format("Expected result of single left turn to be one of %s, but was %s",
                          roundTrips.toString(),
                          result.toString())
        );
    }

    @Test
    void generateRoundTrip_singleRightTurn_returnsRoundTrip() {
        // GIVEN
        // route with just one left turn
        List<NavigationStep> turnRight = ImmutableList.of(NavigationStep.TURN_RIGHT);
        // acceptable round trips
        List<List<NavigationStep>> roundTrips = new ArrayList<>();
        roundTrips.add(ImmutableList.of(NavigationStep.TURN_RIGHT,
                                        // U-Turn
                                        NavigationStep.TURN_RIGHT, NavigationStep.TURN_RIGHT,
                                        NavigationStep.TURN_LEFT)
        );
        roundTrips.add(ImmutableList.of(NavigationStep.TURN_RIGHT,
                                        // U-Turn
                                        NavigationStep.TURN_LEFT, NavigationStep.TURN_LEFT,
                                        NavigationStep.TURN_LEFT)
        );

        // WHEN
        List<NavigationStep> result = roundTripGenerator.generateRoundTrip(turnRight);

        // THEN
        // result is one of the acceptable round trips
        boolean foundAcceptableResult = false;
        for (List<NavigationStep> roundTrip : roundTrips) {
            if (roundTrip.equals(result)) {
                foundAcceptableResult = true;
                break;
            }
        }

        assertTrue(
            foundAcceptableResult,
            String.format("Expected result of single right turn to be one of %s, but was %s",
                          roundTrips.toString(),
                          result.toString())
        );
    }

    @Test
    void generateRoundTrip_severalNavigations_returnsRoundTrip() {
        // GIVEN
        // route
        List<NavigationStep> route = ImmutableList.of(NavigationStep.TURN_LEFT,
                                                  NavigationStep.GO_STRAIGHT,
                                                  NavigationStep.GO_STRAIGHT,
                                                  NavigationStep.TURN_LEFT,
                                                  NavigationStep.GO_STRAIGHT,
                                                  NavigationStep.TURN_RIGHT,
                                                  NavigationStep.TURN_RIGHT,
                                                  NavigationStep.GO_STRAIGHT);
        List<NavigationStep> reverseRoute = ImmutableList.of(NavigationStep.GO_STRAIGHT,
                                                         NavigationStep.TURN_LEFT,
                                                         NavigationStep.TURN_LEFT,
                                                         NavigationStep.GO_STRAIGHT,
                                                         NavigationStep.TURN_RIGHT,
                                                         NavigationStep.GO_STRAIGHT,
                                                         NavigationStep.GO_STRAIGHT,
                                                         NavigationStep.TURN_RIGHT);
        // acceptable round trips
        List<List<NavigationStep>> roundTrips = new ArrayList<>();
        for (List<NavigationStep> uTurn : validUTurns()) {
            List<NavigationStep> roundTrip = new ArrayList<>();
            roundTrip.addAll(route);
            roundTrip.addAll(uTurn);
            roundTrip.addAll(reverseRoute);
            roundTrips.add(roundTrip);
        }

        // WHEN
        List<NavigationStep> result = roundTripGenerator.generateRoundTrip(route);

        // THEN
        // result is one of the acceptable round trips
        boolean foundAcceptableResult = false;
        for (List<NavigationStep> roundTrip : roundTrips) {
            if (roundTrip.equals(result)) {
                foundAcceptableResult = true;
                break;
            }
        }

        assertTrue(
            foundAcceptableResult,
            String.format("Expected result of route [%s] to be one of [%s[, but was [%s]",
                          route,
                          roundTrips.toString(),
                          result.toString())
        );
    }

    private List<List<NavigationStep>> validUTurns() {
        return ImmutableList.of(
            ImmutableList.of(NavigationStep.TURN_LEFT, NavigationStep.TURN_LEFT),
            ImmutableList.of(NavigationStep.TURN_RIGHT, NavigationStep.TURN_RIGHT)
        );
    }
}
