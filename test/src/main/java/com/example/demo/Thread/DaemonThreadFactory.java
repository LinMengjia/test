package com.example.demo.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class DaemonThreadFactory implements ThreadFactory {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool(new DaemonThreadFactory());
        for (int i = 0;i < 10;i++){
            service.execute(new DaemonFromFactoty());
        }
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(5000);
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread();
        thread.setDaemon(true);
        return thread;
    }
}
