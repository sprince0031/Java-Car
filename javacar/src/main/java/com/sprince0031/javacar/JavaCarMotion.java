package com.sprince0031.javacar;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class JavaCarMotion implements GenericCarMotion {
    private static volatile int currentSpeed = 0;
    private static volatile double unacceleratedDistance = 0.0;
    private static volatile double acceleratedDistance = 0.0;
    private static volatile double totalDistance = 0.0;
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
        while (JavaCar.getCurrentState().equals("--")) {
            if (currentSpeed > 0) {
                currentSpeed = (int)(currentSpeed - ((naturalDecelerationRate * 125)/1000));
            } else {
                currentSpeed = 0;
            }
            JavaCar.setCurrentState("NIL");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    // public void totalDistanceUpdate() {
    //     // currentDistance += (currentSpeed * 0.0002777778); // that much km per second
    //     totalDistance = acceleratedDistance + unacceleratedDistance;
    // }

    public void distanceUpdate() {
        if (JavaCar.getCurrentState().equals("Accelerating")) {
            acceleratedDistance += (currentSpeed * 0.0002777778); // that much km per second
        } else {
            unacceleratedDistance += (currentSpeed * 0.0002777778);
        }
    }
    
    public static int getCurrentSpeed() {
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
        totalDistance = acceleratedDistance + unacceleratedDistance;
        return BigDecimal.valueOf(totalDistance).setScale(1, RoundingMode.DOWN).doubleValue();
        // return currentDistance;
    }

    public double getAcceleratedDistance() {
        return BigDecimal.valueOf(acceleratedDistance).setScale(1, RoundingMode.DOWN).doubleValue();
    }

}