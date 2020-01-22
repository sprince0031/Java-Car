package com.sprince0031.javacar;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class ConsoleDisplay implements NativeKeyListener, Runnable {

    JavaCarMotion jcMotion = new JavaCarMotion();
    Thread decelerateDaemon = new Thread(new DecelerateDaemon());
    public final static Object obj = new Object();

    @Override
    public void nativeKeyPressed(NativeKeyEvent pressEvent) {

        if (pressEvent.getKeyCode() == NativeKeyEvent.VC_UP || pressEvent.getKeyCode() == NativeKeyEvent.VC_W) {
            // synchronized (decelerateDaemon){
                // try {
                //     decelerateDaemon.wait();
                //     // jcMotion.accelerate();
                // } catch (InterruptedException e) {
                //     // TODO Auto-generated catch block
                //     e.printStackTrace();
                // }
            // }
            // DecelerateDaemon.isRun = false;
            jcMotion.accelerate();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(new String(new char[50]).replace("\0", "\r\n"));
            
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
        if (releaseEvent.getKeyCode() == NativeKeyEvent.VC_DOWN) {
            // DecelerateDaemon.isRun = true;
            // jcMotion.decelerate();
            // synchronized(decelerateDaemon){
                // try {
                //     decelerateDaemon.notifyAll();
                // } catch(Exception e) {
                //     e.printStackTrace();
                // }
            // }
        }
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
        decelerateDaemon.setDaemon(true);
        decelerateDaemon.start();

        GlobalScreen.addNativeKeyListener(new ConsoleDisplay());

        // System.out.println("Speed: " + jcMotion.getCurrentSpeed());
        // System.out.println(new String(new char[30]).replace("\0", "\r\n"));
        // System.out.flush();
        
        return;
    }

}