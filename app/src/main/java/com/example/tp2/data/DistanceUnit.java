package com.example.tp2.data;

public enum DistanceUnit implements IUnit {
    METER("Meter", 1.0),
    KILOMETER("Kilometer", 1000.0),
    CENTIMETER("Centimeter", 0.01),
    MILLIMETER("Millimeter", 0.001),
    INCH("Inch", 0.0254),
    FOOT("Foot", 0.3048),
    YARD("Yard", 0.9144),
    MILE("Mile", 1609.34);

    private final String name;
    private final double toMetersFactor;

    DistanceUnit(String name, double toMetersFactor) {
        this.name = name;
        this.toMetersFactor = toMetersFactor;
    }

    public String getName() {
        return name;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * toMetersFactor;
    }

    @Override
    public double convertFromBaseUnit(double valueInBaseUnit) {
        return valueInBaseUnit / toMetersFactor;
    }
}
