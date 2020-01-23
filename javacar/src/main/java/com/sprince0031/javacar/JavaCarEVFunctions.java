package com.sprince0031.javacar;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class JavaCarEVFunctions implements EV {
    private static double chargeLevel = 25.0;
    private double remainingEnergy;
    private double avgRangePerkWh = 6.25;
    private double maxEnergyCapacity = 95.0; // 95 kWh of battery capacity.
    // private double maxCharge = 95.0 / 400; // 400 volts
    private double maxRange = avgRangePerkWh * maxEnergyCapacity; // 500 km of max range for P100D
    private double availableRange;
    // private static boolean canRun = true;

    @Override
    public void chargeCar() {
        // charge jc
    }

    @Override
    public double getChargeLevel() {
        return BigDecimal.valueOf(chargeLevel).setScale(1, RoundingMode.DOWN).doubleValue();
    }

    public void chargeLevelUpdate(double currentDistance) {
        if (JavaCar.getCurrentState().equals("Accelerating")) {
            chargeLevel -= (currentDistance % maxRange) / avgRangePerkWh;
        }
    }

    public double calculateRemainingEnergy() {
        remainingEnergy = maxEnergyCapacity * (chargeLevel/100);
        return remainingEnergy;
    }

    @Override
    public double calculateRange() {
        calculateRemainingEnergy();
        availableRange = avgRangePerkWh * remainingEnergy;
        return BigDecimal.valueOf(availableRange).setScale(1, RoundingMode.DOWN).doubleValue();
    }
}