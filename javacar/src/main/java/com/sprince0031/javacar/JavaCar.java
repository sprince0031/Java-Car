package com.sprince0031.javacar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JavaCar {

    private static volatile String currentState = "--";

    public static String getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(String newState) {
        currentState = newState;
    }

    void run() {
        
        Thread conDispThread = new Thread(new ConsoleDisplay());
        conDispThread.setDaemon(true);
        Thread distAndChargeTracker = new Thread(new DistanceAndChargeTracker());
        distAndChargeTracker.setDaemon(true);
        Thread decelerateDaemon = new Thread(new DecelerateDaemon());
        decelerateDaemon.setDaemon(true);
        Thread controlsThread = new Thread(new Controls());

        conDispThread.start();
        distAndChargeTracker.start();
        decelerateDaemon.start();
        controlsThread.start();

    }

    public static void main(String[] args) throws IOException {

        String username, key;

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
            e.printStackTrace();
        }
        

    }
}
