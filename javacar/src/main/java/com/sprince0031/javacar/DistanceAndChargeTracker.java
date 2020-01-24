package com.sprince0031.javacar;

public class DistanceAndChargeTracker implements Runnable {

    JavaCarMotion jcMotion = new JavaCarMotion();
    JavaCarEVFunctions jcEVFunc = new JavaCarEVFunctions();

    @Override
    public void run() {
        while (true) {
            jcMotion.distanceUpdate();
            if (JavaCar.getCurrentState().equals("Charging")) {
                jcEVFunc.chargeCar();
            }
 
            if (JavaCarMotion.getCurrentSpeed() == 0 && !(JavaCar.getCurrentState().equals("Charging"))) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                JavaCar.setCurrentState("Parking");
            }
            jcEVFunc.chargeLevelUpdate();
            jcEVFunc.calculateRemainingEnergy(jcMotion.getAcceleratedDistance());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}