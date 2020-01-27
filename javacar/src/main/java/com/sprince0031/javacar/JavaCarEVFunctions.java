package com.sprince0031.javacar;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class JavaCarEVFunctions implements EV {
    private static double chargeLevel;
    private static double remainingEnergy = 0.0;
    private double avgRangePerkWh = 6.25;
    private double maxEnergyCapacity = 95.0; // 95 kWh of battery capacity.
    // private double maxCharge = 95.0 / 400; // 400 volts
    private double maxRange = avgRangePerkWh * maxEnergyCapacity; // 500+ km of max range for P100D
    private static double availableRange;
    // private static boolean canRun = true;

    @Override
    public void chargeCar() {

        if (remainingEnergy < maxEnergyCapacity) {
            remainingEnergy += 0.1;
        } else {
            JavaCar.setCurrentState("Parking");
        }
    }

    @Override
    public double getChargeLevel() {
        return BigDecimal.valueOf(chargeLevel).setScale(1, RoundingMode.DOWN).doubleValue();
    }

    public void chargeLevelUpdate() {
        chargeLevel = (remainingEnergy/maxEnergyCapacity) * 100;
    }

    public void calculateRemainingEnergy(double acceleratedDistance) {
        if (JavaCar.getCurrentState().equals("Accelerating") || JavaCar.getCurrentState().equals("Autopilot")) {
            remainingEnergy -= (acceleratedDistance % maxRange) / (avgRangePerkWh * 36);    
            chargeLevelUpdate();
        }

    }

    @Override
    public double calculateRange() {

        availableRange = avgRangePerkWh * remainingEnergy;
        return BigDecimal.valueOf(availableRange).setScale(1, RoundingMode.DOWN).doubleValue();
    }
}