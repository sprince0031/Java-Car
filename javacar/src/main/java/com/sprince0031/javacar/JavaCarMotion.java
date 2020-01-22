package com.sprince0031.javacar;

public class JavaCarMotion implements GenericCarMotion {
    private int currentSpeed = 0;
    private double accelerationRate = 9.25; // m/s^2
    private int topSpeed = 250; // 250 kmph 
    public void accelerate() {
        // accelerate jc     
       
        if (currentSpeed < topSpeed) {
            currentSpeed = (int)(currentSpeed + ((accelerationRate * 125)/1000));
        } else {
            currentSpeed = topSpeed;
        }
        System.out.println("Speed: " + currentSpeed);
        try {
            Thread.sleep(125);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(new String(new char[50]).replace("\0", "\r\n"));
        System.out.flush();

    }
	public void decelerate() {
	}


}