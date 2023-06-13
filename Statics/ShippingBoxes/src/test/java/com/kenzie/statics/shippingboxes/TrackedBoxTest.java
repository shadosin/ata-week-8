package com.kenzie.statics.shippingboxes;

import com.kenzie.test.infrastructure.reflect.ClassQuery;
import com.kenzie.test.infrastructure.reflect.ConstructorQuery;
import com.kenzie.test.infrastructure.reflect.MethodInvoker;
import com.kenzie.test.infrastructure.reflect.MethodQuery;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TrackedBoxTest {

    private static final String BASE_PACKAGE = "com.kenzie.statics.shippingboxes";

    @Test
    public void trackedBoxClass_containsCorrectConstructors() {
        // The Class Exists
        Class<?> trackedBoxClass = assertDoesNotThrow( () -> ClassQuery.inContainingPackage(BASE_PACKAGE)
                .withExactSimpleName("TrackedBox")
                .findClassOrFail(), "The TrackedBox class must exist before this test will pass.");

        // The constructor for the Subclass
        Constructor constructor = assertDoesNotThrow( () -> ConstructorQuery.inClass(trackedBoxClass)
                .withExactArgTypes(Arrays.asList(String.class))
                .findConstructor(), "The TrackedBox Constructor with a single boxId parameter was not found. Please double check your TrackedBox Constructor is configured correctly.");
    }


    @Test
    public void getBoxId_fromTrackedBox_returnsBoxId() {
        // The Class Exists
        Class<?> trackedBoxClass = assertDoesNotThrow( () -> ClassQuery.inContainingPackage(BASE_PACKAGE)
                .withExactSimpleName("TrackedBox")
                .findClassOrFail(), "The TrackedBox class must exist before this test will pass.");

        Constructor constructor = assertDoesNotThrow( () -> ConstructorQuery.inClass(trackedBoxClass)
                .withExactArgTypes(Arrays.asList(String.class))
                .findConstructor(), "The TrackedBox Constructor with a single boxId parameter was not found. Please double check your TrackedBox Constructor is configured correctly.");

        Method getBoxIdMethod = assertDoesNotThrow( () -> MethodQuery.inType(trackedBoxClass)
                .withExactName("getBoxId")
                .findMethodOrFail(), "The method getBoxId was not found in the TrackedBox Class");

        Object trackedBoxClassObject = null;

        try {
            trackedBoxClassObject = constructor.newInstance("A123");
        } catch (Exception e) {
            try {
                throw new InstantiationException("Cannot instantiate trackedBoxClassObject.");
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            }
        }

        Object trackedBoxId = MethodInvoker.invokeInstanceMethodWithReturnValue(trackedBoxClassObject, getBoxIdMethod);

        assertEquals(trackedBoxId.toString(), "A123");

    }

    @Test
    public void getTrackingId_fromTrackedBox_returnsBoxIdAndUUID() {
        // The Class Exists
        Class<?> trackedBoxClass = assertDoesNotThrow( () -> ClassQuery.inContainingPackage(BASE_PACKAGE)
                .withExactSimpleName("TrackedBox")
                .findClassOrFail(), "The TrackedBox class must exist before this test will pass.");

        Constructor constructor = assertDoesNotThrow( () -> ConstructorQuery.inClass(trackedBoxClass)
                .withExactArgTypes(Arrays.asList(String.class))
                .findConstructor(), "The TrackedBox Constructor with a single boxId parameter was not found. Please double check your TrackedBox Constructor is configured correctly.");

        Method getTrackingIdMethod = assertDoesNotThrow( () -> MethodQuery.inType(trackedBoxClass)
                .withExactName("getTrackingId")
                .findMethodOrFail(), "The method getTrackingId was not found in the TrackedBox Class");

        Method createTrackingIdMethod = assertDoesNotThrow( () -> MethodQuery.inType(trackedBoxClass)
                .withExactName("createTrackingId")
                .findMethodOrFail(), "The method createTrackingId was not found in the TrackedBox Class");


        Object trackedBoxClassObject = null;

        try {
            trackedBoxClassObject = constructor.newInstance("B123");
        } catch (Exception e) {
            try {
                throw new InstantiationException("Cannot instantiate trackedBoxClassObject.");
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            }
        }

        Object createTrackingId = MethodInvoker.invokeInstanceMethodWithReturnValue(trackedBoxClassObject, createTrackingIdMethod);

        Object trackingId = MethodInvoker.invokeInstanceMethodWithReturnValue(trackedBoxClassObject, getTrackingIdMethod);

        //THEN
        assertTrue(trackingId.toString().startsWith("B123"), "The trackingId should start with the boxId.");
        assertEquals(40, trackingId.toString().length(), "A trackingId should contain the boxId " +
                "and also a UUID.");
    }

    @Test
    public void getTrackingId_fromTrackedBox_returnsUniqueTrackingId() {
        // The Class Exists
        Class<?> trackedBoxClass = assertDoesNotThrow( () -> ClassQuery.inContainingPackage(BASE_PACKAGE)
                .withExactSimpleName("TrackedBox")
                .findClassOrFail(), "The TrackedBox class must exist before this test will pass.");

        Constructor constructor = assertDoesNotThrow( () -> ConstructorQuery.inClass(trackedBoxClass)
                .withExactArgTypes(Arrays.asList(String.class))
                .findConstructor(), "The TrackedBox Constructor with a single boxId parameter was not found. Please double check your TrackedBox Constructor is configured correctly.");

        Method getTrackingIdMethod = assertDoesNotThrow( () -> MethodQuery.inType(trackedBoxClass)
                .withExactName("getTrackingId")
                .findMethodOrFail(), "The method getTrackingId was not found in the TrackedBox Class");

        Method createTrackingIdMethod = assertDoesNotThrow( () -> MethodQuery.inType(trackedBoxClass)
                .withExactName("createTrackingId")
                .findMethodOrFail(), "The method createTrackingId was not found in the TrackedBox Class");

        Object trackedBoxClassObject1 = null;
        Object trackedBoxClassObject2 = null;

        try {
            trackedBoxClassObject1 = constructor.newInstance("B123");
            trackedBoxClassObject2 = constructor.newInstance("B123");
        } catch (Exception e) {
            try {
                throw new InstantiationException("Cannot instantiate trackedBoxClassObject.");
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            }
        }

        Object createTrackingId1 = MethodInvoker.invokeInstanceMethodWithReturnValue(trackedBoxClassObject1, createTrackingIdMethod);
        Object createTrackingId2 = MethodInvoker.invokeInstanceMethodWithReturnValue(trackedBoxClassObject2, createTrackingIdMethod);

        Object trackingId1 = MethodInvoker.invokeInstanceMethodWithReturnValue(trackedBoxClassObject1, getTrackingIdMethod);
        Object trackingId2 = MethodInvoker.invokeInstanceMethodWithReturnValue(trackedBoxClassObject2, getTrackingIdMethod);

        boolean doTrackingIdsMatch = false;

        if(trackingId1 == trackingId2) {
            doTrackingIdsMatch = true;
        }

        //THEN
        assertFalse(doTrackingIdsMatch,"The Tracking ID's should never be equal.");

    }

}
