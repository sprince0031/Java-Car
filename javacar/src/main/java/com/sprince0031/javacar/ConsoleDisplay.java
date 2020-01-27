package com.sprince0031.javacar;

public class ConsoleDisplay implements Runnable {

    JavaCarEVFunctions jcEVFunc = new JavaCarEVFunctions();
    JavaCarMotion jcMotion = new JavaCarMotion();
    private static boolean chargingErrorFlag = false;
    private int messageDisplayCounter = 0;
    private int chargeBarAnimationCounter = 1, i;

    public static void toggleChargeErrorFlag() {
        if (chargingErrorFlag) {
            chargingErrorFlag = false;
        } else {
            chargingErrorFlag = true;
        }
    }

    @Override
    public void run() {
        while (true) {
            if (JavaCarMotion.getCurrentSpeed() >= 200) {
                System.out.println("\t\t\t\t\t\t\t========== Going too fast! Please slow down! ==========");
            }
            if (jcEVFunc.getChargeLevel() <= 20.0 && jcEVFunc.getChargeLevel() > 10.0 && !(JavaCar.getCurrentState().equals("Charging"))) {
                System.out.println("\t\t\t\t\t\tBattery level is low! Consider charging by pressing 'C' when the car is parked.");
            } else if (jcEVFunc.getChargeLevel() <= 10.0 && jcEVFunc.getChargeLevel() > 0.0 && !(JavaCar.getCurrentState().equals("Charging"))) {
                System.out.println("\t\t\t\t\t\tBattery level critical! Please charge ASAP by pressing 'C' when the car is parked.");
            } else if (jcEVFunc.getChargeLevel() == 0.0 && !(JavaCar.getCurrentState().equals("Charging"))) {
                System.out.println("\t\t\t\t\t\t\t\t\tBattery empty! Please charge.");
            }
            if (chargingErrorFlag) {
                System.out.println("\t\t\t\t\t\t\t\tSorry! Can't charge while car in motion.");
                messageDisplayCounter++;
                if (messageDisplayCounter > 20) {
                    chargingErrorFlag = false;
                    messageDisplayCounter = 0;
                }
            }

            System.out.println("Speed: " + JavaCarMotion.getCurrentSpeed() + " kmph" + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   State: " + JavaCar.getCurrentState() + "\n");
            System.out.println("Autopilot: " + jcMotion.getAutopilotState() + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t           Distance travelled: " + jcMotion.getCurrentDistance() + " km" + "\n");
            System.out.println("Charge level: " + jcEVFunc.getChargeLevel() + " %" + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   Estimated range: " + jcEVFunc.calculateRange() + " km");

            if (JavaCar.getCurrentState().equals("Charging")) {

                if (chargeBarAnimationCounter < 12) {
                    for (i = (11-chargeBarAnimationCounter); i > 0; i--) {
                        System.out.print("\t");
                    }

                    for (i = 1; i < chargeBarAnimationCounter; i++) {
                        System.out.print("    ");
                    }
                    for (i = 0; i < chargeBarAnimationCounter; i++) {
                        System.out.print("\\\\\\\\");
                    }
                    for (i = 0; i < chargeBarAnimationCounter; i++) {
                        System.out.print("////");
                    }
                    chargeBarAnimationCounter++;
                } else {
                    chargeBarAnimationCounter = 1;
                }
             }

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