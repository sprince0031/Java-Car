package com.sprince0031.javacar;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class Controls implements Runnable, NativeKeyListener {

    JavaCarMotion jcMotion = new JavaCarMotion();
    JavaCarEVFunctions jcEVFunc = new JavaCarEVFunctions();
    // Thread decelerateDaemon = new Thread(new DecelerateDaemon());
    // private boolean shift = false, a = false;

    @Override
    public void nativeKeyPressed(NativeKeyEvent pressEvent) {

        if (pressEvent.getKeyCode() == NativeKeyEvent.VC_UP || pressEvent.getKeyCode() == NativeKeyEvent.VC_W) {
            // synchronized (decelerateDaemon) {
            //     try {
            //         decelerateDaemon.wait();
            //     } catch (InterruptedException e) {
            //         // TODO Auto-generated catch block
            //         e.printStackTrace();
            //     }
            // }
            if (jcEVFunc.getChargeLevel() > 0.0 ) {
                JavaCar.setCurrentState("Accelerating");
                jcMotion.accelerate();            
            } else {
                // TODO: Display charge warning message.
            }
        }

        if (pressEvent.getKeyCode() == NativeKeyEvent.VC_DOWN || pressEvent.getKeyCode() == NativeKeyEvent.VC_S) {
            JavaCar.setCurrentState("Braking");
            jcMotion.brake();
        }
        
        // if (pressEvent.getKeyCode() == NativeKeyEvent.VC_SHIFT) {
        //     shift = true;
        //     if (a) {
        //         jcMotion.toggleAutopilot();
        //     }
        // }

        if (pressEvent.getKeyCode() == NativeKeyEvent.VC_A) {
            // a = true;
            // if (shift) {
            jcMotion.toggleAutopilot();
            // }
        }

        if (pressEvent.getKeyCode() == NativeKeyEvent.VC_C) {
            if (JavaCarMotion.getCurrentSpeed() == 0) {
                if (JavaCar.getCurrentState().equals("Charging")) {
                    JavaCar.setCurrentState("Parking");
                } else {
                    JavaCar.setCurrentState("Charging");
                }
            } else {
                ConsoleDisplay.toggleChargeErrorFlag();
            }
        }

        if (pressEvent.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent releaseEvent) {
        if (releaseEvent.getKeyCode() == NativeKeyEvent.VC_UP) {
            JavaCar.setCurrentState("--");
            jcMotion.decelerate();          
        }

        // if (releaseEvent.getKeyCode() == NativeKeyEvent.VC_SHIFT) {
        //     shift = false;          
        // }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent typedEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void run() {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        logger.setUseParentHandlers(false); // TODO: Lookup what this does

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            System.err.println("There was a problem registering the native hook.");
			System.err.println(e.getMessage());
        }
        // decelerateDaemon.setDaemon(true);
        // decelerateDaemon.start();

        GlobalScreen.addNativeKeyListener(new Controls());
    }

} 