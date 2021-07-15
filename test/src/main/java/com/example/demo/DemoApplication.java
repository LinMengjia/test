package com.example.demo;

import com.example.demo.Thread.LiftOff;
import com.example.demo.controller.ScheduleTestController;
import com.example.demo.service.ScheduleService;
import com.example.demo.util.MainModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class DemoApplication {

    private static volatile int a = 0;

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
		/*Injector injector = Guice.createInjector(new MainModule());
		injector.getInstance(ScheduleService.class);

		AtomicInteger a = new AtomicInteger(0);
		System.out.println("the value of AtomicInteger after increment is >>> " + a.getAndIncrement());*/

        /*Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        System.out.println(a++);
                        Thread.sleep(500);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }*/


        LiftOff liftOff = new LiftOff();

        liftOff.run();

    }

}
