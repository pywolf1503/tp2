package com.example.tp2.data;

import com.example.tp2.data.IUnit;

public enum TemperatureUnit implements IUnit {
    CELSIUS("Celsius", 1.0),
    FAHRENHEIT("Fahrenheit", 5.0/9.0),
    KELVIN("Kelvin", 1.0);

    private final String name;
    private final double toCelsiusFactor;

    TemperatureUnit(String name, double toCelsiusFactor) {
        this.name = name;
        this.toCelsiusFactor = toCelsiusFactor;
    }

    public String getName() {
        return name;
    }

    @Override
    public double convertToBaseUnit(double value) {
        // Convert the value to Celsius for consistency
        return (value - toCelsiusFactor) / toCelsiusFactor;
    }

    @Override
    public double convertFromBaseUnit(double valueInBaseUnit) {
        // Convert the value from Celsius back to the specific temperature unit
        return valueInBaseUnit * toCelsiusFactor + toCelsiusFactor;
    }
}
