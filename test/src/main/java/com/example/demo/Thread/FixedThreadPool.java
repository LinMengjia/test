package com.example.demo.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        for(int i = 0;i < 5;i++){
            service.execute(new LiftOff());
        }
        service.shutdown();
    }
}
