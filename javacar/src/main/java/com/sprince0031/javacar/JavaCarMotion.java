package com.sprince0031.javacar;

public class JavaCarMotion implements GenericCarMotion {
    private static volatile int currentSpeed = 0;
    private static volatile String currentState = "--";
    private double accelerationRate = 9.25; // m/s^2
    private double naturalDecelerationRate = 6.25;
    private double brakingDecelerationRate = 12.25;
    private int topSpeed = 250; // 250 kmph

    public void accelerate() {
        // accelerate jc
        currentState = "Accelerating";
        if (currentSpeed < topSpeed) {
            currentSpeed = (int) (currentSpeed + ((accelerationRate * 125) / 1000));
            // System.out.println("Speed: " + this.getCurrentSpeed() + "\tAccelerating!");
            
        } else {
            currentSpeed = topSpeed;
        }
        // System.out.println("Speed: " + currentSpeed);
        // try {
        //     Thread.sleep(125);
        // } catch (InterruptedException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        // System.out.println(new String(new char[50]).replace("\0", "\r\n"));
        // System.out.flush();

    }
	public void decelerate() {
        currentState = "--";
        if (currentSpeed > 0) {
            currentSpeed = (int)(currentSpeed - ((naturalDecelerationRate * 125)/1000));
        } else {
            currentSpeed = 0;
        }
    }

    public void brake() {
        currentState = "Braking";
        if (currentSpeed > 0) {
            currentSpeed = (int)(currentSpeed - ((brakingDecelerationRate * 125)/1000));
            // System.out.println("Speed: " + this.getCurrentSpeed() + "\tBraking!");

        } else {
            currentSpeed = 0;
        }
    }
    
    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public String getCurrentState() {
        return currentState;
    }


}