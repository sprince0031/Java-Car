package com.sprince0031.javacar;

public class DistanceTracker implements Runnable {

    JavaCarMotion jcMotion = new JavaCarMotion();

    @Override
    public void run() {
        while (true) {
            jcMotion.distanceUpdate();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}