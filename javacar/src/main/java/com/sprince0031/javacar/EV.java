package com.sprince0031.javacar;

public interface EV {
    public void chargeCar();
    public double calculateRange();
    public double getChargeLevel();
    public void calculateRemainingEnergy(double acceleratedDistance);
}