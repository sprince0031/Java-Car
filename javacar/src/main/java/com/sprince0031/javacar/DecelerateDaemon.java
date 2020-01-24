package com.sprince0031.javacar;

public class DecelerateDaemon implements Runnable {

    // static boolean isRun = true;
    JavaCarMotion jcMotion = new JavaCarMotion();
    private String currentState = JavaCar.getCurrentState();
    @Override
    public void run() {
        while (true) {
            currentState = JavaCar.getCurrentState();
            if (!(currentState.equals("Accelerating")) && !(currentState.equals("Charging")) && !(currentState.equals("Parking")) && !(currentState.equals("Braking"))) {
                JavaCar.setCurrentState("--");
                jcMotion.decelerate();
                try {
                    Thread.sleep(125);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}