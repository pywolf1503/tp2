package com.example.tp2.data;

public class UnitConverter {
    public static double convert(double value, IUnit fromUnit, IUnit toUnit) {
        if (fromUnit.getClass() != toUnit.getClass()) {
            throw new IllegalArgumentException("Units belong to different categories, conversion not supported");
        }
        double valueInBaseUnit = fromUnit.convertToBaseUnit(value);
        return toUnit.convertFromBaseUnit(valueInBaseUnit);
    }
}
