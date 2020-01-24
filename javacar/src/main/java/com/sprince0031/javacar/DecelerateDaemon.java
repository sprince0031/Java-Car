package com.sprince0031.javacar;

public class DecelerateDaemon implements Runnable {

    JavaCarMotion jcMotion = new JavaCarMotion();
    @Override
    public void run() {
        while (true) {
            
            if (JavaCar.getCurrentState().equals("--")) {
                jcMotion.decelerate();
            }
        }
    }
}