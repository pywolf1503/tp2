package com.example.tp2.data;

import com.example.tp2.data.IUnit;

public enum EnergyUnit implements IUnit {
    JOULE("Joule", 1.0),
    KILOJOULE("Kilojoule", 1000.0),
    MEGAJOULE("Megajoule", 1000000.0),
    CALORIE("Calorie", 4.184),
    KILOCALORIE("Kilocalorie", 4184);

    private final String name;
    private final double toJoulesFactor;

    EnergyUnit(String name, double toJoulesFactor) {
        this.name = name;
        this.toJoulesFactor = toJoulesFactor;
    }

    public String getName() {
        return name;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * toJoulesFactor;
    }

    @Override
    public double convertFromBaseUnit(double valueInBaseUnit) {
        return valueInBaseUnit / toJoulesFactor;
    }
}
