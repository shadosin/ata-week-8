package com.kenzie.statics.fulfillment;

public class Box {
    private static final double MAX = 72;
    private double height;
    private double width;
    private double length;

    /**
     * Constructs a Box object if the dimensions are valid
     * @param length - length of box
     * @param width - width of box
     * @param height - height of box
     * @throws DimensionValueException if the provided dimensions exceed the max Box size
     */
    public Box(double length, double width, double height) throws DimensionValueException {
        if(validateBoxDimensions(length, width, height)){
        this.length = length;
        this.width = width;
        this.height = height;
        } else {
            throw new DimensionValueException("Invalid Box dimensions");
        }
    }

    public double getHeight() {
            return height;

    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }
    public static boolean validateBoxDimensions(double height, double width, double length){
       return height <= MAX && width <=MAX && length <= MAX;
    }
}
