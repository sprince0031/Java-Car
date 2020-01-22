package com.sprince0031.javacar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JavaCar {

    public static void main(String[] args) throws IOException {

        String username, key;
        JavaCarFunctions jcFuntions = new JavaCarFunctions();
        do {
            System.out.println( "Hello World! Please provide credentials to unlock JavaCar." );
            BufferedReader stringInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("username: ");
            username = stringInput.readLine();
            System.out.print("key: ");
            key = stringInput.readLine();     
        } while (!(jcFuntions.unlockCar(username, key)));

        System.out.println("Welcome " + username + "!");

    }
}
