package com.example.demo.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0;i < 5;i++){
            executorService.execute(new LiftOff());
        }
        System.out.println("Thread is stopping");
        executorService.shutdown();
        System.out.println("Thread is stopped");
    }
}
