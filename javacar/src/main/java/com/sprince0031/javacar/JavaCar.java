package com.sprince0031.javacar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JavaCar {

    void run() {
        JavaCarEVFunctions jcEVFunc = new JavaCarEVFunctions();
        // JavaCarMotion jcMotion = new JavaCarMotion();
        
        System.out.println("Current charge level: " + jcEVFunc.getChargeLevel() + " %");
        System.out.println("Estimated range: " + jcEVFunc.calculateRange() + " km");
        if (jcEVFunc.getChargeLevel() <= 20.0) {
            System.out.println("Battery level is low! Consider charging.");
        }
        Thread conDispThread = new Thread(new ConsoleDisplay());
        conDispThread.start();

    }

    public static void main(String[] args) throws IOException {

        String username, key;
        // int action;
        JavaCarFunctions jcFuntions = new JavaCarFunctions();
        do {
            System.out.println("Hello World! Please provide credentials to unlock JavaCar.");
            BufferedReader stringInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("username: ");
            username = stringInput.readLine();
            System.out.print("key: ");
            key = stringInput.readLine();
        } while (!(jcFuntions.unlockCar(username, key)));

        System.out.println("Welcome " + username + "! Press Return to start.");
        try {
            System.in.read();
            new JavaCar().run();
        } catch (Exception e) {
            // TODO: handle exception
        }
        

    }
}
