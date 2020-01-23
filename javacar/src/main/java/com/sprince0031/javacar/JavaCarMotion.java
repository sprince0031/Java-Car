package com.sprince0031.javacar;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class JavaCarMotion implements GenericCarMotion {
    private static volatile int currentSpeed = 0;
    private static volatile double currentDistance = 0.0;
    private double accelerationRate = 9.25; // m/s^2
    private double naturalDecelerationRate = 6.25;
    private double brakingDecelerationRate = 12.25;
    private int topSpeed = 250; // 250 kmph
    private static boolean toggleAutopilot = false;

    public void accelerate() {
        // accelerate jc
        // currentState = "Accelerating";
        if (currentSpeed < topSpeed) {
            currentSpeed = (int) (currentSpeed + ((accelerationRate * 125) / 1000));
            // System.out.println("Speed: " + this.getCurrentSpeed() + "\tAccelerating!");
            
        } else {
            currentSpeed = topSpeed;
        }
    }
	public void decelerate() {
        // currentState = "--";
        if (currentSpeed > 0) {
            currentSpeed = (int)(currentSpeed - ((naturalDecelerationRate * 125)/1000));
        } else {
            currentSpeed = 0;
        }
    }

    public void brake() {
        // currentState = "Braking";
        if (currentSpeed > 0) {
            currentSpeed = (int)(currentSpeed - ((brakingDecelerationRate * 125)/1000));
            // System.out.println("Speed: " + this.getCurrentSpeed() + "\tBraking!");

        } else {
            currentSpeed = 0;
        }
    }

    public void distanceUpdate() {
        currentDistance += (currentSpeed * 0.0002777778);
    }
    
    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void toggleAutopilot() {
        if (toggleAutopilot) {
            toggleAutopilot = false;
        } else {
            toggleAutopilot = true;
        }
    }

    public String getAutopilotState() {
        if (toggleAutopilot) {
            return "ON";
        } else {
            return "OFF";
        }
    }

    public double getCurrentDistance() {
        return BigDecimal.valueOf(currentDistance).setScale(1, RoundingMode.DOWN).doubleValue();
        // return currentDistance;
    }

}