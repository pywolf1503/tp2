package com.example.tp2.data;

import com.example.tp2.data.IUnit;

public enum VolumeUnit implements IUnit {
    LITER("Liter", 1),
    MILLILITER("Milliliter", 0.001),
    CUBIC_METER("Cubic Meter", 1000),
    CUBIC_CENTIMETER("Cubic Centimeter", 0.001),
    GALLON("Gallon", 3.78541),
    QUART("Quart", 0.946353),
    PINT("Pint", 0.473176),
    FLUID_OUNCE("Fluid Ounce", 0.0295735);

    private final String name;
    private final double toLitersFactor;

    VolumeUnit(String name, double toLitersFactor) {
        this.name = name;
        this.toLitersFactor = toLitersFactor;
    }

    public String getName() {
        return name;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * toLitersFactor;
    }

    @Override
    public double convertFromBaseUnit(double valueInBaseUnit) {
        return valueInBaseUnit / toLitersFactor;
    }
}
