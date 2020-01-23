package com.sprince0031.javacar;

public class ConsoleDisplay implements Runnable {

    JavaCarEVFunctions jcEVFunc = new JavaCarEVFunctions();
    JavaCarMotion jcMotion = new JavaCarMotion();

    @Override
    public void run() {
        while (true) {
            if (jcEVFunc.getChargeLevel() <= 20.0) {
                System.out.println("\t\t\t\t\t\t\t\tBattery level is low! Consider charging.");
            }
            System.out.println("Speed: " + jcMotion.getCurrentSpeed() + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   State: " + jcMotion.getCurrentState() + "\n");
            System.out.println("Autopilot: " + jcMotion.getAutopilotState() + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t           Distance travelled: " + jcMotion.getCurrentDistance() + " km" + "\n");
            System.out.println("Charge level: " + jcEVFunc.getChargeLevel() + " %" + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   Estimated range: " + jcEVFunc.calculateRange() + " km");
            try {
                Thread.sleep(125);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(new String(new char[50]).replace("\0", "\r\n"));
            System.out.flush();
        }
    }

}