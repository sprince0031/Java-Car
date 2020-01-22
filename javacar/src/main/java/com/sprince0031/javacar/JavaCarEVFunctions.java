package com.sprince0031.javacar;

public class JavaCarEVFunctions implements EV {
    private double chargeLevel = 100.0;
    private double remainingEnergy;
    private double avgRangePerkWh = 6.25;
    private double maxEnergyCapacity = 95.0; // 95 kWh of battery capacity.
    // private double maxCharge = 95.0 / 400; // 400 volts
    // private double maxRange = 500.0; // 500 km of max range for P100D
    private double availableRange;

    @Override
    public void chargeCar() {
        // charge jc
    }

    @Override
    public double getChargeLevel() {
        return chargeLevel;
    }

    double calculateRemainingEnergy() {
        return maxEnergyCapacity * (chargeLevel/100);
    }

    @Override
    public double calculateRange() {
        remainingEnergy = calculateRemainingEnergy();
        availableRange = avgRangePerkWh * remainingEnergy;
        return availableRange;
    }
}