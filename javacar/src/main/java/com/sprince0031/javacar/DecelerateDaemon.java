package com.sprince0031.javacar;

public class DecelerateDaemon implements Runnable {

    // static boolean isRun = true;
    JavaCarMotion jcMotion = new JavaCarMotion();

    @Override
    public void run() {
        while (true) {
            jcMotion.decelerate();
            System.out.println("Speed: " + jcMotion.getCurrentSpeed());
            try {
                Thread.sleep(125);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(new String(new char[50]).replace("\0", "\r\n"));
            
        }
        // try {
        //     this.wait();
        // } catch (InterruptedException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
    }
}