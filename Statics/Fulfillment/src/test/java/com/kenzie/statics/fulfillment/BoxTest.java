package com.kenzie.statics.fulfillment;

import com.kenzie.test.infrastructure.reflect.MethodQuery;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

public class BoxTest {

    @Test
    public void boxClass_inspectMethods_containsValidateBoxDimensions() {
        MethodQuery.inType(Box.class).withExactName("validateBoxDimensions").findMethodOrFail();
    }

    @Test
    public void boxClass_inspectMethods_validateBoxDimensionsStatic() {
        Method method = MethodQuery.inType(Box.class).withExactName("validateBoxDimensions").findMethodOrFail();
        if(!Modifier.isStatic(method.getModifiers())) {
            fail("Expected validateBoxDimensions to be static");
        }
    }

    @Test
    public void boxClass_inspectMethods_validateBoxDimensionsPublic() {
        Method method = MethodQuery.inType(Box.class).withExactName("validateBoxDimensions").findMethodOrFail();
        if(!Modifier.isPublic(method.getModifiers())) {
            fail("Expected validateBoxDimensions to be public");
        }
    }

    //Unit test methods
    @Test
    public void oversizedDimensions_createBox_throwsException() {
        //GIVEN
        int length = 75;
        int width = 15;
        int height = 20;

        // WHEN && THEN
        assertThrows(DimensionValueException.class, () -> new Box(length, width, height),
            "Expected DimensionValueException to be thrown when creating a Box with input: 75, 15, 20");
    }

    @Test
    public void allowedDimesions_createBox_noException() throws DimensionValueException {
        // GIVEN
        int length = 50;
        int width = 50;
        int height = 50;

        // WHEN
        new Box(length, width, height);

        // THEN - no exception thrown
    }

    @Test
    public void allowedDimesions_createBox_noException_inUpperLimit() throws DimensionValueException {
        // GIVEN
        int length = 72;
        int width = 72;
        int height = 72;

        // WHEN
        new Box(length, width, height);

        // THEN - no exception thrown
    }

}
