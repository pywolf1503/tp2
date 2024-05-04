package com.example.tp2.data;

import com.example.tp2.data.IUnit;

public enum MassUnit implements IUnit{
    KILOGRAM("Kilogram",1),
    GRAM("Gram", 0.001),
    MILLIGRAM("Milligram", 0.000001),
    MICROGRAM("Microgram", 0.000000001),
    METRIC_TON("Metric Ton", 1000.0),
    POUND("Pound", 0.453592),
    OUNCE("Ounce", 0.0283495),
    STONE("Stone", 6.35029),
    NEWTON("Newton", 0.101972);

    private final String name;
    private final double toKilogramsFactor;

    MassUnit(String name, double toKilogramsFactor) {
        this.name = name;
        this.toKilogramsFactor = toKilogramsFactor;
    }

    public String getName() {
        return name;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * toKilogramsFactor;
    }

    @Override
    public double convertFromBaseUnit(double valueInBaseUnit) {
        return valueInBaseUnit / toKilogramsFactor;
    }
}
