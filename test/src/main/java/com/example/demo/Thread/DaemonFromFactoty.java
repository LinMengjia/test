package com.example.demo.Thread;

import java.util.concurrent.TimeUnit;

public class DaemonFromFactoty implements Runnable{
    @Override
    public void run() {
        try {
            while (true){
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
            System.out.println("Interrupted");
        }
    }
}
