package com.example.tp2.data;

import com.example.tp2.data.IUnit;

public enum PowerUnit implements IUnit {
    WATT("Watt", 1.0),
    KILOWATT("Kilowatt", 1000.0),
    MEGAWATT("Megawatt", 1000000.0),
    HORSEPOWER("Horsepower", 745.7);

    private final String name;
    private final double toWattsFactor;

    PowerUnit(String name, double toWattsFactor) {
        this.name = name;
        this.toWattsFactor = toWattsFactor;
    }

    public String getName() {
        return name;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * toWattsFactor;
    }

    @Override
    public double convertFromBaseUnit(double valueInBaseUnit) {
        return valueInBaseUnit / toWattsFactor;
    }
}
