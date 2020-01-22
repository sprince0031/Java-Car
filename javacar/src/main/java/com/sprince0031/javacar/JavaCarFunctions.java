package com.sprince0031.javacar;

public class JavaCarFunctions implements MiscellaneousCarFunctions {
    private boolean carState = false;
    private String ownerName = "Sprince0031";
    private String pass = "p@ssw0rD";

    // public JavaCarFunctions(String username, String key) {
        
    // }
    
    @Override
    public boolean unlockCar(String username, String key) {
        if (ownerName.equals(username)) {
            if (pass.equals(key)) {
                carState = true;
            } else {
                System.out.println("Incorrect key! Try again!");
            }
        } else {
            System.out.println("Wrong username! Try again.");
        }

        return carState;
    }

    @Override
    public boolean lockCar() {
        carState = false;
        return carState;
    }

}