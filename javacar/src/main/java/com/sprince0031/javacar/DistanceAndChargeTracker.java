package com.sprince0031.javacar;

public class DistanceAndChargeTracker implements Runnable {

    JavaCarMotion jcMotion = new JavaCarMotion();
    JavaCarEVFunctions jcEVFunc = new JavaCarEVFunctions();

    @Override
    public void run() {
        while (true) {
            jcMotion.distanceUpdate();
            jcEVFunc.chargeLevelUpdate(jcMotion.getCurrentDistance());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}